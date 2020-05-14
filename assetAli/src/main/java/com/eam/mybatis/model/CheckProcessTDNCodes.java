package com.eam.mybatis.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CheckProcessTDNCodes {
    private ArrayList<String> TDNCodes; //大阿里编号List
    private String corp; //资产所属组织
    private String process; //用于流程
    private String language; //语言标识
    private String workNo; //员工
    private String depNo; //
    private String fromStore;
    private String toStore;
    private String fromLocation;
    private String toLocation;
    private String transferMode;
    private String fromOUCode;
    private String toOUCode;
    private int start; //分页起始
    private int limit; //分页结束

    //资产转移校验参数
    private String receiverCorp; //接收人组织
    private String receiverNo;  //接收人工号
}
