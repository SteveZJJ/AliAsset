package com.eam.context;

import lombok.Getter;

/**
 * @Author JasonZhao
 * @Description // 工单默认的一些类型
 * @Date 2018/8/27 12:02:16
 * @Param
 * @Return
 **/

@Getter
public enum EventDefault {
	
	YS("10YS","YS10","验收单",Constants.EVT_DETAIL_TYPE_EVENT),
	RZ("20RZ","TS10","%s 领用单 %s",Constants.EVT_DETAIL_TYPE_PART),
	LZ("30LZ","TS40","%s Leave on %s",Constants.EVT_DETAIL_TYPE_EVENT),
	LY("40LY","TS10","%s日常领用(借用）%s",Constants.EVT_DETAIL_TYPE_PART),
	XJ("50XJ","TS40","%s资产续借%s",Constants.EVT_DETAIL_TYPE_EVENT),
	GH("60GH","TS40","%s日常归还%s",Constants.EVT_DETAIL_TYPE_EVENT),
	BF("70BF","TS10","%s报废申请%s",Constants.EVT_DETAIL_TYPE_EVENT),
	ZR("80ZR","TS10","%s转让申请%s",Constants.EVT_DETAIL_TYPE_EVENT),
	PD("90PD","PD10","%s盘点任务%s",Constants.EVT_DETAIL_TYPE_EVENT),
	SDYS("100YS","YS10","%s手动新增%s",Constants.EVT_DETAIL_TYPE_EVENT),
	CZ("110CZ","TS10","%s处置申请%s",Constants.EVT_DETAIL_TYPE_EVENT),
	AQCL("120AQ","TS10","%s安全处理申请%s",Constants.EVT_DETAIL_TYPE_EVENT),
	ZY("130ZY","TS10","%s转移申请%s",Constants.EVT_DETAIL_TYPE_EVENT),
	DB("140DB","TS10","%s调拨申请%s",Constants.EVT_DETAIL_TYPE_REQ),
	PL("150PL","YS10","%s批量新增申请%s",Constants.EVT_DETAIL_TYPE_EVENT),
	ZG("160ZG","TS40","%s Transfer on %s",Constants.EVT_DETAIL_TYPE_EVENT);

	private String evtJobType;
	private String evtStatus;
	private String evtDesc;
	private String detailType;
	
	EventDefault(String evtJobType,String evtStatus,String evtDesc,String detailType){
		this.evtJobType = evtJobType;
		this.evtStatus = evtStatus;
		this.evtDesc = evtDesc;
		this.detailType = detailType;
	}
}
