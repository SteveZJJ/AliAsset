package com.eam.mybatis.dao;

import com.eam.mybatis.model.*;

import java.util.HashMap;
import java.util.List;

public interface InventoryMapper {

	AliResultProcedure createOrUpdateInventory(InventoryHeader header);

	int queryInventoryAssetNum(InventoryHeader header);

	void createInventoryParam(InventoryHeader header);

	void deleteInventoryParam(InventoryHeader header);

	String getInventoryStatus(InventoryHeader header);

	InventoryHeader queryInventoryHeader(HashMap<String, Object> map);

	List<Attachment> queryEventFiles(HashMap<String, Object> map);

	Attachment queryDataFile(HashMap<String, Object> map);

	List<String> getInventoryParam(HashMap<String, Object> map);

	List<InventoryList> queryInventoryRespList(HashMap<String, Object> map);

	List<InventoryLine> queryInventoryLineList(HashMap<String, Object> map);

	int updateInventoryItem(InventoryLine line);
}
