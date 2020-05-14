package com.eam;

import org.junit.Test;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**************************************
 *@ClassName someDate
 *@Description   //测试一些日期格式demo
 *@Author jason
 *@Date 2018/9/17 - 14:08
 *@Version 1.0
 *
 * Date ：　　　　　　　　 getTime（） 、setTime（）
 *
 * SimpleDateFormate :   Formate(Date)、 parse(String s)
 *
 * Calendar ：　　　　　　getInstance()、set() 、get()、getActualMaximum()、add()、
 *
 * 　　　　　　　　　　　  gettime()、setTime（Date）
 **************************************/
public class someDate {
	private Logger jl = JLogger.getInstance().getlogger();
	
	@Test
	public void getDate(){
		Date date = new Date();
		jl.info(format(date,"YYYY-MM-dd HH:mm:ss"));
		jl.info(format(date,"YYYY-MM-DD HH-hh:mm:ss"));
		long time = date.getTime();
		date.setTime(time + 24 * 60 * 60 * 1000);
		jl.info(String.valueOf(date));
		
		
	}
	
	/****
	 * 将Date格式化为String　　  String format(Date d)
	 * 将String解析为Date　　　　Date   parse(String s)
	 */
	private String format(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	@Test
	public void testCalendar(){
		Calendar now = Calendar.getInstance();
		Calendar now_timezone = Calendar.getInstance(TimeZone.getDefault());
		jl.info(now.getTime().toString());
		jl.info(now.get(Calendar.YEAR) + " YEAR");
		jl.info(now.get(Calendar.MONTH) + " 月份MONTH");
		jl.info(now.get(Calendar.DATE) + "第几天DATE");
		jl.info(now.get(Calendar.DAY_OF_MONTH) + "第几天DAY_OF_MONTH");
		jl.info(now.get(Calendar.DAY_OF_WEEK) + "周几DAY_OF_WEEK");
		jl.info(now.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "某月中第几周DAY_OF_WEEK_IN_MONTH");
		jl.info(now.get(Calendar.WEEK_OF_MONTH) + "某月中第几中农DAY_OF_WEEK_IN_MONTH");
		jl.info(now.get(Calendar.WEEK_OF_YEAR) + "某月中第几中农DAY_OF_WEEK_IN_MONTH");
		jl.info(now.get(Calendar.DAY_OF_YEAR) + "DAY_OF_YEAR");
		jl.info(now.get(Calendar.HOUR) + "HOUR");
		jl.info(now.get(Calendar.HOUR_OF_DAY) + "HOUR_OF_DAY");
		jl.info(now.get(Calendar.MINUTE) + "MINUTE");
		jl.info(now.get(Calendar.SECOND) + "SECOND");
		jl.info(now_timezone.toString());
	}
}
