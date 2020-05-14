package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName Attechment
 *@Description   //文件附件
 *@Author jason
 *@Date 2018/8/25 - 12:04
 *@Version 1.0
 **************************************/
@Data
public class Attachment {
	private String line;//附件序号
	private String name;//附件名称
	private String url;//附件Url路径
	private String bidLine; //竞价附件行号
	private String eventCode;
}
