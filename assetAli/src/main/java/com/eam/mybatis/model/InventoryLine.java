package com.eam.mybatis.model;

import lombok.Data;

@Data
public class InventoryLine {
    private String evtCode;
    private String corp;
    private String assetCode;
    private String assetDesc;
    private String assetStatus;
    private String brandCode;
    private String brandDesc;
    private String modelCode;
    private String modelDesc;
    private String serialNo;
    private String respNo;
    private String respName;
    private String userNo;
    private String userName;
    private String locationCode;
    private String locationDesc;
    private String locationNote;
    private String storeCode;
    private String storeDesc;
    private String value;
    private String currency;
    private String ouCode;
    private String faAssetCode;
    private String acceptDate;
    private String created;
    private String updated;
    private String ifFeedBack;
    private String feedBackStatus;
    private String comment;
    private String feedBackWorkNo;
    private String feedBackType;
    private String feedBackDate;
    private long id;
}
