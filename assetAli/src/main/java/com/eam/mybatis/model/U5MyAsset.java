package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName U5MyAsset
 *@Description 我的资产列表信息
 *@Author jason
 *@Date 2018/8/21 上午11:16
 *@Version 1.0
 **************************************/
@Data
public class U5MyAsset {
	private String assetCode;//大阿里编号 obj_code
	private String serialNo;//序列号 obj_serialno
	private String assetName;//名称  根据代码取描述
	private String featureDesc;//obj_udfchar05 品牌型号--根据代码取描述
	private String ownerDesc;//obj_udfchar12  使用人，根据代码取描述
	private String ownerNo;//obj_udfchar12
	//	@JsonProperty(value = "faNo")
	private String fixAssetNo;//obj_udfchar01 固定资产编号
	private String sendTime;//obj_commiss  使用日期
	private String usedTime;//已使用(月)
	private String ifScrap; //是否达到报废年限
	private String usage;//使用说明udfchar07
	private String usageDesc; //使用说明描述
	private String assetTypeDesc;//资产异动说明  //这个跟state一致。暂不处理了
	private String instanceId;//资产异动任务单对应员工侧单据号
	//---------以下是明细字段所需要的-----------------
	private String brand;//品牌描述
	private String asset;//资产类别描述 obj_category
	private String configChange;//配置变更信息 obj_udfchar16
	private String depNo;//归口管理部门 obj_udfchar14
	private String depName;//归口管理部门描述
	private String acceptDesc;//账簿obj_udfchar29
	private String locationCode;//存放地点 obj_udfchar15
	private String locationDesc;//存放地点 obj_udfchar15 取描述
	private String locationNote;//详细位置信息obj_udfchar27
	private String model;//型号 obj_udfchar05取描述
	private String userNo;//obj_person desc
	private String userDesc;//obj_person desc
	private String orientation;//新增类型 obj_udfchar04
	private String orientationDesc; //新增类型 描述
	private String originalConfig;//obj_udfchar17;
	private String ouCode;//所属OU  obj_udfchar03
	private String partCode;//物料编码 obj_part
	private String partDesc;//物料描述
	private String poNumber;//PO号	obj_udfchar10
	private String acceptNumber;//采购发货单 obj_udfchar11
	private String assetStatus;//状态 取描述
	private String storeCode;//存放仓库
	private String storeDesc;//存放仓库描述
	private String supplierName;//供应商 objudfchar06
	private String manageType;//管理类别
	private String state;//动态(流程+链接)
	private String borrowStartDate;//借用开始时间
	private String borrowEndDate;//借用结束时间
	private String updatedConfig;//配置变更信息
	private String corp;//组织
	private String eamTaskId;//对应流程功单号
	//add By Jason 2018-Sep-18 借用次数
	private String reBorrowCount;
	private String borrowType; //借用类型
	private String ifSecure; //是否需要安全处理
	private String ifSecureDisposed; //是否已安全处理
	private String NetValue;
	private String oldAssetCode; //原资产编号
}
