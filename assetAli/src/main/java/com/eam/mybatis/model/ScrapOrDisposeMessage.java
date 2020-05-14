package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

/**************************************
 *@ClassName ScrapOrDisposeMessage
 *@Description 报废处置实体类
 *@Author Steve Zhang
 *@Date 2019年4月17日19:40:52
 *@Version 1.0
 **************************************/
@Data
public class ScrapOrDisposeMessage {
	private String corp;//组织  evt_org
	private String actionType;
	//状态  I-新建插入，UH 更新单据头基本信息 UL 更新报废处置清单
	private String ouCode;//所属OU  evt_udfchar08
	private String department;//归口部门
	private String personCode;//申请人
	private String personName;//申请人姓名

	private String evtCode;//资产中台创建的单据号，在更新时需传入
	private String comments;//申请原因
	private String central; //归口管理
	private List <ScrapLine> item;//报废、处置单详情

	private String evtJobType;//
	private String evtStatus;//
	private String evtDesc;//
	private String language;//

	private String scrapType;//报废类型
	private String scrapTypeDesc; //报废类型描述

	private String faScrapType; //财务报废类型

	private String disposeType;//处置类型
	private String disposeTypeDesc;//处置类型描述

	private String secureType;//安全处理类型
	private String secureTypeDesc;//安全处理类型描述

	private List <Attachment> attachments;//文档清单
	private String detailType;//子项类型

	private List <Quotation> quotations; //三方比价报价行
	private String reason; //三方报价原因备注


	private String dueDate;//申请日期
	private String JobTypeDesc; //工单类型描述
	private String corpDesc; //组织描述
	private String departmentDesc; //部门描述
	private String statusDesc; //状态描述
}

