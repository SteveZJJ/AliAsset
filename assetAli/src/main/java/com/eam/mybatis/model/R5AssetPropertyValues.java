package com.eam.mybatis.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/*
 * @author  Steve Zhang
 * @date 创建时间：2019年1月17日15:13:23
 * @version 1.0
 */
@Data
public class R5AssetPropertyValues {
	private String objCode;//
	@JsonProperty(value = "corp")
	private String corp;// 组织描述
	private List<R5PropertyValues> propertyList;
	private String receiptNo;// 验收单号
}
