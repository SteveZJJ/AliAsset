package com.eam.task.quartz;

import com.eam.task.service.SendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMailJob {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void execute(){
		logger.info("-SendMailJob- is Beginning running------");
		if ( SendMailService.getInstance().isInProgress() ) {
			logger.info("\"SendMailJob\" is already running, skip the sync task for this time");
		} else {
			try {
				SendMailService.getInstance().startSync();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
