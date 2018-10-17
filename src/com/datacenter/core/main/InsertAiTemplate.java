package com.datacenter.core.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.chainsaw.Main;

import com.datacenter.core.dao.ai.AiMapper;
import com.datacenter.core.pojo.AiTemplate;
import com.datacenter.core.utils.MybatisUtils;
import com.datacenter.core.utils.excel.ExcelLoadTools;

public class InsertAiTemplate {
	
	
	public static void main(String[] args) {
		InsertAiTemplate insertAiTemplate=new InsertAiTemplate();
		insertAiTemplate.testAdd();
	}
	public void testAdd() {
		
		String path="D://work";
		List<AiTemplate> list=new ArrayList<AiTemplate>();
		ExcelLoadTools.initAchievement(list, path);
		String aa=list.get(0).getName();
		for(AiTemplate aiTemplate:list){
			insert(aiTemplate);
		}
	}
	
	public void insert(AiTemplate  aiTemplate){
		//处理数据库部分
		SqlSessionFactory factory = MybatisUtils.getFactory();
		//
		SqlSession session = factory.openSession(true);
		
		AiMapper mapper = session.getMapper(AiMapper.class);
		int add = mapper.addAiTemplate(aiTemplate);
		System.out.println(add);
		session.close();
	}
}
