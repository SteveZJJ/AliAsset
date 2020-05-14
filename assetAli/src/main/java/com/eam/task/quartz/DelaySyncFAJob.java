package com.eam.task.quartz;

import com.eam.task.service.DelaySyncFAService;
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
public class DelaySyncFAJob {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void execute(){
		logger.info("-DelaySyncFAJob- is Beginning running------");
		if ( DelaySyncFAService.getInstance().isInProgress() ) {
			logger.info("DelaySyncFAJob  is already running, skip the sync task for this time");
		} else {
			try {
				DelaySyncFAService.getInstance().startSync();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
