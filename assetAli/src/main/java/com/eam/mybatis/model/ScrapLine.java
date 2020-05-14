package com.eam.mybatis.model;

import lombok.Data;

import java.math.BigDecimal;

/**************************************
 *@ClassName ScrapLine
 *@Description 报废单详情行
 *@Author Steve
 *@Date 2019年5月24日16:48:10
 *@Version 2.0
 **************************************/
@Data
public class ScrapLine {
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
}

