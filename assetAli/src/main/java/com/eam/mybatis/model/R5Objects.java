package com.eam.mybatis.model;

import lombok.Data;

@Data
public class R5Objects {
    private String assetCode;// (30),--OBJ_CODE 大阿里编号
    private String assetDesc; // (80), --OBJ_DESC 资产名称
    private String locationCode;// (30),--OBJ_LOCATION存放地点代码
    private String model;// (30),--OBJ_MANUFACTMODEL 型号
    private String modelName; //(80)
    private String partCode;// (30),--OBJ_PART物资编码
    private String serialNo;// (30),--OBJ_SERIALNO序列号
    private String storeCode;// (30),--OBJ_STORE存放仓库ID
    private String corp;// (30),--OBJ_ORG
    private String brand;// (30),--OBJ_UDFCHAR05品牌
    private String brandName; // (80)
    private String supplierName;// (30),--OBJ_UDFCHAR06供应商
    private String usageNote;// (80),--OBJ_UDFCHAR07使用说明
    private String userNo;// (80),--OBJ_UDFCHAR13使用人ID
    private String depNo;// (80),--OBJ_UDFCHAR14归口管理部门ID
    private String configChange;// (80),--OBJ_UDFCHAR16配置变更信息
    private String locationNote;// (80)--OBJ_UDFCHAR27详细位置备注
    private String ouCode;// (80)--OBJ_UDFCHAR03所属OU
    private String poNumber;// (80)--OBJ_UDFCHAR10PO号
    private String currencyCode; // (30)--OBJ_UDFCHAR30原币币种
    private String taxInclusive;// (30)--OBJ_UDFCHKBOX02是否含税
    private String unitPrice; // (30)--OBJ_VALUE单价

    //2018-12-27 10:52:02 尾差及汇率税率新增字段

    private String standardCoin; // 本位币币种
    private String withTaxPrice; // 含税单价
    private String noTaxPrice; // 不含税单价
    private String rate; // 执行汇率
    private String calDate; // 计算时间

    //2019年11月27日18:38:36 延时入库及验收效率优化字段
    private String targetSyncDate; //目标同步时间
    private String actualSyncTime; //实际同步时间
    private String ifSync; //是否成功同步财务
    private String ifDelay; //是否延时同步到财务标识


    @Override
    public String toString() {
        return "R5Objects [assetCode=" + assetCode + ", assetDesc=" + assetDesc + ", locationCode=" + locationCode + ", model=" + model
                + ", partCode=" + partCode + ", serialNo=" + serialNo + ", storeCode=" + storeCode + ", corp=" + corp
                + ", brand=" + brand + ", supplierName=" + supplierName + ", usageNote=" + usageNote + ", userNo="
                + userNo + ", depNo=" + depNo + ", configChange=" + configChange + ", locationNote=" + locationNote
                + "]";
    }

}
