
/*
 * MybatisService.java
 *
 */
package com.eam.mybatis.service;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.dao.PickupReturnMapper;
import com.eam.mybatis.dao.ReceiptMapper;
import com.eam.mybatis.dao.ScrapDisposeMapper;
import com.eam.mybatis.model.*;
import com.eam.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

import static com.eam.context.EventDefault.AQCL;

/**
 * For db accessor. To store the EHR data to EAM database.
 *
 * @Author Steve Zhang
 */
@Service
public class MybatisScrapDisposeService {
	
	@Autowired
	ScrapDisposeMapper scrapDisposeMapper = null;
	

	
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
//		scrapDisposeMapper.cleanScrapLine(map);
//		for (PickupLine line : item) {
//			map.put(MapKey.REQ_LINE,line.getReqLine());
//			map.put(MapKey.PART_CODE,line.getAssetCategory());
//			map.put(MapKey.NORMAL_MODEL,line.getNormalModel());
//			map.put(MapKey.QTY,line.getQty());
//			map.put(MapKey.SPECIFICATION,line.getSpecification());
//			map.put(MapKey.DURATION,line.getDuration());
//			scrapDisposeMapper.updateScrapLine(map);
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
	private void updateDetailEvents(String evtCode, String language, List <ScrapLine> item){
		HashMap <String, Object> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,evtCode);
		map.put(MapKey.LANGUAGE, MyUtils.getRealLanguage(language));
		scrapDisposeMapper.cleanDetailEvents(map);
		for (ScrapLine line : item) {
			map.put(MapKey.OBJ_CODE,line.getAssetCode());
			map.put(MapKey.OBJ_ORIGIN_VALUE, line.getOriginValue());
			map.put(MapKey.OBJ_NET_VALUE, line.getNetValue());
			map.put(MapKey.FA_TYPE, line.getFaAssetType());
			scrapDisposeMapper.updateDetailEvents(map);
		}
	}
	
	public void registerExpressNo(String eventCode,String expressNo) throws Exception{
		if ( StringUtils.isEmpty(eventCode) )
			throw new Exception("单号为空，无法更新快递单号");
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,eventCode);
		map.put(MapKey.EXPRESS_NO,expressNo);
		scrapDisposeMapper
				.registerExpressNo(map);
	}


	public AliResultProcedure createScrapEvent(ScrapOrDisposeMessage Scrap){
		return scrapDisposeMapper.createScrapEvent(Scrap);
	}

	public void insertEventFiles(Attachment s){
		scrapDisposeMapper.insertEventFiles(s);
	}

	public void updateApplyItem(String evtCode, String language, List<ScrapLine> item, String detailType) {
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,evtCode);
		switch(detailType) {
			case Constants.EVT_DETAIL_TYPE_EVENT:
				updateDetailEvents(evtCode,language,item);
				break;
			case Constants.EVT_DETAIL_TYPE_PART:
				//updateScrapItem(evtCode,item);
				break;
			default:
				break;
		}
	}

	public ScrapOrDisposeMessage queryEvent(HashMap<String, String> map) {
		ScrapOrDisposeMessage event = scrapDisposeMapper.queryEventHeader(map);
		List<ScrapLine> eventLines = scrapDisposeMapper.queryEventItems(map);
		List<Attachment> eventAttachment = scrapDisposeMapper.queryEventFiles(map);
		if(AQCL.getEvtJobType().equals(map.get(MapKey.EVT_JOB_TYPE))) {
			List<Quotation> eventQuotation = scrapDisposeMapper.queryEventQuotations(map);
			map.put(MapKey.BID_CODE,"");
			eventQuotation.forEach(
					s -> {
						map.replace(MapKey.BID_CODE,s.getCode());
						List<Attachment> quotationAttachment = scrapDisposeMapper.queryQuotationFiles(map);
						s.setAttachments(quotationAttachment);
					}
			);
			event.setQuotations(eventQuotation);
		}
		event.setItem(eventLines);
		event.setAttachments(eventAttachment);
		return event;
	}

    public void insertQuotationsLines(Quotation s) {
		scrapDisposeMapper.insertQuotationsLines(s);
    }

	public int updateFAScrapResult(HashMap<String, String> map) {
		return scrapDisposeMapper.updateFAScrapResult(map);
	}

	public void cleanQuotationsLines(String evtCode) {
		scrapDisposeMapper.cleanQuotationsLines(evtCode);
		scrapDisposeMapper.cleanQuatationFiles(evtCode);
	}

	public void cleanEventFiles(String evtCode) {
		scrapDisposeMapper.cleanEventFiles(evtCode);
	}
}