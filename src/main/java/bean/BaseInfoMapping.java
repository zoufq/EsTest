package bean;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

/**
 * Created by zoufq on 2017/10/30.
 */
public class BaseInfoMapping {
    /**
     * 创建baseinfo的mapping
     * @return
     */
    public static XContentBuilder createMapping(){
        XContentBuilder xContentBuilder = null;
        try {
            xContentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties")
                    .startObject("id").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("url").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("sname").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("firClass").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("secClass").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("city").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("region").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("comment").field("type","integer").endObject()
                    .startObject("expense").field("type","double").endObject()
                    .startObject("kouwei").field("type","double").endObject()
                    .startObject("env").field("type","double").endObject()
                    .startObject("server").field("type","double").endObject()
                    .startObject("addr").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("phone").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("star").field("type","integer").endObject()
                    .startObject("tese").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("other").field("type","string").field("index","not_analyzed").endObject()
                    .startObject("crawlTime").field("type","date").endObject()
                    .endObject()
                    .endObject();
        }catch (Exception e){
            e.printStackTrace();
        }

        return xContentBuilder;
    }

    /**
     * 封装baseInfo实例的json信息
     * @param baseInfo
     * @return
     */
    public static XContentBuilder getXContentBuilder(BaseInfo baseInfo){
        XContentBuilder xContentBuilder = null;
        try {
            xContentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id",baseInfo.getId())
                    .field("url",baseInfo.getUrl())
                    .field("sname",baseInfo.getSname())
                    .field("firClass",baseInfo.getFirclass())
                    .field("secClass",baseInfo.getSecclass())
                    .field("city",baseInfo.getCity())
                    .field("region",baseInfo.getRegion())
                    .field("comment",baseInfo.getComment())
                    .field("expense",baseInfo.getExpense())
                    .field("kouwei",baseInfo.getKouwei())
                    .field("env",baseInfo.getEnv())
                    .field("server",baseInfo.getServer())
                    .field("addr",baseInfo.getAddr())
                    .field("phone",baseInfo.getPhone())
                    .field("star",baseInfo.getStar())
                    .field("tese",baseInfo.getTese())
                    .field("other",baseInfo.getOther())
                    .field("crawlTime",baseInfo.getCrawltime())
                    .endObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return xContentBuilder;
    }
}
