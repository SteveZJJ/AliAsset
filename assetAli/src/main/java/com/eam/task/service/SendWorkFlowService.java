package com.eam.task.service;

import com.eam.mybatis.model.TodoWorkFlow;
import com.eam.mybatis.service.GetService;
import com.eam.task.soap.SendWorkFlowTask;
import com.eam.utils.JsonUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**************************************
 *@ClassName SendWorkFlowService
 *@Description   //发送工作流service
 *@Author jason
 *@Date 2018/9/3 - 17:25
 *@Version 1.0
 **************************************/
@Data
public class SendWorkFlowService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private boolean isInProgress = false;
	private ExecutorService threadPool = Executors.newFixedThreadPool(2);
	
	public static SendWorkFlowService getInstance(){
		return SyncServiceHolder.instance;
	}
	
	public void startSync(){
		Thread syncTask = new Thread(
				() -> {
					setInProgress(true);
					try {
						syncAll();
					}
					catch (Throwable err) {
						logger.error("Error occurred when sync all data: ",err);
					}
					setInProgress(false);
				});
		threadPool.submit(syncTask);
	}
	
	private void syncAll(){
		try {
			logger.info("Go into the Job for SendWorkFlowList");
			GetService
					.getInstance()
					.getMybatisJobService()
					.getWorkFlowList()
					.forEach(this::sendWorkFlow);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendWorkFlow(TodoWorkFlow todoWorkFlow){
		try {
			SendWorkFlowTask task = new SendWorkFlowTask(todoWorkFlow);
			process(todoWorkFlow,task.call());
		}
		catch (Exception e) {
			process(todoWorkFlow,e.getMessage());
		}
	}
	
	private void process(TodoWorkFlow todoWorkFlow,String call){
		logger.info(call);
		try {
			Map <String, Object> map = JsonUtils.getMapFromJson2(call);
			if ( (boolean) map.get("success") ) {
				logger.info("处理成功");
				GetService
						.getInstance()
						.getMybatisJobService()
						.interfaceSuccess(todoWorkFlow.getTransId());
			} else {
				logger.info("处理失败");
				GetService
						.getInstance()
						.getMybatisJobService()
						.interfaceError(todoWorkFlow.getTransId(),map.get("errorMsg").toString());
			}
		}
		catch (Exception e) {
			logger.info("处理失败");
			GetService
					.getInstance()
					.getMybatisJobService()
					.interfaceError(todoWorkFlow.getTransId(),e.getMessage());
		}
	}
	
	public boolean isInProgress(){
		return isInProgress;
	}
	
	private void setInProgress(boolean isInProgress){
		this.isInProgress = isInProgress;
	}
	
	
	private static class SyncServiceHolder {
		static SendWorkFlowService instance = new SendWorkFlowService();
	}
}
