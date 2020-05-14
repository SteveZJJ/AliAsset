package com.eam.task.quartz;

import com.eam.task.service.SendWorkFlowService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**************************************
 *@ClassName TodoWorkFlowJob
 *@Description   //发送工作流审批Job
 *@Author jason
 *@Date 2018/9/3 - 17:16
 *@Version 1.0
 **************************************/
@Data
public class SendWorkFlowJob {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void execute(){
		logger.info("-SendWorkFlowJob- is Beginning running------");
		if ( SendWorkFlowService.getInstance().isInProgress() ) {
			logger.info("\"SendWorkFlowJob\" is already running, skip the sync task for this time");
		} else {
			try {
				SendWorkFlowService.getInstance().startSync();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
