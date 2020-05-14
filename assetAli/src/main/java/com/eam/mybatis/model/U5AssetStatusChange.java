package com.eam.mybatis.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

/**************************************
 *@ClassName U5AssetStatusChange
 *@Description   //状态发生变化实体类
 *@Author jason
 *@Date 2018/10/16 - 15:01
 *@Version 1.0
 **************************************/
@Data
public class U5AssetStatusChange {
	private String transId;//交易号
	private String assetCode; //资产编号OMH_OBJECT
	private String fixedAssetCode; //固定资产编号
	private String currentWorkNo; //原责任人工号
	@JsonProperty(value = "personInChange")
	private String updateWorkNo; //现责任人工号
	private String currentStatus; //原状态Code
	@JsonProperty(value = "assetStatus")
	private String updateStatus;  //现状态Code
	private String currentDepmentId; //原部门Code
	@JsonProperty(value = "departmentId")
	private String updateDepmentId; //现部门Code
	private String currentLocationCode; //原位置Code
	private String currentFALocation;
	@JsonProperty(value = "locationId")
	private String updateLocationCode; //现位置Code
	private String updateFALocation;
	private String updateBy; //操作人
	private String updateDate; //更新时间
	@JsonProperty(value = "faCateId")
	private String faCateCode; //财务类目Code
	@JsonProperty(value = "orgId")
	private String corp; //组织Code
	private String ouCode; //ou代码
	@JsonProperty(value = "modeDesc")
	private String model; //型号
	@JsonProperty(value = "assetDesc")
	private String objDesc; //资产描述
	private String currCode; //币种
	@JsonProperty(value = "origValue")
	private String objValue; //单价
	private String estiMonth; //折旧月份



	private String language;
	private String type;

}
