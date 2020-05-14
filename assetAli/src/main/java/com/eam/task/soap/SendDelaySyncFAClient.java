package com.eam.task.soap;

import com.eam.context.ConfigService;
import com.eam.mybatis.model.CreateSyncFA;
import com.eam.utils.HttpUtils;
import com.eam.utils.JsonUtils;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.util.concurrent.atomic.AtomicReference;

/**************************************
 *@ClassName SendDelaySyncFAClient
 *@Description   //client of job
 *@Author Steve Zhang
 *@Date 2019年12月2日17:29:56
 *@Version 1.0
 **************************************/
@Data
public class SendDelaySyncFAClient {
	String sendDelaySyncFA(CreateSyncFA DelaySyncFA){


		AtomicReference<String> str = new AtomicReference<>("");
		String obj_Json = JsonUtils.getJsonFromObject(DelaySyncFA);
		System.out.println(obj_Json);
		HttpEntity entity = new StringEntity(obj_Json, "utf-8");
		HttpPost httpPost = new HttpPost(httpUrl);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(entity);
		return HttpUtils.queryPostResponse(httpPost);
		//return change.toString();
	}


	static class SendDelaySyncFAClient_Holder {
		static SendDelaySyncFAClient instance = new SendDelaySyncFAClient();
	}

	private String httpUrl;

	private SendDelaySyncFAClient(){
		httpUrl = ConfigService.getInstance().getConfig().getAmpUrl() + ConfigService.getInstance().getConfig().getSendDelaySyncFAApi();
	}
	
	public static SendDelaySyncFAClient getInstance(){
		return SendDelaySyncFAClient_Holder.instance;
	}
}
