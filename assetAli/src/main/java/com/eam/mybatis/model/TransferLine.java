package com.eam.mybatis.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;

/**************************************
 *@ClassName Transfer
 *@Description 转移单详情行
 *@Author Steve
 *@Date 2019年5月6日10:01:12
 *@Version 1.0
 **************************************/
@Data
public class TransferLine {
		private String assetCode;//填写大阿里编号

	private String assetDesc;//资产描述

	private String assetType;  //资产类别

	private String fixAssetCode;  //财务资产编号

	private String ouCode;  //ouCode

	private String ouName;  //ou描述

	private String corp; //组织

	private String usage; //使用说明

	private String usageDesc; //使用说明描述

	private String faAssetType; //成本信息

	private BigDecimal originValue; //原值

    private BigDecimal netValue; //净值

    private Integer usageMonth; //已使用月

    private Integer scrapMonth; //报废年限

	private String scrapDesc; //报废说明

	private String initDate;//启用日期

	private String ifScrap;//是否达到报废年限

	private String serialNo; //序列号

	private String featureDesc;  //品牌型号

	private String ownerDesc; //责任人

	private String ownerNo;  //责任人工号

	private String depNo;  //部门编码

	private String depDesc;  //部门描述

	private String userNo;  //使用人工号

	private String userDesc;  //使用人描述

	private String partCode; //物料编码

	private String partDesc; //物料编码描述

	private String assetStatus; //资产状态

	private String assetStatusDesc; //资产状态描述

	private String manageType;  //管理归口

	private String taxRate; //税率

	private String currencyCode; //币种

	private BigDecimal assignPrice; //转让价格

	private String assignCurrencyCode; //转让币种

	private String detail; //明细

	private String project;  //项目

	private String lineStatus;  //行状态

	private String evtCode;//关联工单号

	private String evtJobtype; //关联工单类型

	private String language; //语言标识

	@JsonProperty(value="outOrgId")
	private String fromCorp; //来源组织

	@JsonProperty(value="inOrgId")
	private String toCorp;  //转入组织

	@JsonProperty(value="outExpendCcid")
	private String fromCCID;  //来源CCID

	@JsonProperty(value="inExpendCcid")
	private String toCCID; //转入CCID

	@JsonProperty(value="outCcidSegments")
	private String fromCCIDSegments;

	@JsonProperty(value="inCcidSegments")
	private String toCCIDSegments;

	private String costCode;

	private String accountingSubject;

	private String newFixAssetCode;

	private String lineProcessStatus;

	private String errMsg;
}

