package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

@Data
public class AssetCodeList {
    private List<String> codes;
    private String language;
}
