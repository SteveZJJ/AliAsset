package com.eam.utils;

import com.eam.context.Constants;
import com.eam.mybatis.model.AliResponse;
import com.eam.mybatis.model.AliResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtils {
	private static final Logger LOG = LoggerFactory.getLogger(ResponseUtils.class);
	
	public static void writeResponse(HttpServletResponse response,String strResponse){
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(strResponse);
		}
		catch (Exception e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
		}
		if ( out!=null ) {
			out.flush();
		}
	}
	
	public static void writeSuccessResponse(HttpServletResponse response,Object object){
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,object)));
		}
		catch (Exception e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
		}
		if ( out!=null ) {
			out.flush();
		}
	}
	
	public static void writeErrorResponse(HttpServletResponse response,String errMsg){
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(JsonUtils.getJsonFromObject(new AliResponse(Constants.INTERFACE_ERROR,errMsg)));
		}
		catch (Exception e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
		}
		if ( out!=null ) {
			out.flush();
		}
	}
	
	public static void writeResponse(HttpServletResponse response,Exception e1){
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = null;
		try {
			AliResponse erroResult = new AliResponse(Constants.INTERFACE_ERROR,e1.getMessage());
			out = response.getWriter();
			out.print(JsonUtils.getJsonFromObject(erroResult));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if ( out!=null ) {
			out.flush();
		}
	}
	
	public static String ReadAsChars(HttpServletRequest request){
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = request.getReader();
			String str;
			while( ( str = br.readLine() )!=null ) {
				sb.append(str);
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if ( null!=br ) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	public static Object ReadAsObjects(HttpServletRequest request,Class <?> c){
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = request.getReader();
			String str;
			while( ( str = br.readLine() )!=null ) {
				sb.append(str);
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if ( null!=br ) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return JsonUtils.getObjectFromJson(sb.toString(),
										   c);
	}
	
}
