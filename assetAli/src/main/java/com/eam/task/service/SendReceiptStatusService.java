package com.eam.task.service;

import com.eam.mybatis.model.R5MailTemplate;
import com.eam.mybatis.model.U5AssetStatusChange;
import com.eam.mybatis.service.GetService;
import com.eam.mybatis.service.MybatisJobService;
import com.eam.task.soap.SendAssetStatusChangeTask;
import com.eam.task.soap.SendReceiptStatusTask;
import com.eam.utils.JsonUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**************************************
 *@ClassName SendAssetStatusChangeService
 *@Description   //发送验收单提交结果
 *@Author jason
 *@Date 2018/10/16 - 14:33
 *@Version 1.0
 **************************************/
@Data
public class SendReceiptStatusService {
	private final static Logger logger = LoggerFactory.getLogger(SendMailService.class);

	private boolean isInProgress = false;
	private ExecutorService threadPool = Executors.newFixedThreadPool(1);
	private HashMap <String, R5MailTemplate> mailTemplates = new HashMap <>();
	private MybatisJobService mybatisService;

	public void startSync(){
		Thread syncTask = new Thread(() -> {
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

	private void syncAll(){
		try {
			List <String> receiptList = mybatisService.getReceiptStatusList();
			receiptList.forEach(this::SendReceiptStatus);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void SendReceiptStatus(String receiptNo){
		logger.info(receiptNo);
		try {

			process(receiptNo,new SendReceiptStatusTask(receiptNo).call());
			Thread.sleep(500);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(String receiptNo,String call){
		try {
			Map <String, Object> map = JsonUtils.getMapFromJson2(call);
			if ( (boolean) map.get("success") ) {
				logger.info("处理成功");
				GetService
						.getInstance()
						.getMybatisJobService()
						.sendReceiptStatusSuccess(receiptNo);
			} else {
				logger.info("处理失败");
				GetService
						.getInstance()
						.getMybatisJobService()
						.sendReceiptStatusError(receiptNo,map.get("errorMsg").toString());
			}
		}
		catch (Exception e) {
			logger.info("处理失败");
			GetService
					.getInstance()
					.getMybatisJobService()
					.sendReceiptStatusError(receiptNo,e.getMessage());
		}
	}

	static class SendAssetStatusChangeServiceHolder {
		static SendReceiptStatusService instance = new SendReceiptStatusService();
	}

	private SendReceiptStatusService(){
		mybatisService = GetService.getInstance().getMybatisJobService();
	}
	
	public static SendReceiptStatusService getInstance(){
		return SendAssetStatusChangeServiceHolder.instance;
	}
}
