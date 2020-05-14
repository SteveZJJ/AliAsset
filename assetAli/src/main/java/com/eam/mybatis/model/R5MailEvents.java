package com.eam.mybatis.model;

import com.eam.context.MailDefault;
import lombok.Data;

/**************************************
 *@ClassName R5MailEvents
 *@Description   //发送邮件邮件内容
 *@Author jason
 *@Date 2018/8/28 - 20:32
 *@Version 1.0
 **************************************/
@Data
public class R5MailEvents {
	private String MAE_UPDATECOUNT;
	private String MAE_TEMPLATE;
	private String MAE_SQLIDENTITY;
	private String MAE_SEND;
	private String MAE_RSTATUS;
	private String MAE_PTFSEND;
	private String MAE_PTFERROR;
	private String MAE_PARAM9;
	private String MAE_PARAM8;
	private String MAE_PARAM7;
	private String MAE_PARAM6;
	private String MAE_PARAM5;
	private String MAE_PARAM4;
	private String MAE_PARAM3;
	private String MAE_PARAM2;
	private String MAE_PARAM15;
	private String MAE_PARAM14;
	private String MAE_PARAM13;
	private String MAE_PARAM12;
	private String MAE_PARAM11;
	private String MAE_PARAM10;
	private String MAE_PARAM1;
	private String MAE_EWSURL;
	private String MAE_ERROR;
	private String MAE_EMAILRECIPIENT;
	private String MAE_DATE;
	private String MAE_CODE;
	private String MAE_ATTRIBPK;

	private MailDefault mer_rulecode;
}
