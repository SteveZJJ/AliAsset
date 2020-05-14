package com.eam.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JsonUtils {
	private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);
	
	public static Object getObjectFromJson(String strJson,Class <?> c){
		try {
			LOG.info(strJson);
			ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD,Visibility.ANY);
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			return mapper.readValue(strJson,c);
		}
		catch (Exception e) {
			//e.printStackTrace();
			//LOG.error("geObjectFromJson error:", e);
			return strJson;
		}
	}
	
	public static Map <String, Boolean> getMapFromJson(String strJson){
		try {
			LOG.info(strJson);
			ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD,Visibility.ANY);
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			return mapper.readValue(strJson,new TypeReference <Map <String, Boolean>>() {});
		}
		catch (Exception e) {
			return new HashMap <>();
		}
	}
	
	public static Map <String, Object> getMapFromJson2(String strJson){
		try {
			ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD,Visibility.ANY);
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			return mapper.readValue(strJson,new TypeReference <Map <String, Object>>() {});
		}
		catch (Exception e) {
			return new HashMap <>();
		}
	}
	
	public static Object getObjectFromJson(String strJson,@SuppressWarnings("rawtypes") TypeReference t){
		try {
			ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD,Visibility.ANY);
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			return mapper.readValue(strJson,t);
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("geObjectFromJson error:",e);
			return strJson;
		}
	}
	
	
	public static String getJsonFromObject(Object obj){
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("getJsonFromObject error:",e);
			return obj.toString();
		}
	}
	
	
}
