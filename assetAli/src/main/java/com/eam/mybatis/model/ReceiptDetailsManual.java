package com.eam.mybatis.model;

import lombok.Data;

/**
 * @author Steve Zhang
 * @date 2018-8-27 10:32:28
 * @version 1.0
 */
@Data
public class ReceiptDetailsManual {
    private String receiptNo; //验收单号 EVT_COD
    private String locId; //存放地点ID EVT_OBJECT
    private String workNo; //验收人 EVT_ORIGIN
    private String ifStore; //是否存放到库房 EVT_UDFCHAR12
    private String usageNote; //使用说明 EVT_UDFCHAR07 (80),--OBJ_UDFCHAR07使用说明
    private String newType; //新增类型 EVT_UDFCHAR08
    private String ownerShip; //所有权 EVT_UDFCHAR09
    private String poNumber; //po号 EVT_UDFCHAR10 (80)--OBJ_UDFCHAR10PO号
    private String locDetail; //详细位置备注 EVT_UDFCHAR11
    private String storeId; //存放仓库ID EVT_UDFCHAR16 (30),--OBJ_STORE存放仓库ID
    private String model;// (30),--OBJ_MANUFACTMODEL 型号
    private String partCode;// (30),--OBJ_PART物资编码
    private String serialNo;// (30),--OBJ_SERIALNO序列号
    private String corp;// (30),--OBJ_ORG
    private String brand;// (30),--OBJ_UDFCHAR05品牌
    private String supplierName;// (30),--OBJ_UDFCHAR06供应商
    private String respNo;// (80),--OBJ_UDFCHAR12责任人ID
    private String userNo;// (80),--OBJ_UDFCHAR13使用人ID
    private String depNo;// (80),--OBJ_UDFCHAR14归口管理部门ID
    private String configChange;// (80),--OBJ_UDFCHAR16配置变更信息
    private String locationNote;// (80)--OBJ_UDFCHAR27详细位置备注
    private String ouCode;// (80)--OBJ_UDFCHAR03所属OU
    private String currencyCode; // (30)--OBJ_UDFCHAR30原币币种
    private String taxInclusive;// (30)--OBJ_UDFCHKBOX02是否含税
    private String unitPrice; // (30)--OBJ_VALUE单价
    private String acceptDate; // OBJ_COMMISS启用日期

    //税率汇率及尾差新增字段

    private String unitPriceTaxIncluded;  //含税单价
    private String taxRate; //税率
    private String amountTaxIncluded; //含税总价

    private String standardCoin; //本位币币种
    private String withTaxPrice; //含税单价
    private String noTaxPrice; //不含税单价
    private String rate; //执行汇率
    private String calDate; //计算时间


    //审批新增字段
    private String assetSource;
    private String assetSourceDescription;

    //2019年11月27日18:38:36 延时入库及验收效率优化字段
    private String ifDelay;
    private String SyncTime;
    private String auditResult;
    private Long personId;
    private Long expenseCcid;
    private String rcvLineId;
    private String rcvNumber;
    private String targetSyncDate;
    private String actualSyncTime;
    private String ifSync;

}
