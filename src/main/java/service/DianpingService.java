package service;

import bean.BaseInfo;
import common.MybatisUtil;
import dao.DianpingDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by zoufq on 2017/10/31.
 */
public class DianpingService implements DianpingDao{
    public List<BaseInfo> selectBaseInfo(String startId, Integer limitCount) {
        SqlSession session = MybatisUtil.getSession();
        try {
            return session.getMapper(DianpingDao.class).selectBaseInfo(startId,limitCount);
        } finally {
            session.close();
        }
    }
}
