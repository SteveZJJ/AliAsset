package com.eam.mybatis.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**************************************
 *@ClassName TransferOrAllocateMessage
 *@Description 转移调拨实体类
 *@Author Steve Zhang
 *@Date 2019年4月29日16:55:53
 *@Version 1.0
 **************************************/
@Data
public class TransferOrAllocateMessage {
	private String corp;//组织  evt_org
	private String actionType;
	//状态  I-新建插入，UH 更新单据头基本信息 UL 更新转移调拨清单
	private String ouCode;//所属OU  evt_udfchar08
	private String department;//归口部门
	private String personCode;//发起人
	private String personName;//发起人姓名


	private String applicantCode; //申请人工号
	private String applicantName; //申请人姓名
	private String receiverCode;  //接收人工号
	private String receiverName;  //接收人姓名
	private String ifAgency; //是否代他人申请

	private String fromStore; //调拨来源仓库
	private String fromStoreDesc;
	private String fromStoreOwner;
	private String fromStoreOwnerName;
	private String toStore; //调拨到仓库
	private String toStoreDesc;
	private String toStoreOwner;
	private String toStoreOwnerName;

	private String fromLocation;
	private String fromLocationDesc;
	private String toLocation;
	private String toLocationDesc;
	private String fromOUCode;
	private String fromOUDesc;
	private String toOUCode;
	private String toOUDesc;
	private String fromRegionalSection;
	private String toRegionalSection;
	private String fromRegionalSectionDesc;
	private String toRegionalSectionDesc;
	@JsonProperty(value="costCenterFrom")
	private String fromCostCode;
	@JsonProperty(value="costCenterTo")
	private String toCostCode;

	@JsonProperty(value="areaAdminFrom")
	private String fromAreaAdmin;
	@JsonProperty(value="areaAdminFromDesc")
	private String fromAreaAdminName;
	@JsonProperty(value="areaAdminTo")
	private String toAreaAdmin;
	@JsonProperty(value="areaAdminToDesc")
	private String toAreaAdminName;
	@JsonProperty(value="areaFAAdminFrom")
	private String fromAreaFAAdmin;
	@JsonProperty(value="areaFAAdminFromDesc")
	private String fromAreaFAAdminName;
	@JsonProperty(value="areaFAAdminTo")
	private String toAreaFAAdmin;
	@JsonProperty(value="areaFAAdminToDesc")
	private String toAreaFAAdminName;



	private String evtCode;//资产中台创建的单据号，在更新时需传入
	private String comments;//申请原因
	private String central; //归口管理
	private List <TransferLine> item;//转移调拨单详情
	private List <LineSummary> summary;//汇总详情

	private String evtJobType;//
	private String evtStatus;//
	private String evtDesc;//
	private String language;//

	private List <Attachment> attachments;//文档清单
	private String detailType;//子项类型


	private String dueDate;//申请日期
	private String JobTypeDesc; //工单类型描述
	private String corpDesc; //组织描述
	private String departmentDesc; //部门描述
	private String statusDesc; //状态描述

	private String receiverCorp; //接收人组织

	private String transferMode; //转移转让模式

}
