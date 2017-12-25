package dao;

import bean.BaseInfo;

import java.util.List;

/**
 * Created by zoufq on 2017/10/31.
 */
public interface DianpingDao {
    /**
     * 获取商铺基本信息
     * @param startId 起始Id
     * @param limitCount 每次查询条数
     * @return 商铺信息集合集合
     */
    List<BaseInfo> selectBaseInfo(String startId,Integer limitCount);
}
