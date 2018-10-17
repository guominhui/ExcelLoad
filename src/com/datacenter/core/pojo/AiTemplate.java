package com.datacenter.core.pojo;

import java.util.Date;

public class AiTemplate {

	
	long id;
	String name;
	String previlegeGoodsIds;
	String actionPeriod;
	String singleIncreases;
	
	Date createTime;
	Date updateTime;
	
	long createTimeInMb;
	long updateTimeInMb;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrevilegeGoodsIds() {
		return previlegeGoodsIds;
	}
	public void setPrevilegeGoodsIds(String previlegeGoodsIds) {
		this.previlegeGoodsIds = previlegeGoodsIds;
	}
	public String getActionPeriod() {
		return actionPeriod;
	}
	public void setActionPeriod(String actionPeriod) {
		this.actionPeriod = actionPeriod;
	}
	public String getSingleIncreases() {
		return singleIncreases;
	}
	public void setSingleIncreases(String singleIncreases) {
		this.singleIncreases = singleIncreases;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public long getCreateTimeInMb() {
		return createTimeInMb;
	}
	public void setCreateTimeInMb(long createTimeInMb) {
		this.createTimeInMb = createTimeInMb;
	}
	public long getUpdateTimeInMb() {
		return updateTimeInMb;
	}
	public void setUpdateTimeInMb(long updateTimeInMb) {
		this.updateTimeInMb = updateTimeInMb;
	}
	public AiTemplate(long id, String name, String previlegeGoodsIds, String actionPeriod, String singleIncreases,
			Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.previlegeGoodsIds = previlegeGoodsIds;
		this.actionPeriod = actionPeriod;
		this.singleIncreases = singleIncreases;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public AiTemplate() {
		super();
	}

	
}
