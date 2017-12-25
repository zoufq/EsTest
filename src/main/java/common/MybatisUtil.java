package common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
	private static SqlSessionFactory factory;
	static {
		String configName = "mybatis-config.xml";
		try {
			InputStream in = Resources.getResourceAsStream(configName);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取SqlSession
	public static SqlSession getSession() {
		return factory.openSession();
	}

	// 关闭SqlSession
	public static void closeSession(SqlSession session) {
		if (session != null) {
			session.close();
		}
	}
}
