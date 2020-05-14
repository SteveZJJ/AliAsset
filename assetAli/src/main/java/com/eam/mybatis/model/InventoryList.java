package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

@Data
public class InventoryList {
    private String respWorkNo;
    private String respWorkName;
    private String evtCode; //进行盘点反馈时必填
    private String corp;
    private List<InventoryLine> assetList;
}
