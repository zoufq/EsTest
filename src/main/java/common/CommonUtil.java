package common;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName:CommonUtil
 * @Description:TODO 通用工具类
 * @author:fuhuaguo
 * @email:fhg@qianjieyun.com
 * @date 2016年5月11日
 */
public class CommonUtil {

	//最短时间戳
	private static SimpleDateFormat shortestDateFmt = new SimpleDateFormat("yyMMddHHmmssSSS");
	//正常时间戳
	private static SimpleDateFormat normalDateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//解析字符串前缀数字
	private static Pattern prefixNumPtn = Pattern.compile("^\\d*");
	
	//获取当前时间
	public static String getCurrShortestDate(){
		return shortestDateFmt.format(new Date());
	}
	public static String getShortestDate(Date date){
		return shortestDateFmt.format(date);
	}
	public static Date getShortestDate(String date) throws ParseException{
		if(date == null)
			return null;
		return shortestDateFmt.parse(date);
	}
	
	//获取当前时间
	public static String getCurrNormalDate(){
		return normalDateFmt.format(new Date());
	}
	public static String getNormalDate(Date date){
		if(date == null)
			return null;
		return normalDateFmt.format(date);
	}
	public static Date getNormalDate(String date) throws ParseException{
		if(date == null)
			return null;
		return normalDateFmt.parse(date);
	}
	//依据格式解析时间
	public static String transDate(Date date,String format){
		SimpleDateFormat tempFmt = new SimpleDateFormat(format);
		
		return tempFmt.format(date);
	}
	public static Date transDate(String date,String format) throws ParseException{
		SimpleDateFormat tempFmt = new SimpleDateFormat(format);
		
		return tempFmt.parse(date);
	}
	/**
	 * 获取以数字打头的字符串的前缀数字 无效或没有为0
	 */
	public static int getPrefixNum(String str){
		if(str == null || str.length() == 0)
			return 0;
		Matcher m = prefixNumPtn.matcher(str);
		if(m.find()){
			return Integer.parseInt(m.group());
		}else{
			return 0;
		}
	}
	public boolean checkProxyIp(String ip,String port){
		//默认为5S超时
		return checkProxyIp(ip, port,5000);
	}
	/**
	 * 测试代理IP是否可用
	 * @param ip
	 * @param port
	 * @timeout 超时时间 为毫秒
	 * @return
	 */
	public boolean checkProxyIp(String ip,String port,int timeout){
		try{
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, Integer.parseInt(port)));
			//建立 HttpURLConnection 连接对象
			HttpURLConnection conn = (HttpURLConnection)new URL("http://www.baidu.com/").openConnection(proxy);			
			
			conn.setConnectTimeout(timeout);
			conn.connect();
			//获取 返回的satusCode
			int statusCode = conn.getResponseCode();
			conn.disconnect();
			
			return statusCode == 200 ? true : false;
		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 装载属性文件 一般是在程序初始化的时候
	 * @param propFile
	 * @throws Exception
	 */
	public static void loadProp(String propFile) throws Exception{
		InputStream in = ClassLoader.getSystemResourceAsStream("common.properties");
		prop.load(new InputStreamReader(in,"UTF-8"));
	}
	private static Properties prop = new Properties();
	
	/**
	 * 读取通用属性，没有的话使用默认值
	 */
	public static String getProperty(String key,String dft){
		return prop.getProperty(key, dft);
	}
	private static JedisPool jedisPool;
	private static String jedisPass;
	/**
	 * 获取jedis连接池
	 * @return
	 */
	private static JedisPool getJedisPool(){
		jedisPass = getProperty("redis.pass", null);
		if(jedisPool == null){
			// 建立连接池配置参数
	        JedisPoolConfig config = new JedisPoolConfig();
	        // 设置最大连接数
	        config.setMaxTotal(5);
	        // 设置最大阻塞时间，记住是毫秒数milliseconds
	        config.setMaxWaitMillis(5000);
	        
			jedisPool = new JedisPool(getProperty("redis.host", null),Integer.parseInt(getProperty("redis.port", "6379")));
		}
		
		return jedisPool;
	}
	/**
	 * 外界不用关闭
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Jedis getJedis(){
		Jedis temp = getJedisPool().getResource();
		if(jedisPass != null){
			temp.auth(jedisPass);
		}
		jedisPool.returnResource(temp);
		return temp;
	}
	/**
	 * 将一个名为filename的文件转化为一个byte数组  
	 */
    public static byte[] file2Byte(File zipFile) throws Exception{  
        byte[] b = null;  
        BufferedInputStream is = null;  

        b = new byte[(int) zipFile.length()];  
        is = new BufferedInputStream(new FileInputStream(zipFile));  
        is.read(b);  
        
        if(is != null) {  
        	is.close();
        }
        return b;  
    }  
    
