package com.eam.mybatis.model;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月23日 下午3:13:07
 * @version 1.0
 */

import lombok.Data;

@Data
public class Receipt_CreateLine {
	private String receiptNo;// 验收单号
	private String categoryCode;// 采购类目
	private String acceptanceLineId;// 发运行ID
	private String acceptDate;// 启用日期
	private String corp;// 组织
	private String ouCode;// 所属OU
	private String orientation;// 新增类型
	private String poNumber;// PO号
	private String acceptNumber;// 采购发货单
	private String originalConfig;
	private String acceptDesc;// 账簿
	private String currencyCode;// 原币币种
	private double unitPrice;// 单价
	private String faCategoryCode;// 财务资产类别
	private String taxInclusive;//是否含税
	private int quantity; //数量
	private String workNo;// 验收人
	private String language;


	
	private String flag;
	private String message;
	
	//add by Jason For 归口管理部门  2018-08-28
	private String depNo;
	//add by Jason For  盒马 发运行ID  2018-08-28
	private String acceptanceLineOfHEMA;  //盒马发运行ID
	
	
}
