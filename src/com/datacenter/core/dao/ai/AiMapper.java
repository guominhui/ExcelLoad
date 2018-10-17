package com.datacenter.core.dao.ai;

import java.util.List;

import com.datacenter.core.pojo.AiTemplate;

public interface AiMapper {

	public List<AiTemplate> getAiInfos(int start_index,int length);

	public int addAiTemplate(AiTemplate aiTemplate);
	
	public int updateAiTemplate(AiTemplate aiTemplate);

	public int deleteAiTempalte(long aiTemplateId);
}
