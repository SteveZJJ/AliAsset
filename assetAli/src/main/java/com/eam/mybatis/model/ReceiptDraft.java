package com.eam.mybatis.model;

/**
 * @Author Steve Zhang
 * @date 2018-8-31 10:38:24
 * @Description 验收单草稿
 */
public class ReceiptDraft {

    private String receiptType; //创建类型 EVT_UDFCHKBOX3  如果是"+"则代表是手动新增
    private String receiptNo; //验收单号 EVT_CODE
    private String receiptDesc; //验收单说明 EVT_DESC
    private String enteredBy; //录入人用户
    private String partCode; //物料ID EVT_UDFCHAR18
    private String partDesc; //物料描述
    //private String jobRType; //工单类型系统代码 EVT_JOBRTYPE
    private String jobType; //工单类型系统代码 EVT_JOBTYPE
    private String depNo; //使用部门 EVT_MRC
    private String workNo; //验收人 EVT_ORIGIN
    private String workDesc; //验收人描述
    private String ifStore; //是否入库 EVT_UDFCHAR12
    private String number; //数量 EVT_UDFNUM01
    private String createDate; // 创建日期 EVT_CREATED

    //private String assetCode; //大阿里编号 OBJ_CODE
    private String model; //型号 OBJ_MANUFACTIMODEL
    private String modelDesc; //型号描述
    //private String serialNo; //SN OBJ_SERIALNO
    private String storeCode; //仓库ID OBJ_STORE
    private String storeDesc; //仓库描述
    private String corp; //组织 OBJ_ORG
    private String ouCode; //所属OU OBJ_UDFCHAR03
    private String brand; //品牌 OBJ_UDFCHAR05
    private String brandDesc; //品牌描述
    //private String supplierCode; //供应商 OBJ_UDFCHAR06
    private String usageNote; //使用说明 OBJ_UDFCHAR07
    private String usageDesc; //使用说明描述
    private String poNumber; //po号 OBJ_UDFCHAR10
    private String respNo; //责任人ID OBJ_UDFCHAR12
    private String respDesc; //责任人描述
    private String locId; //存放地点ID OBJ_UDFCHAR15
    private String locDesc; //地点描述
    private String currencyCode; //货币 OBJ_UDFCHAR30 原币币种
    private String taxInclusive;// (30)--OBJ_UDFCHKBOX02是否含税
    private String unitPrice; // (30)--OBJ_VALUE单价
    private String acceptDate;// (50)--OBJ_COMMISS 启用日期
    private String locationNote; //--EVT_UDFCHAR11 详细位置备注
    private String ownerShip; // --EVT_UDFCHAR21 所有权
    private String ownerShipDesc; // 所有权描述
    private String newType; // --OBJ_UDFCHAR04 新增类型
    private String newTypeDesc;//新增类型描述


    //税率汇率及尾差新增字段

    private String unitPriceTaxIncluded;  //含税单价
    private String taxRate; //税率
    private String amountTaxIncluded; //含税总价

    private String standardCoin; //本位币币种
    private String withTaxPrice; //含税单价
    private String noTaxPrice; //不含税单价
    private String rate; //执行汇率
    private String calDate; //计算时间

    //手工新增审批新增字段
    private String assetSource;
    private String assetSourceDescription;


    private String[] assetCode; //大阿里编号集合 OBJ_CODE


    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getStoreDesc() {
        return storeDesc;
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getLocDesc() {
        return locDesc;
    }

    public void setLocDesc(String locDesc) {
        this.locDesc = locDesc;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getReceiptDesc() {
        return receiptDesc;
    }

    public void setReceiptDesc(String receiptDesc) {
        this.receiptDesc = receiptDesc;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

//    public String getJobRType() {
//        return jobRType;
//    }
//
//    public void setJobRType(String jobRType) {
//        this.jobRType = jobRType;
//    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getIfStore() {
        return ifStore;
    }

    public void setIfStore(String ifStore) {
        this.ifStore = ifStore;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
//
//    public String getAssetCode() {
//        return assetCode;
//    }
//
//    public void setAssetCode(String assetCode) {
//        this.assetCode = assetCode;
//    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

//    public String getSerialNo() {
//        return serialNo;
//    }
//
//    public void setSerialNo(String serialNo) {
//        this.serialNo = serialNo;
//    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getOuCode() {
        return ouCode;
    }

    public void setOuCode(String ouCode) {
        this.ouCode = ouCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

//    public String getSupplierCode() {
//        return supplierCode;
//    }
//
//    public void setSupplierCode(String supplierCode) {
//        this.supplierCode = supplierCode;
//    }

    public String getUsageNote() {
        return usageNote;
    }

    public void setUsageNote(String usageNote) {
        this.usageNote = usageNote;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getRespNo() {
        return respNo;
    }

    public void setRespNo(String respNo) {
        this.respNo = respNo;
    }

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTaxInclusive() {
        return taxInclusive;
    }

    public void setTaxInclusive(String taxInclusive) {
        this.taxInclusive = taxInclusive;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String[] getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String[] assetCode) {
        this.assetCode = assetCode;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLocationNote() {
        return locationNote;
    }

    public void setLocationNote(String locationNote) {
        this.locationNote = locationNote;
    }

    public String getOwnerShip() {
        return ownerShip;
    }

    public void setOwnerShip(String ownerShip) {
        this.ownerShip = ownerShip;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }

    public String getUnitPriceTaxIncluded() {
        return unitPriceTaxIncluded;
    }

    public void setUnitPriceTaxIncluded(String unitPriceTaxIncluded) {
        this.unitPriceTaxIncluded = unitPriceTaxIncluded;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getAmountTaxIncluded() {
        return amountTaxIncluded;
    }

    public void setAmountTaxIncluded(String amountTaxIncluded) {
        this.amountTaxIncluded = amountTaxIncluded;
    }

    public String getStandardCoin() {
        return standardCoin;
    }

    public void setStandardCoin(String standardCoin) {
        this.standardCoin = standardCoin;
    }

    public String getWithTaxPrice() {
        return withTaxPrice;
    }

    public void setWithTaxPrice(String withTaxPrice) {
        this.withTaxPrice = withTaxPrice;
    }

    public String getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(String noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCalDate() {
        return calDate;
    }

    public void setCalDate(String calDate) {
        this.calDate = calDate;
    }

    public String getAssetSource() {
        return assetSource;
    }

    public void setAssetSource(String assetSource) {
        this.assetSource = assetSource;
    }

    public String getAssetSourceDescription() {
        return assetSourceDescription;
    }

    public void setAssetSourceDescription(String assetSourceDescription) {
        this.assetSourceDescription = assetSourceDescription;
    }

    public String getNewTypeDesc() {
        return newTypeDesc;
    }

    public void setNewTypeDesc(String newTypeDesc) {
        this.newTypeDesc = newTypeDesc;
    }

    public String getOwnerShipDesc() {
        return ownerShipDesc;
    }

    public void setOwnerShipDesc(String ownerShipDesc) {
        this.ownerShipDesc = ownerShipDesc;
    }

    public String getUsageDesc() {
        return usageDesc;
    }

    public void setUsageDesc(String usageDesc) {
        this.usageDesc = usageDesc;
    }
}
