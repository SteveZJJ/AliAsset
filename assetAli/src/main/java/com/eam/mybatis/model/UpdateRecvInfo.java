package com.eam.mybatis.model;

import lombok.Data;

@Data
public class UpdateRecvInfo {
    private String receiptNo;
    private String storeCode;
    private String partCode;
    private CategoryMapping mapping;
}
