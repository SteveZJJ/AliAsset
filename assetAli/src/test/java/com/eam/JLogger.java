package com.eam;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**************************************
 *@ClassName queryTestLogger
 *@description 获取test版的Logger
 *@Author jason
 *@Date 2018/8/6 下午8:10
 *@Version 1.0
 **************************************/
public class JLogger {
	static class queryTestLoggerHolder {
		static JLogger instance = new JLogger();
	}
	
	public static JLogger getInstance(){
		return queryTestLoggerHolder.instance;
	}
	
	public Logger getlogger(){
		System.out.println("调用创建logger");
		Logger logger;
		String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
		//String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
		logger = LoggerFactory.getLogger(classname);
		//System.out.println(test.class.getResource(""));
		//System.out.println(System.getProperty("user.dir"));
		PropertyConfigurator.configure("src/test/java/log4j.properties");
		//PropertyConfigurator.configure(System.getProperty("user.dir") + "/assetAli/src/test/java/log4j.properties");
		return logger;
	}
	
}
