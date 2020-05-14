package com.eam.mybatis.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月23日 下午1:51:40
 * @version 1.0
 */
//验收单行信息
@Data
public class ReceiptItem {
	
	@JsonProperty(value = "receiptNo")
	private String evtCode;// 每一行待验收资产  EVT_CODE
	private String assetCode;// 大阿里资产编号  EVT_OBJECT
	private String categoryCode;// 采购类目 EVT_UDFCHAR01
	private String categoryName;
	private String assetDesc;
	private String duration;
	private String transDate;
	private String dueDate;
	
	
}
