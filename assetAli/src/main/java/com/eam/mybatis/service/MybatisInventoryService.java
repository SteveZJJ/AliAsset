
/*
 * MybatisService.java
 *
 */
package com.eam.mybatis.service;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.dao.InventoryMapper;
import com.eam.mybatis.dao.PickupReturnMapper;
import com.eam.mybatis.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * For db accessor. To store the EHR data to EAM database.
 *
 * @Author JASONZHAO
 */
@Service
public class MybatisInventoryService {
	
	@Autowired
	InventoryMapper inventoryService = null;


	public AliResultProcedure createOrUpdateInventory(InventoryHeader header) {
		return inventoryService.createOrUpdateInventory(header);
	}

	public int queryInventoryAssetNum(InventoryHeader header) {
		return inventoryService.queryInventoryAssetNum(header);
	}

	public void createInventoryParam(InventoryHeader header) {
		inventoryService.createInventoryParam(header);
	}

	public void deleteInventoryParam(InventoryHeader header) {
		inventoryService.deleteInventoryParam(header);
	}

	public String getInventoryStatus(InventoryHeader header) {
		return inventoryService.getInventoryStatus(header);
	}

	public InventoryHeader queryInventoryHeader(HashMap<String, Object> map) {
		InventoryHeader header = inventoryService.queryInventoryHeader(map);
		List<Attachment> files = inventoryService.queryEventFiles(map);
		Attachment dataFile = inventoryService.queryDataFile(map);
		map.put(MapKey.PARAM_FIELD,"objStatus");
		List<String> statusList = inventoryService.getInventoryParam(map);
		map.replace(MapKey.PARAM_FIELD,"objClass");
		List<String> classList = inventoryService.getInventoryParam(map);
		map.replace(MapKey.PARAM_FIELD,"objCate");
		List<String> cateList = inventoryService.getInventoryParam(map);

		header.setAttachments(files);
		header.setDataFile(dataFile);
		header.setObjCate(cateList);
		header.setObjClass(classList);
		header.setObjStatus(statusList);
		return header;
	}

	public List<InventoryList> queryInventoryDetails(HashMap<String, Object> map) {
		List<InventoryList> inventoryList = inventoryService.queryInventoryRespList(map);
		List<InventoryLine> inventoryLine = inventoryService.queryInventoryLineList(map);
		for(InventoryList item : inventoryList){
			item.setEvtCode((String)map.get(MapKey.EVT_CODE));
			item.setCorp((String)map.get(MapKey.CORP));
			List<InventoryLine> lines = new ArrayList<>();
			for(InventoryLine line: inventoryLine){
				if(line.getRespNo().equals(item.getRespWorkNo())){
					lines.add(line);
				}
			}
			item.setAssetList(lines);
		}
		return inventoryList;
	}

	public List<InventoryList> queryInventoryLineList(HashMap<String, Object> map) {
		List<InventoryLine> inventoryLine = inventoryService.queryInventoryLineList(map);
		List<InventoryList> inventoryList = new ArrayList<>();
		InventoryList list = new InventoryList();
		list.setAssetList(inventoryLine);
		list.setEvtCode((String)map.get(MapKey.EVT_CODE));
		list.setCorp((String)map.get(MapKey.CORP));
		inventoryList.add(list);
		return inventoryList;
	}

	public int feedbackInventoryList(InventoryList list) {
		int num = 0;
		for(InventoryLine line :list.getAssetList()){
			//为每一行设置基本参数
			line.setEvtCode(list.getEvtCode());
			if(!isNull(list.getCorp())) {
				line.setCorp(list.getCorp());
			}
			//只有当反馈的说明或反馈状态不为空的时候才更新
			if((!isNull(line.getComment())&&!"".equals(line.getComment()))
				||(!isNull(line.getFeedBackStatus())&&!"".equals(line.getFeedBackStatus()))){
				num += inventoryService.updateInventoryItem(line);
			}
		}
		return num;
	}

}