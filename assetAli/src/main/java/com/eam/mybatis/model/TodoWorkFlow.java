package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

/**************************************
 *@ClassName TodoWorkFlow
 *@Description   //待办工作流
 *@Author jason
 *@Date 2018/8/30 - 15:31
 *@Version 1.0
 **************************************/
@Data
public class TodoWorkFlow {
	//private String eamTaskId;//任务单号
	//private String ownerNo;//被通知人
	//private String confirmationType;//通知类型
	/***
	 * 领用-obtain
	 * 借用-borrow
	 * 更换-change
	 * 部门领用-public
	 **/
	List <TodoWorkFlowDetail> item;
	private String eamTaskId;//任务单号 evt_Code
	private String eamTransactionId;//交易号  tra_Code
	private String ownerNo;//被通知人 tra_pers
	private String confirmationType;//通知类型 evt_jobtype evt_udfchar07
	private String confirmStatus;//确认状态 tra_udfchar02  UCOD,PLST
	private String confirmName;//确认状态描述  燕来取得名字
	private String transDate;//领用时间 TRA_DATE
	private String confirmDate;//确认时间 TRA_UDFDATE01
	private String operator;//发放人 TRA_AUTH
	private String instanceId;//AMP流程单号 tra_udfchar01
	private String requestId;//AMP需求单号 evt_udfchar01
	private String language;//语言
	
	
	private String transId;//接口交易ID
	
	//public TodoWorkFlow(String eamTaskId,String eamTransactionId,String ownerNo,String confirmationType,String operator){
	//	this.eamTaskId = eamTaskId;
	//	this.eamTransactionId = eamTransactionId;
	//	this.ownerNo = ownerNo;
	//	this.confirmationType = confirmationType;
	//	this.operator = operator;
	//}
	//
	
}
