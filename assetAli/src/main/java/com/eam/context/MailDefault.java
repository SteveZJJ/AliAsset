package com.eam.context;

import lombok.Getter;

/**
 * @Author Steve Zhang
 * @Description // 邮件规则默认的一些类型
 * @Date 2018/8/27 12:02:16
 * @Param
 * @Return
 **/

@Getter
public enum MailDefault {

	Other(0,"其他"),
	Personal(1,"发送员工私人邮箱"),
	Company(2,"发送员工公司邮箱"),
	Superior(3,"发送员工主管公司邮箱"),
	HumanResource(4,"发送员工HR公司邮箱"),
	HRSuperior(5,"发送员工的HR主管公司邮箱"),
	SuperiorAndHR(6,"发送员工主管和HR公司邮箱"),
	PersonalAndCompany(7,"发送员工个人邮箱和公司邮箱");
	private int mailRuleCode;
	private String mailRuleDesc;

	MailDefault(int mailRuleCode, String mailRuleDesc){
		this.mailRuleCode = mailRuleCode;
		this.mailRuleDesc = mailRuleDesc;
	}
}
