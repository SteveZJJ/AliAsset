package com.eam.task.soap;
/*
 * @author  Jason.Zhao
 * @E-mail: jason.zhao@assetech.cn
 * @date 创建时间：2018年7月5日 上午11:25:31
 * @version 1.0
 */

import com.eam.context.ConfigService;
import com.eam.mybatis.model.TodoWorkFlow;
import com.eam.utils.HttpUtils;

public class SendWorkFlowClient {
	String sendWorkFlowClient(TodoWorkFlow workFlow) throws Exception{
		return HttpUtils.queryGetResponse(httpUrl,workFlow);
	}
	
	static class SendWorkFlowClient_Holder {
		static SendWorkFlowClient instance = new SendWorkFlowClient();
	}
	
	private String httpUrl;
	
	private SendWorkFlowClient(){
		httpUrl = ConfigService.getInstance().getConfig().getAmpUrl() + ConfigService.getInstance().getConfig().getSendWorkFlowApi();
	}
	
	public static SendWorkFlowClient getInstance(){
		return SendWorkFlowClient_Holder.instance;
	}
	
	
}
