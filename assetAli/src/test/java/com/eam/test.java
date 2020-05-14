package com.eam;


import com.eam.mybatis.model.R5MailEvents;
import com.eam.utils.JsonUtils;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**************************************
 *@ClassName testEquals
 *@Description test
 *@Author jason
 *@Date 2018/8/6 下午6:00
 *@Version 1.0
 **************************************/

@SuppressWarnings("unused")
public class test {
	
	
	private Logger jl = JLogger.getInstance().getlogger();
	
	@Test
	public void equals(){
		//so that the equals is value,and the == is the same
		//Integer r1 = new Integer(1);
		//Integer r2 = new Integer(1);
		//int r3 = 1;
		//int r4 = 1;
		//jl.info(String.format("r1==r2 结果是%b",r1==r2));
		//jl.info(String.format("r2==r4 结果是%b",r2==r4));
		//jl.info(String.format("r3==r4 结果是%b",r3==r4));
		//jl.info(String.format("r1 equals r2 结果是%b",r1.equals(r2)));
		//jl.info(String.format("r2 equals r3 结果是%b",r2.equals(r3)));
		//jl.info(String.format("%s  结果是%sdd","not null ",""));
		jl.info(map.entrySet().stream().filter(s -> s.getKey().equals("content")).count() + "");
		sendMailClient(map);
		
	}
	
	private HashMap <String, String> map = new HashMap <String, String>() {
		{
			put("workNos","[\"JWANG\"]");
			put("title","Password reset request");
			put("content","The password has been reset for your USER ID: PARM2.\n"
								  + "\n"
								  + "Your new password is: PARM3\n"
								  + "\n"
								  + "Thank you!");
		}
	};
	
	@SuppressWarnings("unused")
	private String sendMailClient(HashMap <String, String> map){
		String body = "";
		List <NameValuePair> values = new ArrayList <>();
		jl.info(map.toString());
		for (Map.Entry <String, String> entry : map.entrySet()) {
			values.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		}
		HttpPost httpPost;
		//httpPost = new HttpPost(ConfigService.getInstance().getConfig().getSendMailUrl());
		httpPost = new HttpPost("http://11.162.84.205:7001/workflow/email/emailContent.json");
		httpPost.setEntity(new UrlEncodedFormEntity(values,Charsets.UTF_8));
		httpPost.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		httpPost.setHeader("Content-type","application/x-www-form-urlencoded");
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			//执行请求操作，并拿到结果（同步阻塞）
			response = client.execute(httpPost);
			//获取结果实体
			HttpEntity entity = response.getEntity();
			if ( entity!=null ) {
				//按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity,"utf-8");
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
	
	
	@Test
	public void testReplace(){
		String str = "%1%2%3%4%5%6%7%8%9%10%11%12%13%14%15%";
		jl.info(str);
		jl.info(str.replaceAll("%1","-1-")
						.replaceAll("%2","-2-")
						.replaceAll("%3","3")
						.replaceAll("%4","4")
						.replaceAll("%5","5")
						.replaceAll("%6","6")
						.replaceAll("%7","7")
						.replaceAll("%8","8")
						.replaceAll("%9","9")
						.replaceAll("%10","10")
						.replaceAll("%11","11")
						.replaceAll("%12","12")
						.replaceAll("%13","13")
						.replaceAll("%14","14")
						.replaceAll("%15","15"));
	}
	
	@Test
	public void testStringToMap(){
		jl.info(map.toString());
		jl.info(String.valueOf(Charsets.UTF_8));
		
	}
	
	
	@Test
	public void objectJsonToMap(){
		AtomicReference <String> str = new AtomicReference <>("");
		R5MailEvents test = new R5MailEvents();
		test.setMAE_PARAM1("parm1");
		test.setMAE_PARAM2("parm2");
		test.setMAE_PARAM3("parm3");
		test.setMAE_PARAM4("parm4");
		test.setMAE_PARAM5("parm5");
		test.setMAE_PARAM6("parm6");
		test.setMAE_PARAM7("parm7");
		test.setMAE_PARAM8("parm8");
		test.setMAE_PARAM9("parm9");
		test.setMAE_PARAM10("parm10");
		test.setMAE_PARAM11("parm11");
		test.setMAE_PARAM12("parm12");
		
		
		try {
			String obj_Json = JsonUtils.getJsonFromObject(test);
			Map <String, Object> map
					= JsonUtils.getMapFromJson2(obj_Json);
			map.forEach((key,value) -> {
				if ( ! StringUtils.isEmpty(value) )
					str.set(str.get() + "&" + key + "=" + value);
			});
			jl.info(str.get().replaceFirst("&",""));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void objectToMap(){
		AtomicReference <String> str = new AtomicReference <>("%1%2%3%4%5%6%7%8%9%10%11%12%13%14%15%");
		R5MailEvents test = new R5MailEvents();
		test.setMAE_PARAM1("parm1");
		test.setMAE_PARAM2("parm2");
		test.setMAE_PARAM3("parm3");
		test.setMAE_PARAM4("parm4");
		test.setMAE_PARAM5("parm5");
		test.setMAE_PARAM6("parm6");
		test.setMAE_PARAM7("parm7");
		test.setMAE_PARAM8("parm8");
		test.setMAE_PARAM9("parm9");
		test.setMAE_PARAM10("parm10");
		test.setMAE_PARAM11("parm11");
		test.setMAE_PARAM12("parm12");
		try {
			
			BeanMap map2
					= new BeanMap(test);
			Map <String, String> map = new HashMap <>();
			map2.entrySet().forEach(
					o -> {
						if ( ! StringUtils.isEmpty(o.getValue()) && o.getKey().toString().startsWith("MAE_PARAM") ) {
							map.put(o.getKey().toString(),o.getValue().toString());
						}
					});
			jl.info(map.toString());
			map.entrySet().stream()
					.sorted((p1,p2) -> ( p2.getKey().compareTo(p1.getKey()) ))
					.forEach((s) -> {
						jl.info(s.toString());
						str.set(str.get().replaceAll(
								s.getKey().replace("MAE_PARAM","%"),
								s.getValue()));
					});
			
			jl.info(str.get());
			//map2.entrySet().forEach(e -> jl.info(e.toString()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void tryCatch(){
		
		try {
			StringBuilder checkString = new StringBuilder();
			String xx = null;
			jl.info("--" + checkString.toString() + "--");
			if ( "".equals(checkString.toString()) )
				jl.info("dfsfss");
			;
			jl.info(xx);
			throw new SecurityException();
		}
		catch (SecurityException e) {
			jl.info("SecurityException");
		}
		catch (Exception e) {
			jl.info("Exception");
		}
		
	}
	
	@Test
	public void test22() throws IOException{
		
		String xml1 = "UGFzc3dvcmQgUmVzZXQgUmVxdWVzdA==";
		String content = "VGhlIHBhc3N3b3JkIGhhcyBiZWVuIHJlc2V0IGZvciB5b3VyIFVTRVIgSUQ6IDE0NDk0OS4KCllvdXIgbmV3IHBhc3N3b3JkIGlzOiBuZXdQYXNzV2QKClRoYW5rIHlvdSE=";
		BASE64Decoder decoder = new BASE64Decoder();
		String content_xx = new String(decoder.decodeBuffer(content),"utf-8");
		
		jl.info(new String(decoder.decodeBuffer(xml1),"utf-8"));
		jl.info(content_xx);
		jl.info(content_xx.replace("\n","</br>"));
		jl.info(new String(URLEncoder.encode(content_xx,"utf-8")));
	}
	
	
}
