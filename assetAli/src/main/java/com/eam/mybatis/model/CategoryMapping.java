package com.eam.mybatis.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class CategoryMapping {

    private String assetCategory; //资产类目
    private String assetCategoryDesc; //资产类目名称
    private String purchaseCategory; //采购类目
    private String purchaseCategoryDesc; //采购类目名称
    private String purchaseUsage; //采购用途
    private String purchaseUsageDesc;  //采购用途名称
    @JsonProperty("faKeywords")
    private String faCategoryCode; //财务类目KeyWords
    private String faCategoryId; //财务类目Id
    private String faCategoryDesc; //财务类目名称
    private String depreciationMonth; //折旧年限（选填）
    private String corp; //组织标识

}
