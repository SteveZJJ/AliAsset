package com.eam.mybatis.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月23日 下午12:41:25
 * @version 1.0
 */
@Data
public class ReceiptDetails {
	private String orgCode;// 组织代码
	@JsonProperty(value = "corp")
	private String orgDesc;// 组织描述
	private String receiptNo;// 验收单号
	private String department;// 使用部门
	@JsonProperty(value = "depNo")
	private String departmentDesc;// 使用部门描述
	private String supplierName;// 供应商
	private String supplierCode;// 供应商
	@JsonProperty(value = "ouCode")
	private String OU;// OU
	private String buyer;// 采购员
	@JsonProperty(value = "poNumber")
	private String PO;// PO号
	@JsonProperty(value = "acceptNumber")
	private String acceptNumber;// 发运单号
	private String workNo;// 验收人用户
	
	private String storeCode;//仓库ID EVT_UDFCHAR16
	private String partCode;//物料ID ,EVT_UDFCHAR18
	private String partDesc;
	private String createDate;//验收单创建日期， evt_created
	private String acceptanceLineId;
	private String acceptanceLineOfHEMA;
	private String ifManual;
	private String status;
	private String statusDesc;
	private List <ReceiptItem> item;
	private List<String> TDNCodes;

	private String unitPriceTaxIncluded;
	private String taxRate;
	private String amountTaxIncluded;

	//手工新增审批新增字段
	private String assetSource;
	private String assetSourceDescription;

	//延时入账及验收效率优化新增字段
	private String ifDelay;
	//private String ifSync;
	private String SyncTime;
	private String auditResult;
	private Long personId;
	private Long expenseCcid;
	private String rcvLineId;
	private String rcvNumber;
	private String targetSyncDate;
	private String actualSyncTime;
	
}
