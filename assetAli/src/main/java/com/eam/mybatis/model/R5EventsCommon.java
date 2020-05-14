package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

/**************************************
 *@ClassName R5EventsCommon
 *@Description   //工单通用接口查询
 *@Author jason
 *@Date 2018/9/12 - 11:32
 *@Version 1.0
 **************************************/
@Data
public class R5EventsCommon {
	private String taskType;//evt_Jobtype 任务单类型 根据代码取描述
	private String taskStatus; //evt_status 工单状态
	private String reqCode;//evt_udfchar01
	private String eventCode;//evt_code
	private String corp;//evt_org 组织
	private String location;//evt_object 部门或地点
	private String locationDesc;//evt_object 部门或地点描述
	private String department;//evt_MRC ID+描述
	private String initialPerson;//evt_origin  验收人、入职、申请、离职人
	private String initialPersonName;//
	private String responsible;//evt_udfchar05 领用人或归还人
	private String responsibleName;//
	private String reqDate;//evt_date   时间
	private String onBehalfMark;//evt_udfchkbox01 Y/N
	private String borrowType;//evt_udfchar12 描述
	private String usage;//evt_udfchar07 查描述
	private String poNumber;//evt_udfchar10 PO 单号
	private String acceptNumber;//evt_udfchar11 发运单号
	private String expressNumber;//evt_udfchar13 快递单号
	private String wareHouse;//evt_udfchar16 仓库ID 加名称
	private String OU;//evt_udfchar08 所属OU
	private String comments;//evt_workaddress  申请原因
	private String property;//evt_udfchar21  所有权
	private String inStore;//evt_udfchkbox01  是否到库房
	private String duration; //evt_udfnum01 借用领用时长
	
	private String jobType;
	private List <Attachment> attachment;//附件清单
	private List <R5EventsDetails> item;//
	//
}
