package com.eam.task.soap;

import com.eam.mybatis.model.U5AssetStatusChange;
import lombok.Data;

import java.util.concurrent.Callable;

/**************************************
 *@ClassName SendAssetStatusChangeTask
 *@Description   // task of job
 *@Author jason
 *@Date 2018/10/16 - 14:45
 *@Version 1.0
 **************************************/
@Data
public class SendReceiptStatusTask implements Callable {

	String receiptNo;

	public SendReceiptStatusTask(String receiptNo){
		this.receiptNo = receiptNo;
	}
	
	@Override
	public String call(){
		return SendReceiptStatusClient.getInstance().sendReceiptStatus(receiptNo);
	}
}
