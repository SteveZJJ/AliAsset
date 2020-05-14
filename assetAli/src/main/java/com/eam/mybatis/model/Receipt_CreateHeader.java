package com.eam.mybatis.model;

import lombok.Data;

/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月23日 下午3:12:53
 * @version 1.0
 */
@Data
public class Receipt_CreateHeader {
	private String corp;//组织
	private String supplierName;//供应商描述
	private String ouCode;//OU
	private String poNumber;//PO号
	private String acceptNumber;//发运单号
	private String workNo;//验收人
	private String language;//语言
	private String acceptanceLineId;//AMP 发运行ID,盒马 发运行行ID
	private String supplierCode;//供应商描述
	private String acceptanceLineOfHEMA;//盒马 发运行ID

	//尾差及税率新增字段
	private String unitPriceTaxIncluded; //含税单价
	private String taxRate; //税率
	private String amountTaxIncluded; //含税总额
	private String orderType; // 创建类型
	
	private String flag;//接口出来标识
	private String message;//错误信息
	private String receiptNo;//验收单号

	//延时入账及验收效率优化新增字段
	private String ifDelay;
	private String SyncTime;
	private String auditResult;
	private Long personId;
	private Long expenseCcid;
	private String rcvLineId;
	private String rcvNumber;

	//BU资产管理新增字段
	private String department; //预算部门
	private String central;

}