    /**
     * 获取操作系统类型
     */
    public static String getOSType(){
    	String temp  = System.getProperty("os.name");
    	if(temp.contains("Mac")){
    		return "mac";
    	}else if(temp.contains("Win")){
    		return "win";
    	}else{
    		try {
				Process process = Runtime.getRuntime().exec("getconf LONG_BIT");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String s = bufferedReader.readLine();
				if(s.contains("64")){
					return "linux64";
				}else{
					return "linux32";
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "linux64"; //默认Linux64
			}
    	}
    }
    /**
     * 测试IP是否能够联通 true false 或 异常信息
     */
    public static String testConn(String ip){
		try {
			return "" + InetAddress.getByName(ip).isReachable(3*1000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
    
    //系统常量 对应数据库中的constantKV 表
    private static Map<String, String> constantMap;
    
    /**
     * 外界读取数据库 并进行设置 主要是为了获取常量
     * @param map
     */
    public static void setConstantMap(Map<String,String> map){
    	constantMap = map;
    }
    /**
     * 获取常量
     */
    public static String getConstantV(String constantK){
    	return constantMap.get(constantK);
    }
    //解析字符串前缀数字
	private static Pattern suffixNumPtn = Pattern.compile("\\d*$");
    /**
	 * 获取以数字打头的字符串的前缀数字
	 */
	public static int getSuffixNum(String str){
		if(str == null || str.length() == 0)
			return 0;
		Matcher m = suffixNumPtn.matcher(str);
		if(m.find()){
			return Integer.parseInt(m.group());
		}else{
			return 0;
		}
	}
	//从一个字符串中解析出
	public static Integer parseNum(String str,String reg){
		if(str == null || str.length() == 0)
			return null;
		Pattern numPtn = Pattern.compile(reg);
		Matcher m = numPtn.matcher(str);
		if(m.find()){
			return Integer.parseInt(m.group(1));
		}else{
			return null;
		}
	}
	public static String parseString(String str,String reg){
		if(str == null || str.length() == 0)
			return null;
		Pattern numPtn = Pattern.compile(reg);
		Matcher m = numPtn.matcher(str);
		if(m.find()){
			return m.group(1);
		}else{
			return null;
		}
	}
	
	/**
	 * 下载图片 并返回存储文件路径
	 * @param path
	 * @param imgSrc
	 * @return
	 */
	public static String getDownloadImg(String path,String imgSrc){
		String imgName = UUID.randomUUID().toString() + fetchPostfix(imgSrc);
		
		org.jsoup.Connection conn = Jsoup.connect(imgSrc)
				.ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36");

		try {
			Response response = conn.execute();
			if (response.statusCode() != 404){
				//FileUtils.writeByteArrayToFile(new File(path+"/"+imgName), response.bodyAsBytes());
				//预留文件写的时间
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return path + "/" + imgName;
	}
	//图片URL后缀
	private static Pattern imgPostfixPattern = Pattern.compile("(.[\\w]+$)");
	//提取图片名字的图片后缀
	public static String fetchPostfix(String imgSrc){
		Matcher m = imgPostfixPattern.matcher(imgSrc);
		if(m.find()){
			return m.group(1);
		}else{
			return "";
		}
	}
	/**
	 * 调用webAPi 并传递数据 返回json数据
	 * @param webApi web api
	 * @param json 发送的数据
	 * @return 接收的数据
	 */
	public static String connWebAPI(String webApi, String json){
		HttpURLConnection conn=null;
		
		try {
			URL url = new URL(webApi);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
		    conn.setRequestProperty("Content-type","application/json");
		    conn.setDoOutput(true);
		    OutputStream ou=conn.getOutputStream();
		    ou.write(json.getBytes("utf8"));
		    ou.flush();
		    ou.close();
		    
		    //读取返回数据
		    InputStream is = conn.getInputStream();
	        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
	        
	        return buffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally{
			if(conn != null){
				conn.disconnect();
			}
		}
	}
	public static String getDriverName(String os) throws IOException{
		if(os == null)
			return null;
		switch (os) {
		case "win":
			return "chromedriver.exe";
		case "mac":
			return "chromedriver";
		case "linux_32":
			return "chromedriver_linux32";
		case "linux_64":
		default:
			return "chromedriver_linux64";
		}
	}
	
	//获取字符串的MD5值
	public static String digestMd5(String str){
		String key = "ertsdggsdg343dgdfgdger343";
		if(str == null){
			return null;
		}else{
			char hexDigits[]={'0','1','2','A','B','C','D','3','4','5','6','7','8','9','E','F','G'};       
            byte[] btInput = (key+str).getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = null;
			try {
				mdInst = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char cs[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                cs[k++] = hexDigits[byte0 >>> 4 & 0xf];
                cs[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(cs);
		}
	}
	/**
     * 读取url中数据，并以字节的形式返回
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static byte[] readStream(InputStream inputStream) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = inputStream.read(buffer)) !=-1){
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }
	//--------------------------------zwg--------------------------------------------------
	//返回所有匹配的值，集合,zwg,2017-04-10
	public static ArrayList<String> parseStringList(String str, String reg, int group){
		if(str == null || str.length() == 0)
			return null;
		Pattern numPtn = Pattern.compile(reg);
		Matcher m = numPtn.matcher(str);
		ArrayList<String> datas=new ArrayList<>();
		while (m.find()) {
			datas.add(m.group(group));
		}
		return datas;
	}
	
	public static String parseStringByGroup(String str, String reg, int group){
		if(str == null || str.length() == 0)
			return null;
		Pattern numPtn = Pattern.compile(reg);
		Matcher m = numPtn.matcher(str);
		while (m.find()) {
			return m.group(group);
		}
		return "";
	}

	//获取日期
	public static String getDate(String pattern) {
		Date now = new Date();
		SimpleDateFormat sdFormatter = new SimpleDateFormat(pattern);
		return sdFormatter.format(now);
	}
	//当前天数加减指定天数
	public static String getDate(int days,String pattern){
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DAY_OF_MONTH, days);
		Date newdate = c.getTime();
		SimpleDateFormat sdFormatter = new SimpleDateFormat(pattern);
		return sdFormatter.format(newdate);
	}
	//--------------------------------------------------------------

    /**
     * 解析url中的各参数
     * @param url url
     * @return 参数map集合
     */
	public static  Map<String,Object> urlParamAnalysis(String url){
		if(url.length()==0) return null;
		String[] strArr = url.split("\\?");
		if(strArr.length<2) return null;
		String[] params = strArr[1].split("&");
		Map<String,Object> paramMaps = new HashMap<>();
		for (String param:params) {
			String[] p = param.split("=");
			if(p.length<2) continue;
			paramMaps.put(p[0],p[1]);
		}
		return paramMaps;
	}

    /**
     * 从字符串中提取出数字
     * @param str 包含数字的字符串
     * @return 字符串中的数字
     */
    public static String extractNumS(String str){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 从字符串中提取出数字
     * @param str 包含数字的字符串
     * @return 字符串中的数字；如果没有数字，则返回0
     */
    public static Integer extractNumI(String str){
    	if(str == null || str.length() == 0){
    		return null;
    	}
        String result = extractNumS(str);
        if(StringUtils.isNotBlank(result)){
            return Integer.parseInt(result);
        }else {
            return null;
        }
    }
    //获取UUID
  	public static String uuid(){
  		return UUID.randomUUID().toString().replaceAll("-", "");
  	}
  	
  	//解析出 年月日 和 时分秒 正则匹配 日空格 应放在 日 和 空格的前面才可以
  	private static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
  	private static Pattern publishDatetimePtn = Pattern.compile("(\\d{4})?[-|年|/]?(\\d{1,2})?[-|月|/](\\d{1,2})?(?:(?:日\\s+)|(?:日)|(?:\\s+))(\\d{2})?[\\:|时]?(\\d{2})?[\\:|分]?(\\d{2})?");
  	private static Pattern publishDatePtn = Pattern.compile("(\\d{4})[-|年|/](\\d{1,2})[-|月|/](\\d{1,2})");
  	private static Pattern publishTimePtn = Pattern.compile("(\\d{1,2})?[\\:](\\d{1,2})?[\\:]?(\\d{1,2})?");
  	private static Pattern minutePtn = Pattern.compile("(\\d+)分钟前");
  	private static Pattern hourPtn = Pattern.compile("(\\d+)小时前");
  	private static Pattern dayPtn = Pattern.compile("(\\d+)天前");
  	//从字符串中解析出发布时间 无则为 null
  	public static Long parsePublishTime(String str){
  		//年月日 时分秒 解析
  		if(str.contains("月") || str.contains(" ") || str.contains("-") || str.contains("日")){
  			Matcher m = publishDatetimePtn.matcher(str);
  			if(m.find()){
  				if(m.group(1) != null){
  					calendar.set(Calendar.YEAR, Integer.parseInt(m.group(1)));
  				}
  				if(m.group(2) != null){
  					calendar.set(Calendar.MONTH, Integer.parseInt(m.group(2))-1);
  				}
  				if(m.group(3) != null){
  					calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(m.group(3)));
  				}
  				if(m.group(4) != null){
  					calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(m.group(4)));
  				}
  				if(m.group(5) != null){
  					calendar.set(Calendar.MINUTE, Integer.parseInt(m.group(5)));
  				}
  				if(m.group(6) != null){
  					calendar.set(Calendar.SECOND, Integer.parseInt(m.group(6)));
  				}
  				return calendar.getTimeInMillis();
  			}else{
  				m = publishDatePtn.matcher(str);
  				if(m.find()){
  					if(m.group(1) != null){
  						calendar.set(Calendar.YEAR, Integer.parseInt(m.group(1)));
  					}
  					if(m.group(2) != null){
  						calendar.set(Calendar.MONTH, Integer.parseInt(m.group(2)) -1);
  					}
  					if(m.group(3) != null){
  						calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(m.group(3)));
  					}
  					return calendar.getTimeInMillis();
  				}
  			}
  		}//时分秒解析
  		if(str.contains(":")){
  			Matcher m = publishTimePtn.matcher(str);
  			if(m.find()){
  				if(m.group(1) != null){
  					calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(m.group(1)));
  				}
  				if(m.group(2) != null){
  					calendar.set(Calendar.MINUTE, Integer.parseInt(m.group(2)));
  				}
  				if(m.group(3) != null){
  					calendar.set(Calendar.SECOND, Integer.parseInt(m.group(3)));
  				}
  				return calendar.getTimeInMillis();
  			}
  		}
  		if(str.contains("分钟前")){
			Matcher m = minutePtn.matcher(str);
			if(m.find()){
				return System.currentTimeMillis() - Integer.parseInt(m.group(1))*60*1000; 
			}
  		}
  		if(str.contains("小时前")){
			Matcher m = hourPtn.matcher(str);
			if(m.find()){
				return System.currentTimeMillis() - Integer.parseInt(m.group(1))*60*60*1000; 
			}
  		}
  		if(str.contains("天前")){
			Matcher m = dayPtn.matcher(str);
			if(m.find()){
				return System.currentTimeMillis() - Integer.parseInt(m.group(1))*24*60*60*1000; 
			}
  		}
  		if(str.contains("今天") || str.contains("刚刚")){
  			return System.currentTimeMillis();
  		}
  		if(str.contains("昨天")){
  			return System.currentTimeMillis() - 24*60*60*1000L;
  		}
  		
  		return null;
  	}

	/**
	 * 字符串转日期
	 * @str str 日期字符串
	 * @format 日期格式
	 */
	public static Date string2Date(String str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取本地IP地址
	 *
	 * @throws SocketException
	 */
	public static String getLocalIP() throws UnknownHostException, SocketException {
		if (isWindowsOS()) {
			return InetAddress.getLocalHost().getHostAddress();
		} else {
			return getLinuxLocalIp();
		}
	}

	/**
	 * 判断操作系统是否是Windows
	 *
	 * @return
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本地Host名称
	 */
	public static String getLocalHostName() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}

	/**
	 * 获取Linux下的IP地址
	 *
	 * @return IP地址
	 * @throws SocketException
	 */
	private static String getLinuxLocalIp() throws SocketException {
		String ip = "";
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				String name = intf.getName();
				if (!name.contains("docker") && !name.contains("lo")) {
					for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							String ipaddress = inetAddress.getHostAddress().toString();
							if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
								return ipaddress;
							}
						}
					}
				}
			}
		} catch (SocketException ex) {
			System.out.println("获取ip地址异常");
			ip = "127.0.0.1";
			ex.printStackTrace();
		}
		return ip;
	}

	/**
	 * 将字符串中的unicode转换成中文
	 * @param asciicode
	 * @return
     */
	public static String ascii2native ( String asciicode )
	{
		String[] asciis = asciicode.split ("\\\\u");
		StringBuilder sbResult = new StringBuilder();
		try
		{
			sbResult.append(asciis[0]);
			for ( int i = 1; i < asciis.length; i++ )
			{
				String code = asciis[i];
				sbResult.append((char) Integer.parseInt (code.substring (0, 4), 16));
				if (code.length () > 4)
				{
					sbResult.append(code.substring (4, code.length ()));
				}
			}
		}
		catch (NumberFormatException e)
		{
			return asciicode;
		}
		return sbResult.toString();
	}

	//替换掉json字符串中不合法的双引号
	public static String jsonString(String s){
		char[] temp = s.toCharArray();
		int n = temp.length;
		for(int i =0;i<n;i++){
			if(temp[i]==':'&&temp[i+1]=='"'&&temp[i-1]=='"'){
				for(int j =i+2;j<n;j++){
					if(temp[j]=='"'){
						if(temp[j+1]!=',' &&  temp[j+1]!='}' &&  temp[j+1]!=']'){
							temp[j]='*';
						}else if(temp[j+1]==',' ||  temp[j+1]=='}' &&  temp[j+1]!=']'){
							break ;
						}
					}
				}
			}
		}
		return new String(temp);
	}
	public static List<String> readMulti(String readPath) {
		List<String> lines=new ArrayList<>();
		//String readTxt = "";
		try {
			File f = new File(readPath);
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					//readTxt += line + "\r\n";
					lines.add(line);
				}
				read.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return readTxt;
		return lines;
	}

	private static Pattern sourcePtn = Pattern.compile("[来源|转载自][:|：]([^\\s]{1,})");
	
	/**
	 * 获取文章详情的 来源 或者转载自 的文字
	 */
	public static String getSourceStr(String str){
		if(StringUtils.isNotBlank(str)){
			Matcher m = sourcePtn.matcher(str);
			if(m.find()){
				return m.group(1);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
    public static void main(String[] args) throws IOException {
		String t4 = " 5月18日 10:31 ";
		System.out.println(new Date(parsePublishTime(t4)));
		
//		Pattern publishDatetimePtn1 = Pattern.compile("(\\d{4})?[-|年]?(\\d{1,2})?[-|月](\\d{1,2})?(?:(?:日\\s+)|(?:日)|(?:\\s+))(\\d{2})?[\\:]?(\\d{2})?[\\:]?(\\d{2})?");
//		Matcher m = publishDatetimePtn1.matcher(t4);
//		if(m.find()){
//			int sum = m.groupCount();
//			for (int i = 0; i < sum+1; i++) {
//				System.out.println(m.group(i));
//			}
//		}
//		System.out.println("end");
	}
}
