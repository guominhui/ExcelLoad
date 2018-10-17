package com.datacenter.core.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {

	public static SqlSessionFactory getFactory() {
		String resource = "conf.xml";
		InputStream is = MybatisUtils.class.getClassLoader().getResourceAsStream(
				resource);

		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

		return factory;
	}
}
