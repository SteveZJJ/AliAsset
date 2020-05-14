package com.eam.task.service;

import com.eam.mybatis.model.R5MailEvents;
import com.eam.mybatis.model.R5MailTemplate;
import com.eam.mybatis.service.GetService;
import com.eam.mybatis.service.MybatisJobService;
import com.eam.task.soap.SendMailTask;
import com.eam.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unused")
public class SendMailService {
	private final static Logger logger = LoggerFactory.getLogger(SendMailService.class);
	
	private boolean isInProgress = false;
	private ExecutorService threadPool = Executors.newFixedThreadPool(1);
	private HashMap <String, R5MailTemplate> mailTemplates = new HashMap <>();
	private MybatisJobService mybatisService;
	
	static class SyncServiceHolder {
		static SendMailService instance = new SendMailService();
	}
	
	private SendMailService(){
		mybatisService = GetService.getInstance().getMybatisJobService();
	}
	
	public static SendMailService getInstance(){
		return SyncServiceHolder.instance;
	}
	
	//class SyncTask implements Runnable {
	//	@Override
	//	public void run(){
	//		setInProgress(true);
	//		try {
	//			syncAll();
	//		}
	//		catch (Throwable err) {
	//			logger.error("Error occured when sync all data: ",err);
	//		}
	//		finally {
	//			setInProgress(false);
	//		}
	//	}
	//}
	
	public void startSync(){
		//SyncTask syncTask = new SyncTask();
		//替换为Lambda写法
		Thread syncTask = new Thread(
				() -> {
					setInProgress(true);
					try {
						syncAll();
					}
					catch (Throwable err) {
						logger.error("Error occured when sync all data: ",err);
					}
					finally {
						setInProgress(false);
					}
				}
		);
		threadPool.submit(syncTask);
	}
	
	private void syncAllMultiLines(){
		try {
			List <R5MailEvents> mailEvents = mybatisService.getSendMailLists();
			mailEvents.forEach(this::SendMail);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void SendMail(R5MailEvents mailEvents){
		logger.info(mailEvents.toString());
		try {

			process(mailEvents,new SendMailTask(mailEvents,
												getMailTemplate(mailEvents.getMAE_TEMPLATE())).call());
			Thread.sleep(500);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	private void process(R5MailEvents mail,String result){
		logger.info("发送邮件结果:" + result);
		boolean flag = false;
		try {
			
			Map <String, Boolean> map = JsonUtils.getMapFromJson(result);
			//如果有value为 true 则为 true
			//flag = map.entrySet().stream().filter(Map.Entry::getValue).count() > 0L;
			flag = map.entrySet().stream().anyMatch(Map.Entry::getValue);
			
		}
		catch (Exception e) {
			//writeResponseBack(mail.getMAE_CODE(),false,e.getMessage());
		}
		try {
			writeResponse(mail.getMAE_CODE(),flag,result);
		}
		catch (Exception e) {
			writeResponse(mail.getMAE_CODE(),flag,"更新error失败");
		}
	}
	
	private void writeResponse(String maeCode,Boolean aBoolean,String message){
		if ( aBoolean ) mybatisService.sendMailSuccess(maeCode,message);
		else mybatisService.sendMailFailed(maeCode,message);
	}
	
	
	private R5MailTemplate getMailTemplate(String mae_template){
		if ( mailTemplates.get(mae_template)==null ) {
			//logger.info("当前没有配置信息，需要重新加载");
			mailTemplates.put(mae_template,mybatisService.getMailTemplateByCode(mae_template));
		}
		//logger.info("已经有配置信息。可以直接使用");
		return mailTemplates.get(mae_template);
	}
	
	private void syncAll(){
		this.syncAllMultiLines();
	}
	
	public boolean isInProgress(){
		return isInProgress;
	}
	
	private void setInProgress(boolean isInProgress){
		this.isInProgress = isInProgress;
	}
	
}
