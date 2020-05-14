package com.eam.mybatis.model;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月19日 上午11:40:01
 * @version 1.0
 */

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Brands {

    @JsonProperty(value = "asset")
    private String catalogue;
    @JsonProperty(value = "brand")
    private List<String> brands;
    //2018-Jul-31  增加name 默认 品牌
    private String name;
    public Brands(String catalogue, List<String> brands) {
        super();
        this.catalogue = catalogue;
        this.brands = brands;
        this.name="品牌";
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
