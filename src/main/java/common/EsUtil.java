package common;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * Created by zoufq on 2017/10/30.
 */
public class EsUtil {
    public final static TransportClient client =  getClient();
    public static TransportClient getClient(){
        if(client!=null) return client;
        try {
            //设置集群名称
            Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
            //创建client
            TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.16.132"), 9300));
            return client;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 关闭
     */
    public static void close(){
        if(null != client){
            try {
                client.close();
            } catch (Exception e) {

            }
        }
    }
}
