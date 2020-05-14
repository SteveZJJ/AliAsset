package com.eam.mybatis.service;

import com.eam.context.Constants;
import com.eam.context.EventDefault;
import com.eam.context.ExceptionDetails;
import com.eam.context.MapKey;
import com.eam.mybatis.dao.QueryCodeDescMapper;
import com.eam.mybatis.model.*;
import com.eam.utils.AliEamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

/**************************************
 *@ClassName MybatisNewAssetReceiptService
 *@Description 资产手动新增接口Service
 *@Author jason
 *@Date 2018/8/20 下午7:08
 *@Version 1.0
 **************************************/
@Service
public class MybatisCodeDescService {
	
	@Autowired
	QueryCodeDescMapper queryCodeDescService = null;
	
	
	public List <U5CodeValue> getAssetCate(HashMap <String, String> map){
		return queryCodeDescService.getAssetCate(map);
	}
	
	public List <U5CodeValue> getUserDefinedField(HashMap <String, String> map){
		return queryCodeDescService.getUserDefinedField(map);
	}
	
	public List <U5CodeValue> getLocation(HashMap <String, String> map){
		return queryCodeDescService.getLocation(map);
	}
	
	public List <U5CodeValue> getCurrency(HashMap <String, String> map){
		return queryCodeDescService.getCurrency(map);
	}
	
	public List <U5CodeValue> getPropertyValuesByAssetCode(HashMap <String, String> map){
		return queryCodeDescService.getPropertyValuesByAssetCode(map);
	}
	
	public List <U5CodeValue> getBrandsByPart(HashMap <String, String> map){
		
		return queryCodeDescService.getBrandsByPart(map);
	}
	
	public List <U5CodeValue> getModelsByBrand(HashMap <String, String> map){
		return queryCodeDescService.getModelsByBrand(map);
	}
	
