package com.eam.task.quartz;

import com.eam.task.service.SendAssetStatusChangeService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**************************************
 *@ClassName SendAssetStatusChangeJob
 *@Description   //同步资产状态等信息。
 *@Author jason
 *@Date 2018/10/16 - 14:30
 *@Version 1.0
 **************************************/
@Data
public class SendAssetStatusChangeJob {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void execute(){
		logger.info("-SendAssetStatusChangeJob- is Beginning running------");
		if ( SendAssetStatusChangeService.getInstance().isInProgress() ) {
			logger.info("SendAssetStatusChangeJob  is already running, skip the sync task for this time");
		} else {
			try {
				SendAssetStatusChangeService.getInstance().startSync();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
