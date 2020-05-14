package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName TodoWorkFlowDetail
 *@Description   //待办工作流明细
 *@Author jason
 *@Date 2018/8/30 - 15:35
 *@Version 1.0
 **************************************/
@Data
public class TodoWorkFlowDetail {
	private String assetCode;//大阿里编号
	private String fixedAssetNo;//固定资产编码
	private String assetName;//名称
	private String brandCode;//品牌代码
	private String brandName;//品牌名称
	private String modelCode;//型号
	private String modelName;//型号
	
	public TodoWorkFlowDetail(String assetCode,String fixAssetNo,String assetName,String brandCode,String brandName,String modelName){
		this.assetCode = assetCode;
		this.fixedAssetNo = fixAssetNo;
		this.assetName = assetName;
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.modelName = modelName;
	}
}
