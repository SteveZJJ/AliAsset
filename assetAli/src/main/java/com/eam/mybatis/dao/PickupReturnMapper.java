package com.eam.mybatis.dao;

import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.model.Attachment;
import com.eam.mybatis.model.PickupOrReturnMessage;

import java.util.HashMap;

public interface PickupReturnMapper {
	AliResultProcedure createPickupEvent(PickupOrReturnMessage pickup);
	
	AliResultProcedure createDailyEvent(PickupOrReturnMessage data);
	
	void updatePickupEvent(PickupOrReturnMessage pickup);
	
	void updatePickupLine(HashMap map);
	
	void registerExpressNo(HashMap <String, String> map);
	
	void updateDetailEvents(HashMap<String, String> map);
	
	void cleanDetailEvents(HashMap<String, String> map);
	
	void cleanPickupLine(HashMap<String, String> map);
	
	void insertEventFiles(Attachment s);

    String getPersonNamebyCode(String personCode);

    AliResultProcedure createTransferReturnEvent(PickupOrReturnMessage pickup);
}
