package com.eam.context;

/**************************************
 *@ClassName ExceptionDetails
 *@Description   //错误代码返回。中英文错误日志。
 *@Author jason
 *@Date 2018/9/5 - 14:54
 *@Version 1.0
 **************************************/

public enum ExceptionDetails {
	
	TaskId_EMPTY("任务单号为空","eamTaskId is empty"),
	TransactionId_EMPTY("交易号为空","eamTransactionId is empty"),
	OwnerNO_EMPTY("被通知人为空","ownerNo is empty"),
	ConfirmationType_EMPTY("通知类型为空","eamTaskId is empty"),
	USER_NOT_EXISTS("用户不存在","The user is not exists in EAM "),
	ASSET_NOT_EXISTS("资产类录不存在：","The assetCategory is not exists in EAM "),
	OBJECT_REFUSED_ACTION("资产不允许此项操作,:","Such transactions can not happen to assets. ,details: "),
	PICKUP_NOT_EXISTS("入职领用单号在资产中台中不存在","The pickup message is not exists in EAM ");
	private String ERROR_ZH;
	private String ERROR_EN;
	
	
	ExceptionDetails(String error_ZH,String error_EN){
		this.ERROR_ZH = error_ZH;
		this.ERROR_EN = error_EN;
	}
	
	public String getERROR_ZH(){
		return ERROR_ZH;
	}
	
	public String getERROR_EN(){
		return ERROR_EN;
	}
}
