package com.eam.mybatis.model;


import lombok.Data;

import java.util.ArrayList;

@Data
public class LocationInfo {

    private String locationCode;
    private String locationDesc;
    private String mappingLocation;
    private String locationClass;
    private String locationParent;
    private ArrayList<LocationOU> locationOUs;
    private String thirdSystem;
    private String areaAdmin; //区域管理员
    private String areaAdminDesc; //区域管理员描述
    private String areaFAAdmin; //区域财务
    private String areaFAAdminDesc; //区域财务姓名
}
