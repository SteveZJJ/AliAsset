package com.eam.mybatis.model;

import lombok.Data;

@Data
public class LocationOU {
    private String locationCode;
    private String locationDesc;
    private String ouCode;
    private String ouDesc;
    private String regionalSection;
    private String regionalSectionDesc;
    private String costCode;
    private String isDefault;
}
