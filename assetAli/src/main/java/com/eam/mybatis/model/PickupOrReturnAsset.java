package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName PickUpOrReturnAsset
 *@Description   //借用/续借的资产列表信息。
 *@Author jason
 *@Date 2018/8/28 - 15:28
 *@Version 1.0
 **************************************/
@Data
public class PickupOrReturnAsset {
	
	private String model;//常用型号ID
	private String modelName;//常用型号描述
	private String modelClass;//型号大类
	private String sequence;//排序
	private String maxQty;//最大领用数量
	private String assetCategory;//资产类目
	private String assetCategoryName;//资产类目描述
	private String modelCategory;//所属类别
	private String configuration;//配置信息
	private String qty;//可用数量
	private String origPicture1;//原图1
	private String origPicture2;//原图2
	private String compPicture1;//缩略图1
	private String compPicture2;//缩略图2
	//
	private String assetCode;//大阿里编号 OBJ_CODE
	private String fixedAssetNo;//固定资产编号 OBJ_UDFCHAR01
	private String assetName;//名称    OBJ_CODE描述
	private String brandCode;//品牌代码 OBJ_UDFCHARO5
	private String brandName;//品牌描述 OBJ_UDFCHAR05 描述
	private String modelCode;//型号代码 OBJ_MANUFACTMODEL
	//private String modelName;// 上文已经定义
	private String sn;//SN序列号 OBJ_SERIALNO
	private String acceptDate;//obj_comiss
	private String usedTime;// 上次借用或领用的发放日期到现在的月份
	private String usage;// 使用说明 OBJ_UDFCHAR07
	private String borrowType;// 借用类型
	private String borrowTypeName;// 借用类型描述
	private String assetStatus;//状态
}
