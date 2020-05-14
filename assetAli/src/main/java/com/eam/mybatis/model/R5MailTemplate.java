package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName R5MailTemplate
 *@Description   //发送邮件邮件模板
 *@Author jason
 *@Date 2018/8/28 - 21:40
 *@Version 1.0
 **************************************/
@Data
public class R5MailTemplate {
	private String mat_subject;// 邮件模板subject
	private String mat_text;//邮件模板body
	private String mat_code;//邮件模板CODE
	private String mat_mail;//邮件模板收件人参数
}
