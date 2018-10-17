package com.datacenter.core.utils.excel;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.datacenter.core.pojo.AiTemplate;

public class ExcelLoadTools {


	public static void initAchievement(List<AiTemplate> aiTemplateList, String path) {
		// 调用Excel解析类，创建Achievement对象
		POIPerformanceExcel2007 poiExcel2007 = new POIPerformanceExcel2007();
		File file =new File(path + "/AI.xlsx");
		File file1 =new File("D://work//AI.xlsx");
		
		boolean aa=file.exists();
		boolean bb=file1.exists();
		poiExcel2007.readExcel2007OneSheet(path + "/AI.xlsx", 0, 5);
		Map<Integer, String[]> all = poiExcel2007.getExcelData();		
		for (int i = 1; i <= all.size(); i++) {
			String[] ss = all.get(i);//表中的某行数据
			String id = ss[0];
			if ("0".equals(id)) {
				continue;
			}
			AiTemplate aiTemplate = new AiTemplate();
			aiTemplate.setId(Long.parseLong(id));//成就模版唯一id
			aiTemplate.setName(ss[1]);
			aiTemplate.setPrevilegeGoodsIds(ss[2]);
			aiTemplate.setActionPeriod(ss[3]);
			aiTemplate.setSingleIncreases(ss[4]);
			aiTemplateList.add(aiTemplate);
		}


//		poiExcel2007 = new POIPerformanceExcel2007();
//		poiExcel2007.readExcel2007OneSheet(path + "aiTemplate.xlsx", 1, 14);
//		all = poiExcel2007.getExcelData();
//		for (int i = 1; i <= all.size(); i++) {
//			String[] ss = all.get(i);
//			AchievementLvTemplate template = new AchievementLvTemplate();
//			template.setLv(AppContext.parseInt(ss[0]));
//			template.setConditions(Util.initMagicAttributeArray(ss, 1, 1));
//			template.setPoint(AppContext.parseInt(ss[3]));
//			template.setMagicAttrs(Util.initMagicAttributeArray(ss, 4, 2));
//			template.setTupo(ss[13].equals("1"));//是否需要突破
//			aiTemplateBind.getAchievementLvMap().put(template.getLv(), template);//成就注册类
//		}
	}

}
