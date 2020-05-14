package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

/**************************************
 *@ClassName R5Users
 *@Description   //员工信息
 *@Author jason
 *@Date 2018/9/5 - 10:31
 *@Version 1.0
 **************************************/
@Data
public class R5Users {
	private String userId;//员工号
	private String userName;//员工姓名
	private String userLang;//员工默认语言
	private List <R5UserGroup> groups;//用户组信息
	
	
	private String passWord;//密码
	private String notUsed;//+,-
}
