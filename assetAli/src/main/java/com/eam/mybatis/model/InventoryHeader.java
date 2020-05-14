package com.eam.mybatis.model;


import lombok.Data;

import java.util.List;

/**
 * @author  Steve Zhang
 * @Description  盘点单头
 * @Date  2019年12月20日16:17:39
 */

@Data
public class InventoryHeader {

    private String evtCode;
    private String evtDesc;
    private String corp;
    private String corpDesc;
    private String evtJobtype;
    private String evtJobtypeDesc;
    private String evtStatus;
    private String evtStatusDesc;
    private String evtCreateDate;
    private String comment;

    private String creatorCode;
    private String creatorName;
    private String responseCode;
    private String responseName;
    private String inventoryType;

    private String locationCode;
    private String locationDesc;
    private String storeCode;
    private String storeDesc;

    private String ownerShipCode;
    private String ownerShipDesc;
    private List<String> objStatus;
    private List<String> objStatusDesc;
    private List<String> objClass;
    private List<String> objClassDesc;
    private List<String> objCate;
    private List<String> objCateDesc;
    private String objValue;
    private String acceptDateStart;
    private String acceptDateEnd;
    private String faDateStart;
    private String faDateEnd;
    private String ifIncludeNonValue;

    //盘点时间节点记录
    private String inventoryTarget;
    private String inventoryStart;
    private String inventoryReport;
    private String inventoryEnd;

    private List<Attachment> attachments;//文档清单
    private Attachment dataFile;
    private String ifExcelErr;
    private String excelErrMsg;


}
