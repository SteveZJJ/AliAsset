package com.eam.task.soap;
/*
 * @author  Jason.Zhao
 * @E-mail: jason.zhao@assetech.cn
 * @date 创建时间：2018年7月5日 上午11:25:31
 * @version 1.0
 */

import com.eam.context.ConfigService;
import com.eam.task.service.SendMailService;
import com.eam.utils.HttpUtils;
import org.apache.commons.codec.Charsets;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SendMailClient {

	private final static Logger logger = LoggerFactory.getLogger(SendMailClient.class);

	String sendMailClient(HashMap <String, Object> map){
		List <NameValuePair> values = new ArrayList <>();
		//for (Map.Entry <String, String> entry : map.entrySet()) {
		//	values.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		//}
		map.forEach((key,value) -> values.add(new BasicNameValuePair(key,String.valueOf(value))));
		HttpPost httpPost = new HttpPost(httpUrl);
		httpPost.setHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8");
		httpPost.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		httpPost.setEntity(new UrlEncodedFormEntity(values,Charsets.UTF_8));
		logger.info(httpPost.getEntity().toString());
		return HttpUtils.queryPostResponse(httpPost);
	}
	
	static class SendMailClient_Holder {
		static SendMailClient instance = new SendMailClient();
	}
	
	private String httpUrl;
	
	private SendMailClient(){
		httpUrl = ConfigService.getInstance().getConfig().getAmpUrl() + ConfigService.getInstance().getConfig().getSendMailApi();
	}
	
	public static SendMailClient getInstance(){
		return SendMailClient_Holder.instance;
	}
	
	
}
