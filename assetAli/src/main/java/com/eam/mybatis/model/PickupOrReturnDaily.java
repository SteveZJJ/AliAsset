package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

/**************************************
 *@ClassName PickupOrReturnDaily
 *@Description   //日常借用、信息
 *@Author jason
 *@Date 2018/8/25 - 11:49
 *@Version 1.0
 **************************************/
@Data
public class PickupOrReturnDaily {
	private String corp;//组织  evt_org
	private String actionType;
	//状态  I-新建插入，UH 更新单据头基本信息 UL 更新领用清
	private String reqCode;//借用单\归还单
	private String ouCode;//OU
	private String reqStatus;//员工借用单\归还单  状态，人力系统
	private String evtCode;//资产中台创建的单据号，在更新时需传入
	private String location;//借用、归还地点
	private String department;//借用、归还部门
	private String initialPerson;//借用、归还   申请人
	private String initialPersonName;//借用、归还   申请人姓名
	private String responsible;//借用人，归还人
	private String responsibleName;//借用人姓名 归还人姓名，如无人的姓名，缺省为'-'
	private String onBehalfMark;//代他人领用、借用标识  Y-代他人领用借用。N-本人借用领用
	private String borrowType;//借用类型。归还时为 空
	private String usage;//用途(使用说明) 归还时为 空
	private String comments;//申请原因
	
	private List <PickupLine> item;//借用、归还 清单
	private List <Attachment> attachments;//文档清单
	
	
	private String evtJobType;//
	private String evtStatus;//
	private String evtDesc;//
	private String detailType;//子项类型
}
