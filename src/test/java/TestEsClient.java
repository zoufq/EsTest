import common.EsUtil;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Requests;

/**
 * Created by Administrator on 2017/6/28.
 */
public class TestEsClient {
    public static void main(String[] args) {

        try {
            createBangMapping();
            /*
            //搜索数据
            GetResponse response = client.prepareGet("bank", "account", "25").execute().actionGet();
            //输出结果
            System.out.println(response.getSourceAsString());
            //关闭client
            client.close();*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createBangMapping(){
        try {
            /*IndexRequestBuilder lrb = ESTools.client.prepareIndex("zhidao", "zhidao").setSource("{\"zhidao\":{\"zhidao\":\"\"}}", XContentType.JSON);
            IndexResponse idresponse = lrb.execute().get();
            System.out.println(idresponse.toString());*/
            PutMappingRequest mapping = Requests.putMappingRequest("zhidao").type("zhidao").source(ZhidaoMapping.getMapping());
            PutMappingResponse putRep = EsUtil.client.admin().indices().putMapping(mapping).actionGet();
            System.out.println(putRep.toString());
            //搜索数据
            GetResponse response = EsUtil.client.prepareGet("zhidao", "zhidao", "_mapping").execute().actionGet();
            //输出结果
            System.out.println(response.getSourceAsString());
            //关闭client
            EsUtil.client.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
