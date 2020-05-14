package com.eam.mybatis.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/*
 * @author  Steve.Zhang
 * @date 2019年8月6日14:54:45
 * @version 1.0
 */
@Data
public class R5PropertyValues implements Cloneable {
	private String prv_property;
	private String prv_rentity;
	private String prv_class;
	private String prv_code;
	private String prv_desc;
	private String prv_value;
	private String prv_nvalue;
	private String prv_dvalue;
	private String prv_class_org;
	private String prv_updatecount;
	private String prv_created;
	private String prv_updated;
	private String obj_code;

	@Override
	public Object clone() throws CloneNotSupportedException{
		R5PropertyValues pv = (R5PropertyValues) super.clone();
		return pv;
	}
}
