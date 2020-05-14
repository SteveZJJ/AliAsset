
/*
 * MybatisService.java
 *
 */
package com.eam.mybatis.service;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.dao.PickupReturnMapper;
import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.model.Attachment;
import com.eam.mybatis.model.PickupLine;
import com.eam.mybatis.model.PickupOrReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * For db accessor. To store the EHR data to EAM database.
 *
 * @Author JASONZHAO
 */
@Service
public class MybatisPickupReturnService {
	
	@Autowired
	PickupReturnMapper pickupReturnService = null;
	
	public AliResultProcedure createPickupEvent(PickupOrReturnMessage pickup){
		return pickupReturnService.createPickupEvent(pickup);
	}
	
	/**
	 * @Author JasonZhao
	 * @Description updatePickupItem // 创建备件信息
	 * @Date 2018/8/27 11:54:23
	 * @Param [evtCode, item]
	 * @Return void
	 **/
	public void updatePickupItem(String evtCode,List <PickupLine> item){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,evtCode);
		//add by Jason Zhao
		//避免处理其他内容，先执行清理操作;
		pickupReturnService.cleanPickupLine(map);
		for (PickupLine line : item) {
			map.put(MapKey.REQ_LINE,line.getReqLine());
			map.put(MapKey.PART_CODE,line.getAssetCategory());
			map.put(MapKey.NORMAL_MODEL,line.getNormalModel());
			map.put(MapKey.QTY,line.getQty());
			map.put(MapKey.SPECIFICATION,line.getSpecification());
			map.put(MapKey.DURATION,line.getDuration());
			pickupReturnService.updatePickupLine(map);
			
		}
	}
	
	/**
	 * @Author JasonZhao
	 * @Description updateDetailEvents // 更新子工单信息
	 * @Date 2018/8/27 11:55:531
	 * @Param [evtCode, item]
	 * @Return void
	 **/
	private void updateDetailEvents(String evtCode,List <PickupLine> item){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,evtCode);
		//add by Jason Zhao
		//避免处理其他内容，先执行清理操作;
		pickupReturnService.cleanDetailEvents(map);
		for (PickupLine line : item) {
			map.put(MapKey.OBJ_CODE,line.getAssetCode());
			map.put(MapKey.DURATION,line.getDuration());
			pickupReturnService.updateDetailEvents(map);
		}
	}
	
	public void updatePickupEvent(PickupOrReturnMessage pickup){
		pickupReturnService.updatePickupEvent(pickup);
	}
	
	public void registerExpressNo(String eventCode,String expressNo) throws Exception{
		if ( StringUtils.isEmpty(eventCode) )
			throw new Exception("单号为空，无法更新快递单号");
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,eventCode);
		map.put(MapKey.EXPRESS_NO,expressNo);
		pickupReturnService
				.registerExpressNo(map);
	}
	
	public void updateDailyItem(String evtCode,List <PickupLine> item,String detailType){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_CODE,evtCode);
		switch(detailType) {
			case Constants.EVT_DETAIL_TYPE_EVENT:
				updateDetailEvents(evtCode,item);
				break;
			case Constants.EVT_DETAIL_TYPE_PART:
				updatePickupItem(evtCode,item);
				break;
			default:
				break;
		}
		
		
	}
	
	public AliResultProcedure createDailyEvent(PickupOrReturnMessage data){
		
		return pickupReturnService.createDailyEvent(data);
	}
	
	public void insertEventFiles(Attachment s){
		pickupReturnService.insertEventFiles(s);
	}

	public String getPersonNamebyCode(String personCode) {
		return pickupReturnService.getPersonNamebyCode(personCode);
	}

    public AliResultProcedure createTransferReturnEvent(PickupOrReturnMessage pickup) {
    	return pickupReturnService.createTransferReturnEvent(pickup);
	}
}