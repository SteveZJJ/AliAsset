
/*
 * MybatisService.java
 *
 */
package com.eam.mybatis.service;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.dao.TransferAllocateMapper;
import com.eam.mybatis.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * For db accessor. To store the EHR data to EAM database.
 *
 * @Author Steve Zhang
 */
@Service
public class MybatisTransferAllocateService {
	
	@Autowired
	TransferAllocateMapper transferAllocateMapper = null;


	private final Logger logger = LoggerFactory.getLogger(getClass());
//	/**
//	 * @Author JasonZhao
//	 * @Description updatePickupItem // 创建备件信息
//	 * @Date 2018/8/27 11:54:23
//	 * @Param [evtCode, item]
//	 * @Return void
//	 **/
//	public void updateScrapItem(String evtCode,List <PickupLine> item){
//		HashMap <String, String> map = new HashMap <>();
//		map.put(MapKey.EVT_CODE,evtCode);
//		//add by Jason Zhao
//		//避免处理其他内容，先执行清理操作;
//		transferAllocateMapper.cleanScrapLine(map);
//		for (PickupLine line : item) {
//			map.put(MapKey.REQ_LINE,line.getReqLine());
//			map.put(MapKey.PART_CODE,line.getAssetCategory());
//			map.put(MapKey.NORMAL_MODEL,line.getNormalModel());
//			map.put(MapKey.QTY,line.getQty());
//			map.put(MapKey.SPECIFICATION,line.getSpecification());
//			map.put(MapKey.DURATION,line.getDuration());
//			transferAllocateMapper.updateScrapLine(map);
//
//		}
//	}
	
	/**
	 * @Author Steve Zhang
	 * @Description updateDetailEvents // 更新子工单信息
	 * @Date 2019年4月18日11:07:32
	 * @Param [evtCode, item]
	 * @Return void
	 **/
	private void updateDetailEvents(String evtCode,List <TransferLine> item){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,evtCode);
		transferAllocateMapper.cleanDetailEvents(map);
		for (TransferLine line : item) {
			line.setEvtCode(evtCode);
			transferAllocateMapper.updateDetailEvents(line);
		}
	}
	
	public void registerExpressNo(String eventCode,String expressNo) throws Exception{
		if ( StringUtils.isEmpty(eventCode) )
			throw new Exception("单号为空，无法更新快递单号");
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,eventCode);
		map.put(MapKey.EXPRESS_NO,expressNo);
		transferAllocateMapper
				.registerExpressNo(map);
	}


	public AliResultProcedure createTransferEvent(TransferOrAllocateMessage Transfer){
		return transferAllocateMapper.createTransferEvent(Transfer);
	}

	public void insertEventFiles(Attachment s){
		transferAllocateMapper.insertEventFiles(s);
	}

	public void updateApplyItem(String evtCode, List<TransferLine> item, String detailType) {
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,evtCode);
		switch(detailType) {
			case Constants.EVT_DETAIL_TYPE_EVENT:
				updateDetailEvents(evtCode,item);
				break;
			case Constants.EVT_DETAIL_TYPE_PART:
				//updateScrapItem(evtCode,item);
				break;
			case Constants.EVT_DETAIL_TYPE_REQ:
				updateRequistionItems(evtCode,item);
			default:
				break;
		}
	}


	/**
	 * @Author Steve Zhang
	 * @Description updateRequistionItems // 更新子工单信息
	 * @Date 2019年5月8日15:44:18
	 * @Param [evtCode, item]
	 * @Return void
	 **/
	private void updateRequistionItems(String evtCode,List <TransferLine> item){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,evtCode);
		transferAllocateMapper.cleanRequistionLines(map);
		for (TransferLine line : item) {
			map.put(MapKey.OBJ_CODE,line.getAssetCode());
			transferAllocateMapper.updateRequistionItems(map);
		}
	}


	public TransferOrAllocateMessage queryEvent(HashMap<String, String> map) {

		TransferOrAllocateMessage event = new TransferOrAllocateMessage();
		List<TransferLine> eventLines = new ArrayList<>();
		List<LineSummary> eventLineSummary = new ArrayList<LineSummary>();
		List<Attachment> eventFiles = new ArrayList<>();

		if(Constants.EVT_DETAIL_TYPE_REQ.equals(map.get(MapKey.EVT_DETAILTYPE))){
			event = transferAllocateMapper.queryRequiHeader(map);
			eventLines = transferAllocateMapper.queryRequiItems(map);
			eventLineSummary = transferAllocateMapper.queryRequiSummary(map);
			eventFiles = transferAllocateMapper.queryEventFiles(map);
		}else {
			event = transferAllocateMapper.queryEventHeader(map);
			eventLines = transferAllocateMapper.queryEventItems(map);
			eventLineSummary = transferAllocateMapper.queryEventSummary(map);
			eventFiles = transferAllocateMapper.queryEventFiles(map);
		}
		if(event!=null) {
			event.setItem(eventLines);
			event.setSummary(eventLineSummary);
			event.setAttachments(eventFiles);
		}
		return event;
	}

	public String getEvtJobtype(String evtCode) {
		return transferAllocateMapper.getEvtJobtype(evtCode);
	}

    public AliResultProcedure updateEventLine(TransferLine line) {
		return transferAllocateMapper.updateEventLine(line);
    }

	public List<TransferLine> queryEventLines(HashMap<String, String> map) {
		return transferAllocateMapper.queryEventLines(map);
	}
}