package com.eam.mybatis.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 *
 */

@Data
public class BatchReceiptHeader {

    private String receiptNo;
    private String corp;
    private String personCode;
    private String personName;
    private String PO;
    private String sourceType;
    private String sourceTypeDesc;
    @JsonProperty(value = "quantity")
    private int number;
    private String evtStatus;
    private String evtStatusDesc;
    private String supplierCode;
    private String supplierName;
    private String comment;
    private List<Attachment> attachments;//文档清单
    private Attachment dataFile;
    private Attachment bpmsUrl;


    private String ifDelay;
    private String syncTime;
    private String auditResult;
    private Long personId;
    private Long expenseCcid;
    private String rcvLineId;
    private String rcvNumber;
    private String targetSyncDate;

    private String flag;
    private String message;

    private String submitter;
    private String submitterName;
    private String submitDate;
    private String createDate;

    private String evtDesc;
    private String corpDesc;
    private String jobType;
    private String jobTypeDesc;

    private String ifErr;
    private String ifExcelErr;
    private String excelErrMsg;
}
