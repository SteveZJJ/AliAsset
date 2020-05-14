package com.eam.task.soap;

import com.eam.mybatis.model.CreateSyncFA;
import lombok.Data;

import java.util.concurrent.Callable;

/**************************************
 *@ClassName SendDelaySyncFATask
 *@Description   // task of job
 *@Author Steve Zhang
 *@Date 2019年12月2日17:27:41
 *@Version 1.0
 **************************************/
@Data
public class SendDelaySyncFATask implements Callable {

	CreateSyncFA DelaySyncLine;

	public SendDelaySyncFATask(CreateSyncFA DelaySyncLine){
		this.DelaySyncLine = DelaySyncLine;
	}
	
	@Override
	public String call(){
		return SendDelaySyncFAClient.getInstance().sendDelaySyncFA(DelaySyncLine);
	}
}
