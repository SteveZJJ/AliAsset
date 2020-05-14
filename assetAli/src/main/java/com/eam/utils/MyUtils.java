package com.eam.utils;

import com.eam.context.ExceptionDetails;
import com.eam.context.UserDefinedField;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**************************************
 *@ClassName MyUtils
 *@Description   //一些公用的方法
 *@Author jason
 *@Date 2018/8/31 - 10:58
 *@Version 1.0
 **************************************/
@Data
public class MyUtils {
	
	public static String getRealLanguage(String lang){
		try {
			switch(lang) {
				case "EN":
					return Language.EN.language;
				case "ZH":
					return Language.ZH.language;
				default:
					return Language.OTHERS.language;
			}
		}
		catch (Exception e) {
			return Language.OTHERS.language;
		}
	}
	
	public static String getErrorMsg(ExceptionDetails e,String lang){
		try {
			switch(lang) {
				case "EN":
					return e.getERROR_EN();
				case "ZH":
					return e.getERROR_ZH();
				default:
					return e.getERROR_ZH();
			}
		}
		catch (Exception e1) {
			return  e.getERROR_ZH();
		}
	}
	
	
	public static UserDefinedField getDefinedField(String type){
		try {
			return UserDefinedField.valueOf(type);
		}
		catch (Exception e) {
			return UserDefinedField.valueOf("OTHER");
		}
	}
	
	enum Language {
		EN("EN"),ZH("ZH"),OTHERS("ZH");
		private String language;
		
		Language(String language){
			this.language = language;
		}
	}

	public static boolean isListEmpty(List list){
		return list == null || list.isEmpty();
	}


	public static boolean isLegalDateFormat(String sDate){
		int length = 10;
		if(sDate==null||sDate.length()!=length){
			return false;
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date date = formatter.parse(sDate);
			return sDate.equals(formatter.format(date));
		}catch(Exception e){
			return false;
		}

	}



}
