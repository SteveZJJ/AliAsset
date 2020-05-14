package com.eam.mybatis.model;

import lombok.Data;

/*
 * @author  Steve Zhang
 * @date 创建时间：2019年1月31日11:06:04
 * @version 1.0
 */
@Data
public class U5PropertyResult {
	private String prv_property;
	private String prv_code;
	private String prv_desc;
	private String prv_value;


	private String obj_code;
	private String pvp_result;
	private String pvp_message;
}
