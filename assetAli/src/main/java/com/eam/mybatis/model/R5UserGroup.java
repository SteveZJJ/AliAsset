package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName R5UserGroup
 *@Description   //用户组信息
 *@Author jason
 *@Date 2018/9/5 - 10:17
 *@Version 1.0
 **************************************/
@Data
public class R5UserGroup {
	private String groupId;
	private String groupName;
	private String corp;//员工组织
	private String corpName;//员工组织
	private String isDefault; //是否为默认组织 +为是 -为否
}