	/**
	 * @Author JasonZhao
	 * @Description getPickupEvtCodeByReqCode //根据人力系统入职单号查看Eam入职领用单号
	 * @Date 2018/8/23 下午2:16
	 * @Param [reqCode]
	 * @Return java.lang.String
	 **/
	public String getPickupEvtCodeByReqCode(String reqCode,String evtJobType){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_UDF_CHAR01,reqCode);
		map.put(MapKey.EVT_JOB_TYPE,evtJobType);
		return getEvtCodeByMap(map);
	}
	
	private String getEvtCodeByMap(HashMap <String, String> map){
		return queryCodeDescService.getEvtCodeByMap(map);
	}
	
	public int checkPartExists(String partCode){
		return queryCodeDescService.checkPartsExists(partCode);
	}
	
	public List <R5Locations> getDrilldownLocation(HashMap <String, String> map){
		return queryCodeDescService.getDrilldownLocation(map);
	}
	
	public List <BorrowType> getBorrowType(HashMap <String, String> map){
		return queryCodeDescService.getBorrowType(map);
	}
	
	public List <PickupOrReturnAsset> getAvailableAssets(HashMap <String, String> map){
		return queryCodeDescService.getAvailableAssets(map);
	}
	
	public List <PickupOrReturnAsset> getOwnedAssets(HashMap <String, String> map){
		return queryCodeDescService.getOwnedAssets(map);
	}
	
	public TodoWorkFlow queryConfirmation(TodoWorkFlow query) throws AliEamException{
		if ( StringUtils.isEmpty(query.getEamTaskId()) )
			throw new AliEamException(ExceptionDetails.TaskId_EMPTY);
		if ( StringUtils.isEmpty(query.getEamTransactionId()) )
			throw new AliEamException(ExceptionDetails.TransactionId_EMPTY);
		//if ( StringUtils.isEmpty(query.getOwnerNo()) )
		//	throw new AliEamException(ExceptionDetails.OwnerNO_EMPTY);
		//if ( StringUtils.isEmpty(query.getConfirmationType()) )
		//	throw new AliEamException(ExceptionDetails.ConfirmationType_EMPTY);
		TodoWorkFlow flow = queryCodeDescService.queryTodoWorkFlow(query);
		flow.setItem(queryCodeDescService.queryTodoWorkFlowDetails(query));
		return flow;
	}
	
	public R5Users queryUserMessage(Map <String, String> map){
		
		return queryCodeDescService.queryUserMessage(map);
	}
	
	public List <R5UserGroup> queryUserGroups(Map <String, String> map){
		return queryCodeDescService.queryUserGroups(map);
	}
	
	public String checkDefaultPartByNormal(String normalModel){
		return queryCodeDescService.checkDefaultPartByNormal(normalModel);
	}
	
	public String checkedObjectOk(PickupOrReturnMessage pickup){
		StringBuilder checkString = new StringBuilder();
		String jobType = pickup.getEvtJobType();
		try {
			pickup.getItem().forEach(s -> {
										 //if ( ( jobType.equals(EventDefault.XJ.getEvtJobType()) && checkCanXJCount(s.getAssetCode())==0 )
										 //		  || ( jobType.equals(EventDefault.GH.getEvtJobType()) && checkCanGHCount(s.getAssetCode())==0 )
										 //)
										 if ( Constants.CHECKBOX_UNCHECKED.equals(checkCanMove(jobType,s.getAssetCode())) )
											 checkString.append(s.getAssetCode()).append(",");
									 }
									);
			
		}
		catch (Exception ignored) {
		
		}
		return checkString.toString();
	}
	
	private String checkCanMove(String jobType,String assetCode){
		Map <String, String> map = new HashMap <>();
		map.put(MapKey.EVT_JOB_TYPE,jobType);
		map.put(MapKey.ASSET_CODE,assetCode);
		String xx = queryCodeDescService.checkObjCanMove(map);
		System.out.println(xx + "\n\n\n\n");
		return xx;
	}
	
	
	public R5EventsCommon getTaskDetails(Map <String, String> map){
		return queryCodeDescService.getTaskDetails(map);
	}
	
	public List <Attachment> getAttachment(String evtCode){
		return queryCodeDescService
					   .getAttachment(evtCode);
	}
	
	public List <R5EventsDetails> getEventDetails(Map <String, String> map,String jobType,String assetMark){
		List <R5EventsDetails> details = new ArrayList <>();
		for (EventDefault evt : EventDefault.values()) {
			if ( evt.getEvtJobType().equals(jobType) ) {
				switch(evt.getDetailType()) {
					case Constants.EVT_DETAIL_TYPE_PART:
						//从备件计划里面获取清单
						details = queryCodeDescService.getEventDetails_Part(map);
						if ( ! isNull(details) && Constants.FLAG_Y.equals(assetMark) )
							//从交易表里面获取详情
							details.forEach(s -> {
								map.put(MapKey.PART_CODE,s.getPartCode());
								s.setAssets(queryCodeDescService.getDetailsFromTransAction(map));
							});
						break;
					case Constants.EVT_DETAIL_TYPE_EVENT:
						//从子工单获取清单
						details = queryCodeDescService.getEventDetails_Event(map);
						if ( ! isNull(details) && Constants.FLAG_Y.equals(assetMark) )
							//从子工单获取详情
							details.forEach(s -> {
								map.put(MapKey.PART_CODE,s.getPartCode());
								s.setAssets(queryCodeDescService.getDetailsFromEvents(map));
							});
						break;
					default:
						return null;
				}
			}
		}
		return isNull(details) ? new ArrayList <>() : details;
	}
	
	public List <PickupOrReturnAsset> relatedUsermodels(HashMap <String, String> map){
		
		return queryCodeDescService.relatedUsermodels(map);
	}
	
	public List <U5EventsApproveList> getRelatedEventCode(Map <String, String> map){
		
		return queryCodeDescService.getRelatedEventCode(map);
	}

	public List<U5MyAsset> getAssetsListByCodeList(Map<String, Object> map) {

		return queryCodeDescService.getAssetsListByCodeList(map);

	}

    public List<String> getHEMALineByLineId(String lineId) {

		return queryCodeDescService.getHEMALineByLineId(lineId);
    }

    public List<U5CodeValue> getAssetCorpByCode(HashMap<String, String> map) {
		return queryCodeDescService.getAssetCorpByCode(map);
    }

    public List<U5CodeValue> getScrapTypes(HashMap<String, String> map) {
		return queryCodeDescService.getScrapTypes(map);
    }

	public List<U5CodeValue> getDisposeTypes(HashMap<String, String> map) {
		return queryCodeDescService.getDisposeTypes(map);
	}

    public List<U5CodeValue> getSecureTypes(HashMap<String, String> map) {
		return queryCodeDescService.getSecureTypes(map);
    }

    public List<U5CodeValue> getSuppliers(HashMap<String, String> map) {
    	return queryCodeDescService.getSuppliers(map);
	}

	public List<PickupOrReturnAsset> getOwnedAssetCodes(HashMap<String, String> map) {
		return queryCodeDescService.getOwnedAssetCodes(map);
	}

	public List<String> getTestData(HashMap<String, String> map) {
		return queryCodeDescService.getTestData(map);
	}

	public List<U5CodeValue> getTaxRates(HashMap<String, String> map) {
		return queryCodeDescService.getTaxRates(map);
	}

	public LocationInfo getLocationInfo(HashMap<String, String> map) {
		LocationInfo locationInfo = queryCodeDescService.getLocationInfo(map);
		ArrayList<LocationOU> locationOUs = queryCodeDescService.getLocationOUs(map);
		if(locationInfo!=null)
			locationInfo.setLocationOUs(locationOUs);
		return locationInfo;
	}

    public List<U5MyAsset> getAssetsListByTDNCodeList(Map<String, Object> map) {
		return queryCodeDescService.getAssetsListByTDNCodeList(map);
    }

    public ArrayList<R5Orgnazition> getCorpList() {
		return queryCodeDescService.getCorpList();
    }

	public ArrayList<R5Parts> getPartList() {
		return queryCodeDescService.getPartList();
	}

	public ArrayList<R5Manufactures> getBrandList() {
		return queryCodeDescService.getBrandList();
	}

	public ArrayList<Models> getModelList() {
		return queryCodeDescService.getModelList();
	}

	public ArrayList<ObjStatus> getobjStatusList() {
		return queryCodeDescService.getobjStatusList();
	}

	public ArrayList<R5Currency> getCurrencyList() {
		return queryCodeDescService.getCurrencyList();
	}

	public ArrayList<R5Mrcs> getMrcList() {
		return queryCodeDescService.getMrcList();
	}

	public ArrayList<R5Personnel> getPersonList() {
		return queryCodeDescService.getPersonList();
	}

    public List<U5CodeValue> getAssetStatusbyLimited(HashMap<String, String> map) {
    	return queryCodeDescService.getAssetStatusbyLimited(map);
	}

	public List<U5CodeValue> getAssetClassList(HashMap<String, String> map) {
		return queryCodeDescService.getAssetClassList(map);
	}

	public List<U5CodeValue> getAssetCateList(HashMap<String, String> map) {
		return queryCodeDescService.getAssetCateList(map);
	}

    public List<U5CodeValue> getLocationAll(HashMap<String, String> map) {
		return queryCodeDescService.getLocationAll(map);
    }

    public ArrayList<String> getAssetCodeList() {
		return queryCodeDescService.getAssetCodeList();
    }

	public List<AssetCategory> getAssetCategoryList() {
		return queryCodeDescService.getAssetCategoryList();
	}

	public R5Mrcs getMrcInfo(HashMap<String, String> map) {
		return queryCodeDescService.getMrcInfo(map);
	}
}
