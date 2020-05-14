
/*
 * MybatisService.java
 *
 */
package com.eam.mybatis.service;

import com.eam.mybatis.dao.JobMapper;
import com.eam.mybatis.model.*;
import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author JasonZhao
 * @Description //  邮件发送
 * @Date 2018/8/28 21:49:43
 * @Param
 * @Return
 **/
@Service
public class MybatisJobService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	JobMapper JobMapper = null;
	
	
	public List <R5MailEvents> getSendMailLists(){
		return JobMapper.getSendMailLists();
	}
	
	public R5MailTemplate getMailTemplateByCode(String mae_template){
		return JobMapper.getMailTemplateByCode(mae_template);
	}
	
	public void sendMailFailed(String maeCode,String message){
		Map <String, String> map = new HashMap <>();
		map.put("maeCode",maeCode);
		map.put("message",message);
		JobMapper.sendMailFailed(map);
	}
	
	public void sendMailSuccess(String maeCode,String message){
		Map <String, String> map = new HashMap <>();
		map.put("maeCode",maeCode);
		map.put("message",message);
		JobMapper.sendMailSuccess(map);
	}
	
	public List <TodoWorkFlow> getWorkFlowList(){
		return JobMapper.getWorkFlowList();
	}
	
	public void interfaceSuccess(String transId){
		Map <String, String> map = new HashMap <>();
		map.put("transId",transId);
		JobMapper.interfaceSuccess(map);
	}
	
	public void interfaceError(String transId,String errorMsg){
		Map <String, String> map = new HashMap <>();
		map.put("transId",transId);
		map.put("errorMsg",errorMsg);
		JobMapper.interfaceError(map);
	}
	
	public List <U5AssetStatusChange> getAssetStatusChangeList(){
		return JobMapper.getAssetStatusChangeList();
	}

    public List<String> getReceiptStatusList() {
		return JobMapper.getReceiptStatusList();
    }

	public void sendReceiptStatusSuccess(String receiptNo) {
		JobMapper.sendReceiptStatusSuccess(receiptNo);
	}
	public void sendReceiptStatusError(String receiptNo,String errorMsg) {
		Map <String, String> map = new HashMap <>();
		map.put("receiptNo",receiptNo);
		map.put("errorMsg",errorMsg);
		JobMapper.sendReceiptStatusError(map);
	}

	public List<CreateSyncFA> getDelaySyncFAList() {
		return JobMapper.getDelaySyncFAList();

	}

//	List<String> getDistinctReceiptNoList(List<CreateSyncFA> SyncFAList){
//		List<String> receiptList = new ArrayList<>();
//		for(CreateSyncFA csf:SyncFAList){
//			csf.getReceiptNo()
//		}
//
//	}

	public void sendDelaySyncFASuccess(String sqlIdentity) {
		JobMapper.sendDelaySyncFASuccess(sqlIdentity);
	}

	public void sendDelaySyncFAError(String id, String errorMsg) {
		Map <String, String> map = new HashMap <>();
		map.put("sqlIdentity",id);
		map.put("errorMsg",errorMsg);
		JobMapper.sendDelaySyncFAError(map);
	}
}
