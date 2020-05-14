package com.eam.mybatis.model;

/**************************************
 *@ClassName U5AssetDetails
 *@description 获取资产信息接口
 *@Author jason
 *@Date 2018/8/4 下午4:14
 *@Version 1.0
 **************************************/

import lombok.Data;
@Data
public class U5AssetDetails {
	
	private String assetCode;//OBJ_CODE大阿里编号
	private String assetName;//OBJ_DESC物品名称
	private String acceptDate;//OBJ_COMMISS启用日期
	private String locationCode;//OBJ_LOCATION存放地点代码
	private String model;//OBJ_MANUFACTMODEL型号
	private String modelName; //型号描述
	private String serialNo;//OBJ_SERIALNOSN
	private String assetStatus;//OBJ_STATUS状态
	private String corp;//OBJ_ORG组织（CORP）
	private String mrc;//OBJ_MRC部门
	private String costCode;//OBJ_COSTCODE成本中心
	private String fixedAssetNo; //OBJ_UDFCHAR01固定资产编号
	private String asset;//OBJ_UDFCHAR02资产类别
	private String ouCode;//OBJ_UDFCHAR03所属OU
	private String orientation;//OBJ_UDFCHAR04新增类型
	private String brand;//OBJ_UDFCHAR05品牌
	private String brandName; //品牌描述
	private String instruction; //OBJ_UDFCHAR07使用说明
	private String poNumber;//OBJ_UDFCHAR10PO号
	private String userNo;//OBJ_UDFCHAR12责任人ID
	private String personNo;//OBJ_UDFCHAR13使用人ID  EAM系统没有
	private String depNo;//OBJ_UDFCHAR14归口管理部门ID
	private String acceptDesc;//OBJ_UDFCHAR29账簿
	private String currencyCode;//OBJ_UDFCHAR30原币币种 EAM系统没有
	private String currencyMoney;//OBJ_UDFNUM01原币金额 EAM系统没有
	private String tax;//OBJ_UDFNUM02汇率 EAM系统没有
	private String unitPrice;//OBJ_VALUE单价
	private String faCategoryCode;//OBJ_UDFCHAR31财务资产类别
	private String taxInclusive;//OBJ_UDFCHKBOX02是否含税
	private String quantity;//'1' 资产数量 EAM 默认1
	private String uom;//OBJ_UDFCHAR32计量单位 EAM 系统没有
	private String storeDesc;//仓库描述
	private String faCategoryId; //财务类目Id
	private String locationDesc; //位置描述
	private String usageNote; //使用说明
	private String usageDesc; //使用说明描述
	private String central; //归口
	private String FALocation;// FA地点映射
	private String locationNote;// 地点详细备注


	//尾差及汇率税率新增字段
	private String standardCoin; //本位币币种
	private String withTaxPrice; //含税单价
	private String noTaxPrice; //不含税单价
	private String rate; //执行汇率
	private String calDate; //计算日期

	//2019年12月17日18:02:28验收优化新增字段
	private String FALocationNew;
	private String ifSync;
	private String ifDelay;
	private String targetSyncDate;
	private String actualSyncTime;

	public U5AssetDetails() {
	}


	@Override
	public String toString(){
		return "U5AssetDetails{" +
					   "assetCode='" + assetCode + '\'' +
					   ", assetName='" + assetName + '\'' +
					   ", acceptDate='" + acceptDate + '\'' +
					   ", locationCode='" + locationCode + '\'' +
					   ", model='" + model + '\'' +
					   ", serialNo='" + serialNo + '\'' +
					   ", assetStatus='" + assetStatus + '\'' +
					   ", corp='" + corp + '\'' +
					   ", mrc='" + mrc + '\'' +
					   ", costCode='" + costCode + '\'' +
					   ", asset='" + asset + '\'' +
					   ", ouCode='" + ouCode + '\'' +
					   ", orientation='" + orientation + '\'' +
					   ", brand='" + brand + '\'' +
					   ", poNumber='" + poNumber + '\'' +
					   ", userNo='" + userNo + '\'' +
					   ", personNo='" + personNo + '\'' +
					   ", depNo='" + depNo + '\'' +
				       ", instruction='" + instruction + '\'' +
					   ", acceptDesc='" + acceptDesc + '\'' +
					   ", currencyCode='" + currencyCode + '\'' +
					   ", currencyMoney='" + currencyMoney + '\'' +
					   ", tax='" + tax + '\'' +
					   ", unitPrice='" + unitPrice + '\'' +
					   ", faCategoryCode='" + faCategoryCode + '\'' +
					   ", taxInclusive='" + taxInclusive + '\'' +
					   ", quantity='" + quantity + '\'' +
					   ", uom='" + uom + '\'' +
					   ", storeDesc='" + storeDesc + '\'' +
					   '}';
	}


}
