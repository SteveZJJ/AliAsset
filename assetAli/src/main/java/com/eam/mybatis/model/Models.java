package com.eam.mybatis.model;

import lombok.Data;

@Data
public class Models {

    private String mod_code;
    private String mod_desc;
    private String mod_brand;
    private String mod_branddesc;
    private String mod_part;
    private String mod_parddesc_zh;
    private String mod_partdesc_en;
    private String mod_nameid;
    private String mod_name;
    private String mod_name_en;

    public Models(String mod_code, String mod_desc, String mod_brand, String mod_branddesc, String mod_part, String mod_parddesc_zh, String mod_partdesc_en, String mod_nameid, String mod_name, String mod_name_en) {
        this.mod_code = mod_code;
        this.mod_desc = mod_desc;
        this.mod_brand = mod_brand;
        this.mod_branddesc = mod_branddesc;
        this.mod_part = mod_part;
        this.mod_parddesc_zh = mod_parddesc_zh;
        this.mod_partdesc_en = mod_partdesc_en;
        this.mod_nameid = mod_nameid;
        this.mod_name = mod_name;
        this.mod_name_en = mod_name_en;
    }

    public Models(String mod_code, String mod_brand, String mod_part) {
        this.mod_code = mod_code;
        this.mod_brand = mod_brand;
        this.mod_part = mod_part;
    }
}
