package com.eam.mybatis.dao;

import com.eam.mybatis.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QueryCodeDescMapper {
	List <U5CodeValue> getAssetCate(HashMap <String, String> map);
	
	List <U5CodeValue> getUserDefinedField(HashMap <String, String> map);
	
	List <U5CodeValue> getLocation(HashMap <String, String> map);
	
	List <U5CodeValue> getCurrency(HashMap <String, String> map);
	
	List <U5CodeValue> getPropertyValuesByAssetCode(HashMap <String, String> map);
	
	List <U5CodeValue> getBrandsByPart(HashMap <String, String> map);
	
	List <U5CodeValue> getModelsByBrand(HashMap <String, String> map);
	
	String getEvtCodeByMap(HashMap <String, String> map);
	
	int checkPartsExists(String partCode);
	
	List <R5Locations> getDrilldownLocation(HashMap <String, String> map);
	
	List <BorrowType> getBorrowType(HashMap <String, String> map);
	
	List <PickupOrReturnAsset> getAvailableAssets(HashMap <String, String> map);
	
	List <PickupOrReturnAsset> getOwnedAssets(HashMap <String, String> map);
	
	List <R5UserGroup> queryUserGroups(Map <String, String> map);
	
	R5Users queryUserMessage(Map <String, String> map);
	
	TodoWorkFlow queryTodoWorkFlow(TodoWorkFlow query);
	
	List <TodoWorkFlowDetail> queryTodoWorkFlowDetails(TodoWorkFlow query);
	
	String checkDefaultPartByNormal(String normalModel);
	
	R5EventsCommon getTaskDetails(Map <String, String> map);
	
	List <Attachment> getAttachment(String evtCode);
	
	List <R5EventsDetails> getEventDetails_Part(Map <String, String> map);
	
	List <R5EventsDetails> getEventDetails_Event(Map <String, String> map);
	
	List <ReceiptItem> getDetailsFromEvents(Map <String, String> map);
	
	List <ReceiptItem> getDetailsFromTransAction(Map <String, String> map);
	
	String checkObjCanMove(Map <String, String> map);
	
	List <PickupOrReturnAsset> relatedUsermodels(HashMap <String, String> map);
	
	List <U5EventsApproveList> getRelatedEventCode(Map <String, String> map);

	List<U5MyAsset> getAssetsListByCodeList(Map<String, Object> map);

	List<String> getHEMALineByLineId(String lineId);

    List<U5CodeValue> getAssetCorpByCode(HashMap<String, String> assetCode);

    List<U5CodeValue> getScrapTypes(HashMap<String, String> map);

	List<U5CodeValue> getDisposeTypes(HashMap<String, String> map);

    List<U5CodeValue> getSecureTypes(HashMap<String, String> map);

    List<U5CodeValue> getSuppliers(HashMap<String, String> map);

    List<PickupOrReturnAsset> getOwnedAssetCodes(HashMap<String, String> map);

	List<String> getTestData(HashMap<String, String> map);

    List<U5CodeValue> getTaxRates(HashMap<String, String> map);

    LocationInfo getLocationInfo(HashMap<String, String> map);

	ArrayList<LocationOU> getLocationOUs(HashMap<String, String> map);

    List<U5MyAsset> getAssetsListByTDNCodeList(Map<String, Object> map);

    ArrayList<R5Orgnazition> getCorpList();

	ArrayList<R5Parts> getPartList();

	ArrayList<R5Manufactures> getBrandList();

	ArrayList<Models> getModelList();

	ArrayList<ObjStatus> getobjStatusList();

	ArrayList<R5Currency> getCurrencyList();

	ArrayList<R5Mrcs> getMrcList();

	ArrayList<R5Personnel> getPersonList();

    List<U5CodeValue> getAssetStatusbyLimited(HashMap<String, String> map);

	List<U5CodeValue> getAssetClassList(HashMap<String, String> map);

	List<U5CodeValue> getAssetCateList(HashMap<String, String> map);

    List<U5CodeValue> getLocationAll(HashMap<String, String> map);

    ArrayList<String> getAssetCodeList();

    List<AssetCategory> getAssetCategoryList();

    R5Mrcs getMrcInfo(HashMap<String, String> map);
}
