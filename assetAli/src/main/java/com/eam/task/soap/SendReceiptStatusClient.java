package com.eam.task.soap;

import com.eam.context.ConfigService;
import com.eam.mybatis.model.U5AssetStatusChange;
import com.eam.utils.HttpUtils;
import com.eam.utils.JsonUtils;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.util.concurrent.atomic.AtomicReference;

/**************************************
 *@ClassName SendAssetStatusChangeClient
 *@Description   //client of job
 *@Author jason
 *@Date 2018/10/16 - 14:50
 *@Version 1.0
 **************************************/
@Data
public class SendReceiptStatusClient {
	String sendReceiptStatus(String receiptNo){


		AtomicReference<String> str = new AtomicReference<>("");
		String obj_Json = JsonUtils.getJsonFromObject(receiptNo);
		System.out.println(obj_Json);
		HttpEntity entity = new StringEntity(obj_Json, "utf-8");
		HttpPost httpPost = new HttpPost(httpUrl);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(entity);
		return HttpUtils.queryPostResponse(httpPost);
		//return change.toString();
	}


	static class SendReceiptStatusClient_Holder {
		static SendReceiptStatusClient instance = new SendReceiptStatusClient();
	}

	private String httpUrl;

	private SendReceiptStatusClient(){
		//httpUrl = ConfigService.getInstance().getConfig().getAmpUrl() + ConfigService.getInstance().getConfig().getSendReceiptStatusApi();
	}
	
	public static SendReceiptStatusClient getInstance(){
		return SendReceiptStatusClient_Holder.instance;
	}
}
