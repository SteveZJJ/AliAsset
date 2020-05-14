package com.eam.utils;

import lombok.Data;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**************************************
 *@ClassName HttpUtils
 *@Description   //HttpClient
 *@Author jason
 *@Date 2018/8/29 - 11:58
 *@Version 1.0
 **************************************/
@Data
public class HttpUtils {

	
	public static String queryPostResponse(HttpPost httpPost){

		String body = "";
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			//执行请求操作，并拿到结果（同步阻塞）
			response = client.execute(httpPost);
			/* 获取结果实体 */
			HttpEntity entity = response.getEntity();
			if ( entity!=null ) {
				//按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity,Charsets.UTF_8);
			}
			EntityUtils.consume(entity);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try { //释放链接
				if ( response!=null ) {
					response.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
		
	}
	
	
	public static String queryGetResponse(String url,Object param){
		String body = "";
		
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		AtomicReference <String> str = new AtomicReference <>("");
		String obj_Json = JsonUtils.getJsonFromObject(param);
		try {
			Map <String, Object> map
					= JsonUtils.getMapFromJson2(obj_Json);
			map.forEach((Object key,Object value) -> {
				if ( ! StringUtils.isEmpty(value) ) {
					try {
						str.set(str.get() + "&" + key + "=" + URLEncoder.encode(value.toString(),Charsets.UTF_8.toString()));
					}
					catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			});
			str.set(str.get().replaceFirst("&","?"));
		}
		catch (Exception e) {
			str.set(obj_Json);
		}


		//HttpGet httpGet = new HttpGet();

		HttpPost httpPost = new HttpPost();
		try {
			//执行请求操作，并拿到结果（同步阻塞）
			System.out.println(url + str.get());
			//httpGet.setURI(new URI(url + str.get()));
			httpPost.setURI(new URI(url+str.get()));

			//response = client.execute(httpGet);
			response = client.execute(httpPost);
			/* 获取结果实体 */
			HttpEntity entity = response.getEntity();
			
			if ( entity!=null ) {
				//按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity,Charsets.UTF_8);
			}
			EntityUtils.consume(entity);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (URISyntaxException e) {
			body = "{\"success\":false,\"errorMsg\":\"访问Url失败\"}";
		}
		finally {
			try { //释放链接
				if ( response!=null ) {
					response.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
		
	}
}
