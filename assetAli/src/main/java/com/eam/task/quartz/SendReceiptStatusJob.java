package com.eam.task.quartz;

import com.eam.task.service.SendReceiptStatusService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**************************************
 *@ClassName SendReceiptStatusJob
 *@Description   //同步资产状态等信息。
 *@Author jason
 *@Date 2018/10/16 - 14:30
 *@Version 1.0
 **************************************/
@Data
public class SendReceiptStatusJob {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void execute(){
		logger.info("-SendReceiptStatusJob- is Beginning running------");
		if ( SendReceiptStatusService.getInstance().isInProgress() ) {
			logger.info("SendReceiptStatusJob  is already running, skip the sync task for this time");
		} else {
			try {
				SendReceiptStatusService.getInstance().startSync();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
