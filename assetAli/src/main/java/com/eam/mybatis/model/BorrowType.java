package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName BorrowType
 *@Description  借用类型
 *@Author jason
 *@Date 2018/8/24 下午6:44
 *@Version 1.0
 **************************************/
@Data
public class BorrowType {
	private String borrowType;
	private String borrowDesc;
	private String borrowDuration;
}
