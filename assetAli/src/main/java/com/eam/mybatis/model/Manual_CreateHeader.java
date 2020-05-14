package com.eam.mybatis.model;


import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @Time 2018-8-22 21:04:51
 * @author Steve Zhang
 * @version 1.0
 */

@Data
public class Manual_CreateHeader {
    private String corp; //组织 EVT_ORG
    private String ouCode; //所属OU EVT_UDFCHAR08
    @JsonProperty(value = "assetCategory")
    private String partCode; //物料ID EVT_UDFCHAR18
    @JsonProperty(value = "quantity")
    private String number; //资产数量
    private String workNo; //验收人
    private String language; //语言
    private String flag;
    private String message;
    private String receiptNo; //验收单号（返回值） EVT_CODE

    //2019年11月27日18:43:36 延时入账及验收效率优化新增字段
    private String ifDelay;
    private String SyncTime;
    private String auditResult;
    private Long personId;
    private Long expenseCcid;
    private String rcvLineId;
    private String rcvNumber;
    private String targetSyncDate;

}
