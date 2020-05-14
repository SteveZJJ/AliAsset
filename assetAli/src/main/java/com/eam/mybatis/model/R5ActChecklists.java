package com.eam.mybatis.model;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月24日 上午10:45:18
 * @version 1.0
 */

public class R5ActChecklists {
    private String serialNo;// --ack_sequence 序号
    private String description;// --验收项目描述
    // dbo.r5o7_o7get_desc(lang,'TCLI',ACK_CODE,'','')
    private String propertyType;// 属性列字段类型
    // dbo.r5o7_o7get_desc(lang,'UOM',ACK_UOM,'','')
    private String isNeed;// 是否必填 ACK_REQUIREDTOCLOSE +/-
    private String checkType;// ACK_TYPE 验收项目类型
    private String checkPointType;// ACK_POINTTYPE 约定值 GENERAL
    // 表示该验收项为公用，验收单头部统一填写。
    private String propertyTypeCode;// ack_uom
    private String receiptNo;//ACK_EVENT 验收单号
    private String actNum;//ACK_ACT 验收单活动号
    private String assetCode;//ACK_OBJECT 资产编号
    private String checkValue;//ACK_VALUE 验收项值
    private String remark;//ACK_NOTES 备注
    private String checked;//ACK_COMPLETED 已检查
    private String checkedYes;//ACK_YES Yes
    private String checkedNo;//ACK_NO No
    private String checkResult;//ACK_FINDING 验收结果（定性）
    private String workNo;//ACK_UPDATEDBY 更新人
    private String language;//语言

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getActNum() {
        return actNum;
    }

    public void setActNum(String actNum) {
        this.actNum = actNum;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(String checkValue) {
        this.checkValue = checkValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getCheckedYes() {
        return checkedYes;
    }

    public void setCheckedYes(String checkedYes) {
        this.checkedYes = checkedYes;
    }

    public String getCheckedNo() {
        return checkedNo;
    }

    public void setCheckedNo(String checkedNo) {
        this.checkedNo = checkedNo;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSerialNo() {
        return serialNo;
    }

//	public R5ActChecklists(String serialNo, String description, String propertyType,
//											String isNeed, String checkType, String checkPointType) {
//										super();
//										this.serialNo = serialNo;
//										this.description = description;
//										this.propertyType = propertyType;
//										this.isNeed = isNeed;
//										this.checkType = checkType;
//										this.checkPointType = checkPointType;
//									}

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getIsNeed() {
        return isNeed;
    }

    public void setIsNeed(String isNeed) {
        this.isNeed = isNeed;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckPointType() {
        return checkPointType;
    }

    public void setCheckPointType(String checkPointType) {
        this.checkPointType = checkPointType;
    }

    @Override
    public String toString() {
        return "R5ActChecklists [serialNo=" + serialNo + ", description=" + description + ", propertyType="
                + propertyType + ", isNeed=" + isNeed + ", checkType=" + checkType + ", checkPointType="
                + checkPointType + ", propertyTypeCode=" + propertyTypeCode + ", receiptNo=" + receiptNo + ", actNum="
                + actNum + ", assetCode=" + assetCode + ", checkValue=" + checkValue + ", remark=" + remark
                + ", checked=" + checked + ", checkedYes=" + checkedYes + ", checkedNo=" + checkedNo + ", checkResult="
                + checkResult + ", workNo=" + workNo + ", language=" + language + "]";
    }

    public String getPropertyTypeCode() {
        return propertyTypeCode;
    }

    public void setPropertyTypeCode(String propertyTypeCode) {
        this.propertyTypeCode = propertyTypeCode;
    }

}
