package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName R5Locations
 *@Description 地点信息，可分级搜索查看
 *@Author jason
 *@Date 2018/8/24 下午5:54
 *@Version 1.0
 **************************************/
@Data
public class R5Locations {
	
	private String locationCode;//obj_code
	private String locationDesc;//obj_desc
	private String locationClass;//obj_class
}
