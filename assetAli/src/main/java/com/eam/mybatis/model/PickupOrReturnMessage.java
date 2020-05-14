package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

/**************************************
 *@ClassName PickupOrReturnMessage
 *@Description 领用归还信息
 *@Author jason
 *@Date 2018/8/23 上午10:52
 *@Version 1.0
 **************************************/
@Data
public class PickupOrReturnMessage {
	private String corp;//组织  evt_org
	private String actionType;
	//状态  I-新建插入，更新U
	//状态  I-新建插入，UH 更新单据头基本信息 UL 更新领用清单
	private String ouCode;//所属OU  evt_udfchar08
	private String location;//入职地点  evt_object
	private String locationDesc;//入职地点描述
	private String department;//入职部门\离职部门
	private String personCode;//入职人\离职人
	private String personName;//入职人姓名\离职人姓名
	private String dueDate;//入职日期\离职归还日期
	private String reqCode;//人力系统入职单\离职单
	private String evtCode;//资产中台创建的单据号，在更新时需传入
	private String reqStatus;//员工入职单状态
	private String comments;//申请原因
	private String central; //归口管理
	private List <PickupLine> item;//领用单详情
	
	private String evtJobType;//
	private String evtStatus;//
	private String evtDesc;//
	private String language;//
	

	//下面是日常增加的接口字段信息
	private String initialPerson;//借用、归还   申请人
	private String initialPersonName;//借用、归还   申请人姓名
	private String responsible;//借用人，归还人
	private String responsibleName;//借用人姓名 归还人姓名，如无人的姓名，缺省为'-'
	private String onBehalfMark;//代他人领用、借用标识  Y-代他人领用借用。N-本人借用领用
	private String borrowType;//借用类型。归还时为 空
	private String usage;//用途(使用说明) 归还时为 空
	private List <Attachment> attachments;//文档清单
	private String detailType;//子项类型

	//转岗单更新字段
	private String formerLeader; //前主管工号
	private String newDept;

}
