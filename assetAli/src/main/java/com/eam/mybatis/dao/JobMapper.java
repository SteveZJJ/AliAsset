package com.eam.mybatis.dao;

import com.eam.mybatis.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**************************************
 *@ClassName SendMailMapper
 *@Description   //
 *@Author jason
 *@Date 2018/8/28 - 22:38
 *@Version 1.0
 **************************************/
public interface JobMapper {
	
	List <R5MailEvents> getSendMailLists();
	
	R5MailTemplate getMailTemplateByCode(String mae_template);
	
	void sendMailFailed(Map <String, String> maeCode);
	
	void sendMailSuccess(Map <String, String> maeCode);
	
	List <TodoWorkFlow> getWorkFlowList();
	
	void interfaceSuccess(Map <String, String> map);
	
	void interfaceError(Map <String, String> map);
	
	List <U5AssetStatusChange> getAssetStatusChangeList();

    List<String> getReceiptStatusList();

	void sendReceiptStatusSuccess(String receiptNo);

	void sendReceiptStatusError(Map<String,String> map);

    List<CreateSyncFA> getDelaySyncFAList();

	void sendDelaySyncFASuccess(String sqlIdentity);

	void sendDelaySyncFAError(Map<String, String> map);
}
