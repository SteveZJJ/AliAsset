package com.eam.mybatis.model;

import lombok.Data;

@Data
public class BatchAsset {

    private int sequenceNo;

    private String assetCode;
    private String evtCode;
    private String result;
    private String errMsg;

    private String corp;
    private String oldAssetCode; //原实物管理编码
    private String assetDesc; //实物名称
    private String brand; //品牌ID
    private String brandName;
    private String model;
    private String modelName;
    private String assetStatus;
    private String assetStatusDesc;
    private String partCode;
    private String partDesc;
    private String objClass;
    private String objCate;
    private String serialNo;
    private String currencyCode;
    private String value;
    private String depNo;
    private String respCode;
    private String respName;
    private String userCode;
    private String userName;
    private String locationCode;
    private String locationDesc;
    private String locationNote;
    private String storeCode;
    private String storeName;

    //默认
    private String bin;
    private String lot;

    private String usageCode;
    private String usageDesc;
    private String borrowAble;
    private String taxInclusive;

    private String PONumber;
    private String DONumber;
    private String lineID;

    private String orientationCode;
    private String orientationDesc;
    private String ownerShipCode;
    private String ownerShipDesc;

    private String acceptDate;
    private String faSystem;
    private String OUCode;
    private String faAssetCode;
    private String faLocationCode;
    private Long expenseCcid;

    private String targetSyncDate; //目标同步时间
    private String ifDelay; //是否延时同步到财务标识

    private String ifSync; //是否同步到财务


}
