package com.eam.mybatis.model;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月23日 下午5:44:37
 * @data 更新时间：2019年1月26日03:02:37  by Steve
 * @version 1.0
 */

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class R5Mrcs {

    @JsonProperty(value = "depCode")
    private String mrc_code;
    @JsonProperty(value = "depDesc")
    private String mrc_desc;
    @JsonProperty(value = "depClass")
    private String mrc_class;
    @JsonProperty(value = "depParent")
    private String mrc_parent;
    @JsonProperty(value = "depFullDesc")
    private String mrc_fulldesc;
    @JsonProperty(value = "depNotused")
    private String mrc_notused;
    @JsonProperty(value = "depDescEn")
    private String mrc_descen;
}
