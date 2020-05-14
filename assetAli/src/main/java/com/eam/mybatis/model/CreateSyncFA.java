package com.eam.mybatis.model;


import lombok.Data;

/**
 * @author Steve Zhang
 * @Date 2019年12月5日19:18:22
 * @Description 延时同步FA参数列表
 */

@Data
public class CreateSyncFA {

    private String receiptNo;
    private String assetCode;
    private String corp;

    private String acceptanceLineID;
    private String acceptanceLineLineID;
    private String PONumber;
    private String DONumber;

    private String ifDelay;
    private String SyncTime;
    private String auditResult;
    private Long personId;
    private Long expenseCcid;
    private String rcvLineId;
    private String rcvNumber;

    private String sqlIdentity;
}
