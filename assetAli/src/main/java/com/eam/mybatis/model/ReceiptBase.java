package com.eam.mybatis.model;

import org.codehaus.jackson.annotate.JsonProperty;

/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月18日 下午6:16:50
 * @version 1.0
 */

public class ReceiptBase {
    @JsonProperty(value = "receiptNo")
    private String receiptNo;
    private String receiptDesc;

    public ReceiptBase(String receiptNo, String receiptDesc) {
        super();
        this.receiptNo = receiptNo;
        this.receiptDesc = receiptDesc;
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

}
