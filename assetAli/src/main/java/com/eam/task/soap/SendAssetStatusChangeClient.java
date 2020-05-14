package com.eam.task.soap;

import com.eam.context.ConfigService;
import com.eam.mybatis.model.U5AssetStatusChange;
import com.eam.utils.HttpUtils;
import com.eam.utils.JsonUtils;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicReference;

/**************************************
 *@ClassName SendAssetStatusChangeClient
 *@Description   //client of job
 *@Author jason
 *@Date 2018/10/16 - 14:50
 *@Version 1.0
 **************************************/
@Data
public class SendAssetStatusChangeClient {
	String sendAssetStatusChange(U5AssetStatusChange change){


		AtomicReference<String> str = new AtomicReference<>("");
		String obj_Json = JsonUtils.getJsonFromObject(change);
		System.out.println(obj_Json);
		HttpEntity entity = new StringEntity(obj_Json, "utf-8");
		HttpPost httpPost = new HttpPost(httpUrl);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(entity);
		return HttpUtils.queryPostResponse(httpPost);
		//return change.toString();
	}
	
	static class SendAssetStatusChangeClient_Holder {
		static SendAssetStatusChangeClient instance = new SendAssetStatusChangeClient();
	}
	
	private String httpUrl;
	
	private SendAssetStatusChangeClient(){
		httpUrl = ConfigService.getInstance().getConfig().getAmpUrl() + ConfigService.getInstance().getConfig().getSendAssetStatusChangeApi();
	}
	
	public static SendAssetStatusChangeClient getInstance(){
		return SendAssetStatusChangeClient_Holder.instance;
	}
}
