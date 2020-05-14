package com.eam.utils;

import com.eam.context.ExceptionDetails;

/**************************************
 *@ClassName AliEamException
 *@Description   //错误信息抛出
 *@Author jason
 *@Date 2018/9/5 - 15:21
 *@Version 1.0
 **************************************/
public class AliEamException extends Throwable {
	
	private ExceptionDetails error;
	
	public AliEamException(ExceptionDetails e){
		this.error = e;
	}
	
	public ExceptionDetails getError(){
		return error;
	}
}
