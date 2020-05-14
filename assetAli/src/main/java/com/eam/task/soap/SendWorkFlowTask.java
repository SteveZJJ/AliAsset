package com.eam.task.soap;

import com.eam.mybatis.model.TodoWorkFlow;
import lombok.Data;

/**************************************
 *@ClassName SendWorkFlow
 *@Description   //
 *@Author jason
 *@Date 2018/9/3 - 18:18
 *@Version 1.0
 **************************************/
@Data
public class SendWorkFlowTask {
	private TodoWorkFlow workFlow;//工作流信息
	
	public SendWorkFlowTask(TodoWorkFlow todoWorkFlow){
		this.workFlow = todoWorkFlow;
	}
	public String call() throws Exception{
		return SendWorkFlowClient
					   .getInstance()
					   .sendWorkFlowClient(workFlow);
		
	}
}
