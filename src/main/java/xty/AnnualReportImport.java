package xty;

import bean.AnnualReport;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/11/1.
 */
public class AnnualReportImport {
    public static void main(String[] args) {
        String file = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] ts = line.split("\001");
                AnnualReport model = new AnnualReport();
                model.setId(Long.parseLong(ts[0]));
                if(StringUtils.isNotBlank(ts[1])){
                    model.setCompanyId(Long.parseLong(ts[1]));
                }
                if(StringUtils.isNotBlank(ts[2])){
                    model.setNbnd(ts[2]);
                }
                if(StringUtils.isNotBlank(ts[3])){
                    model.setName(ts[3]);
                }
                if(StringUtils.isNotBlank(ts[4])){
                    model.setTyshxydm(ts[4]);
                }
                if(StringUtils.isNotBlank(ts[5])){
                    model.setZch(ts[5]);
                }

                if(StringUtils.isNotBlank(ts[6])){
                    model.setPhone(ts[6]);
                }
                if(StringUtils.isNotBlank(ts[7])){
                    model.setPostcode(ts[7]);
                }
                if(StringUtils.isNotBlank(ts[8])){
                    model.setAddr(ts[8]);
                }
                if(StringUtils.isNotBlank(ts[9])){
                    model.setEmail(ts[9]);
                }
                if(StringUtils.isNotBlank(ts[10])){
                    model.setQyjyzt(ts[10]);
                }

                if(StringUtils.isNotBlank(ts[11])){
                    model.setCyrs(ts[11]);
                }

                if(StringUtils.isNotBlank(ts[12])){
                    model.setJyzmc(ts[12]);
                }
                if(StringUtils.isNotBlank(ts[13])){
                    model.setZcze(ts[13]);
                }
                if(StringUtils.isNotBlank(ts[14])){
                    model.setSyzqyhj(ts[14]);
                }
                if(StringUtils.isNotBlank(ts[15])){
                    model.setXsze(ts[15]);
                }

                if(StringUtils.isNotBlank(ts[16])){
                    model.setLrze(ts[16]);
                }
                if(StringUtils.isNotBlank(ts[17])){
                    model.setZyywsr(ts[17]);
                }
                if(StringUtils.isNotBlank(ts[18])){
                    model.setJlr(ts[18]);
                }
                if(StringUtils.isNotBlank(ts[19])){
                    model.setNsze(ts[19]);
                }
                if(StringUtils.isNotBlank(ts[20])){
                    model.setFzze(ts[20]);
                }

                model.setCreateTime(System.currentTimeMillis());

                System.out.println(model);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
