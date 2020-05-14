package com.eam.mybatis.model;
/*
 * @author  Steve Zhang
 * @date 创建时间：2019年1月25日19:12:32
 * @version 1.0
 */

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class R5Personnel {

    @JsonProperty(value = "perCode")
    private String per_code;
    @JsonProperty(value = "perDesc")
    private String per_desc;
    @JsonProperty(value = "perDept")
    private String per_mrc;
    @JsonProperty(value = "perNotUsed")
    private String per_notused;

}
