package dianping;

import bean.BaseInfo;
import bean.BaseInfoMapping;
import common.CommonUtil;
import common.EsUtil;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import service.DianpingService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zoufq on 2017/10/31.
 */
public class BaseInfoIndex {
    private static String indexName = "dianping_index";
    private static String typeName = "baseinfo_mapping";
    private static DianpingService service = new DianpingService();
    public static void main(String[] args) throws ParseException {
        //get();
        query();
        /*Date cd=new Date();
        Integer count=0;
        Integer limitCount = 1000;
        String startId = "10010625";
        List<BaseInfo> baseInfos = service.selectBaseInfo(startId,limitCount);
        boolean hasNext = false;
        if(baseInfos!=null&&baseInfos.size()>0){
            hasNext = true;
        }
        while (hasNext){
            try {
                addDataToIndexBulk(baseInfos);
                startId = baseInfos.get(baseInfos.size()-1).getId();
                baseInfos = service.selectBaseInfo(startId,limitCount);
                if(baseInfos!=null&&baseInfos.size()>0){
                    hasNext = true;
                }
                count+=baseInfos.size();
                if(count>100000){
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("导入数据完成，导入数量："+count+"；总耗时："+((new Date().getTime()-cd.getTime())/1000));*/
    }

    /**
     * 创建索引 mapping
     */
    public static void buildIndexMapping(){
        CreateIndexRequestBuilder cib = EsUtil.client.admin().indices().prepareCreate(indexName);
        cib.addMapping(typeName, BaseInfoMapping.createMapping());
        cib.execute().actionGet();
    }

    /**
     * 单条数据插添加
     * @param baseInfo 单条数据
     */
    public static void addDataToIndexSingle(BaseInfo baseInfo){
        IndexResponse response = EsUtil.client.prepareIndex(indexName,typeName)
                .setSource(BaseInfoMapping.getXContentBuilder(baseInfo))
                .execute()
                .actionGet();
    }

    /**
     * 批量添加
     * @param infoList 数据集合
     */
    public static void addDataToIndexBulk(List<BaseInfo> infoList){
        if(infoList==null || infoList.size()<=0) return;
        BulkRequestBuilder brb = EsUtil.client.prepareBulk();
        for (BaseInfo bi:infoList) {
            brb.add(EsUtil.client.prepareIndex(indexName,typeName,bi.getId()).setSource(BaseInfoMapping.getXContentBuilder(bi)));
        }
        BulkResponse rep = brb.execute().actionGet();
        if(rep.hasFailures()){
            System.out.println("addDataToIndexBulk:"+rep.buildFailureMessage());
        }else {
            System.out.println("addDataToIndexBulk success");
        }
    }

    /**
     * 根据id获取单条数据
     */
    public static void get(){
        GetResponse getResponse = EsUtil.client.prepareGet(indexName,typeName,"1000000")
                .execute().actionGet();
        System.out.println("id:"+getResponse.getId());
        System.out.println("resultStr:"+getResponse.getSourceAsString());
    }

    /**
     * query查询
     * term/match/range
     */
    public static void query() throws ParseException {
        // term 查询
        QueryBuilder termQuery = QueryBuilders.termQuery("firClass","美食");
        // matchQuery
        QueryBuilder matchQuery = QueryBuilders.matchQuery("city","贺州");
        // rangeQuery
        QueryBuilder rangeQuery = QueryBuilders.rangeQuery("crawlTime").gt("2017-11-01T03:00:00.000Z").lt("2017-11-01T23:00:48.000Z");
        //boostingQuery
        BoostingQueryBuilder boostingQuery = QueryBuilders.boostingQuery(termQuery,rangeQuery);
        // queryString
        QueryStringQueryBuilder queryString = QueryBuilders.queryStringQuery("营业时间");

        HighlightBuilder hight = new HighlightBuilder();
        hight.preTags("<h1>");
        hight.postTags("</h1>");
        hight.field("city");
        // search
        SearchResponse searchResponse = EsUtil.client.prepareSearch(indexName)
                .setTypes(typeName)
                .setQuery(termQuery)
                .setQuery(matchQuery)
                .highlighter(hight)
                .addSort("crawlTime", SortOrder.ASC)
                .setSize(15)
                .execute()
                .actionGet();
        SearchHits searchHits = searchResponse.getHits();
        System.out.println("查询到的记录数："+searchHits.getTotalHits());
        for (SearchHit hit: searchHits) {
            System.out.println("String方式打印文档搜索内容:");
            System.out.println(hit.getSourceAsString());
            System.out.println("Map方式打印高亮内容");
            System.out.println(hit.getHighlightFields());

            System.out.println("遍历高亮集合，打印高亮片段:");
            Text[] text = hit.getHighlightFields().get("city").getFragments();
            for (Text str:text) {
                System.out.println(str);
            }
        }

        // MultiSearch
        /*SearchRequestBuilder srb1 = EsUtil.client.prepareSearch(indexName).setTypes(typeName).setQuery(termQuery).setSize(5);
        SearchRequestBuilder srb2 = EsUtil.client.prepareSearch(indexName).setTypes(typeName).setQuery(matchQuery).setSize(3);
        MultiSearchResponse multiSearchResponse = EsUtil.client.prepareMultiSearch().add(srb1).add(srb2).execute().actionGet();
        for (MultiSearchResponse.Item item:multiSearchResponse.getResponses()) {
            System.out.println(item.getResponse().toString());
        }*/
    }

    /**
     * terms 分组查询
     * term/match/range
     */
    public static void agg() throws ParseException {
        // term 查询
        QueryBuilder termQuery = QueryBuilders.termQuery("city","广州");
        // class_agg
        TermsAggregationBuilder termsAgg = AggregationBuilders.terms("class_agg").field("firClass");
        // city_agg
        TermsAggregationBuilder subAgg = AggregationBuilders.terms("city_agg").field("city");
        termsAgg.subAggregation(subAgg);
        // search
        SearchResponse searchResponse = EsUtil.client.prepareSearch(indexName)
                .setTypes(typeName)
                //.setQuery(termQuery)
                .addAggregation(termsAgg)
                .setSize(0)
                .execute()
                .actionGet();
        SearchHits searchHits = searchResponse.getHits();
        System.out.println("查询到的记录数："+searchHits.getTotalHits());
        Terms terms = searchResponse.getAggregations().get("class_agg");
        for (Terms.Bucket bucket: terms.getBuckets()) {
            System.out.println(bucket.getKey()+":"+bucket.getDocCount());
            Terms subterms = bucket.getAggregations().get("city_agg");
            for (Terms.Bucket subb:subterms.getBuckets()) {
                System.out.println(subb.getKey()+":"+subb.getDocCount());
            }
        }
    }
}
