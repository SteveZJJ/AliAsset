
/*
 * MybatisService.java
 *
 */
package com.eam.mybatis.service;

import com.eam.context.MapKey;
import com.eam.mybatis.dao.ReceiptMapper;
import com.eam.mybatis.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * For db accessor. To store the EHR data to EAM database.
 * Author
 */
@Service
public class MybatisReceiptService {
	// private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ReceiptMapper receiptMapper = null;
	
	public List <R5Parts> getPartsLOV(HashMap <String, String> map){
		return receiptMapper.getPartsLOV(map);
	}
	
	public List <ReceiptBase> getListOfReceipts(HashMap <String, String> map){
		return receiptMapper.getListOfReceipts(map);
	}
	
	public ReceiptDetails getReceiptDetails(HashMap <String, String> map){
		return receiptMapper.getReceiptDetails(map);
	}
	
	public List <ReceiptItem> getReceiptsItem(HashMap <String, String> map){
		return receiptMapper.getReceiptsItem(map);
	}
	
	public String getCatByPartCode(HashMap <String, String> map){
		return receiptMapper.getCatByPartCode(map);
	}
	
	public List <String> getBrandsByParCode(HashMap <String, String> map){
		return receiptMapper.getBrandsByParCode(map);
	}
	
	public List <String> getModelsLOV(HashMap <String, String> map){
		return receiptMapper.getModelsLOV(map);
	}
	
	public List <R5Mrcs> getDepartmentLOV(HashMap <String, String> map){
		return receiptMapper.getDepartmentLOV(map);
	}
	
	public List <R5Stores> getStoreLOV(HashMap <String, String> map){
		return receiptMapper.getStoreLOV(map);
	}
	
	public List <R5ActChecklists> getChecklists(HashMap <String, String> map){
		return receiptMapper.getChecklists(map);
	}
	
	public AliResultProcedure createAssetReceipt(Receipt_CreateHeader header){
		return receiptMapper.createAssetReceipt(header);
	}
	
	public AliResultProcedure createRecvLine(Receipt_CreateLine recvLine){
		return receiptMapper.createRecvLine(recvLine);
	}
	
	public void updatePartOfReceipts(HashMap <String, String> map){
		receiptMapper.updatePartOfReceipts(map);
	}
	
	public AliResultProcedure updateReceiptStatus(HashMap <String, String> map){
		return receiptMapper.updateReceiptStatus(map);
	}
	
	public AliResultProcedure newAssetDetails(R5Objects object){
		return receiptMapper.newAssetDetails(object);
	}
	
	public AliResultProcedure newReceiptDetails(R5ActChecklists checklists){
		return receiptMapper.newReceiptDetails(checklists);
	}
	
	public String getReceiptNoByLineId(String acceptanceLineId,String acceptanceLineOfHEMA,String corp){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP,corp);
		map.put(MapKey.ACCEPTANCE_LINE_ID,acceptanceLineId);
		map.put(MapKey.ACCEPTANCE_LINE_ID_HEMA,acceptanceLineOfHEMA);
		return receiptMapper.getReceiptNoByLineId(map);
	}
	
	public List <U5AssetDetails> getAssetDetails(HashMap <String, String> map){
		return receiptMapper.getAssetDetails(map);
	}
	
	public void updateFixedAssetInfo(HashMap <String, String> map){
		receiptMapper.updateFixedAssetInfo(map);
	}
	
	public String getEvtStatus(String receiptNo){
		return receiptMapper.getEvtStatus(receiptNo);
		
	}
	
	public List <U5MyAsset> getMyAsset(HashMap <String, Object> map){
		return receiptMapper.getMyAsset(map);
	}
	
	public int getMyAssetCount(HashMap <String, Object> map){
		return receiptMapper.getMyAssetCount(map);
	}
	
	public U5MyAsset getMyAssetDetails(HashMap <String, String> map){
		return receiptMapper.getMyAssetDetails(map);
	}
	
	public AliResultProcedure createAssetReceiptManual(Manual_CreateHeader header){
		return receiptMapper.createAssetReceiptManual(header);
	}
	
	public AliResultProcedure newReceiptDetailsManual(ReceiptDetailsManual detail){
		return receiptMapper.newReceiptDetailsManual(detail);
	}
	
	public ReceiptDraft getReceiptDraft(HashMap <String, String> map){
		return receiptMapper.getReceiptDraft(map);
	}
	
	public String[] getAssetCodeByReceiptNo(HashMap <String, String> map){
		return receiptMapper.getAssetCodeByReceiptNo(map);
	}
	
	public AliResponse deleteReceipt(String receiptNo){
		return receiptMapper.deleteReceipt(receiptNo);
	}
	
	public void updateConfirmation(TodoWorkFlow upd){
		receiptMapper.updateConfirmation(upd);
	}
	
	public List <ReceiptBase> getReceiptList(HashMap <String, String> map){
		return receiptMapper.getReceiptList(map);
	}

	public List<String> getManualTDNCode(HashMap<String, Object> map) {
		return receiptMapper.getManualTDNCode(map);
	}

	public AliResultProcedure createAssetReceiptManual_New(Manual_CreateHeader header) {
		return receiptMapper.createAssetReceiptManual_New(header);
	}

	public AliResultProcedure createManualRecvLine(HashMap<String, String> map) {
		return receiptMapper.createManualRecvLine(map);
	}

	public int checkRecvLineStatus(HashMap<String, String> map) {
		return receiptMapper.checkRecvLineStatus(map);
	}

	public AliResultProcedure newReceiptHeader(Receipt_CreateHeader header) {
		return receiptMapper.newReceiptHeader(header);
	}

//    public List<R5AssetPropertyValues> getPropertyValues(HashMap<String, String> map) {
//		List<R5AssetPropertyValues> assetPropertyValuesList = new ArrayList<R5AssetPropertyValues>();
//		String[] assetCodeList = receiptMapper.getAssetCodeByReceiptNoMapping(map);
//		List<R5PropertyValues> propertyValuesList = receiptMapper.getPropertyValues(map);
//		for(int i=0;i<assetCodeList.length;i++){
//			R5AssetPropertyValues assetPropertyValues = new R5AssetPropertyValues();
//			List<R5PropertyValues> propertyValuesList1 = new ArrayList<>();
//			for(int j=0; j<propertyValuesList.size(); j++){
//				if(assetCodeList[i].equals(propertyValuesList.get(j).getObj_code())){
//					propertyValuesList1.add(propertyValuesList.get(j));
//				}
//				assetPropertyValues.setPropertyList(propertyValuesList1);
//			}
//			assetPropertyValues.setObjCode(assetCodeList[i]);
//			assetPropertyValues.setReceiptNo(map.get(MapKey.RECEIPT_NO));
//			assetPropertyValuesList.add(assetPropertyValues);
//		}
//		return assetPropertyValuesList;
//    }
//
//	public List<R5AssetPropertyValues> getPropertyValue(HashMap<String, String> map) {
//		List<R5AssetPropertyValues> assetPropertyValuesList = new ArrayList<R5AssetPropertyValues>();
//		R5AssetPropertyValues assetPropertyValues = new R5AssetPropertyValues();
//		List<R5PropertyValues> propertyValuesList = receiptMapper.getPropertyValue(map);
//		assetPropertyValues.setPropertyList(propertyValuesList);
//		assetPropertyValues.setReceiptNo(map.get(MapKey.RECEIPT_NO));
//		assetPropertyValuesList.add(assetPropertyValues);
//		return assetPropertyValuesList;
//	}

	public List<U5PropertyResult> getErrorProperty(HashMap<String, String> map) {
		return receiptMapper.getErrorProperty(map);
	}

	public int getNextFlagId(HashMap<String,Object> map) {
		return receiptMapper.getNextFlagId(map);
	}

	public int insertAssetCodes(HashMap<String, Object> map) {
		return receiptMapper.insertAssetCodes(map);
	}

	public AliResultProcedure VerifyTDNList(HashMap<String, Object> map) {
		return receiptMapper.VerifyTDNList(map);
	}

	public int getPassedAssetsNum(HashMap<String, Object> map) {
		return receiptMapper.getPassedAssetsNum(map);
	}

	public int getFailedAssetsNum(HashMap<String, Object> map) {
		return receiptMapper.getFailedAssetsNum(map);
	}

	public List<U5MyAsset> getVerifiedAssets(HashMap<String, Object> map) {
		return receiptMapper.getCheckedAssets(map);
	}

	public List<FailAsset> getFailedAssetsList(HashMap<String, Object> map) {

		return receiptMapper.getFailedAssetsList(map);
	}

	public ArrayList<String> getMyAssetCodes(HashMap<String, Object> map) {
		return receiptMapper.getMyAssetCodes(map);
	}

	public AliResultProcedure createRecvLine_New(Receipt_CreateLine recvLine) {
		return receiptMapper.createRecvLine_New(recvLine);
	}

	public List<R5AssetPropertyValues> getAssetRecvItem(HashMap<String, Object> map) {
		List<R5AssetPropertyValues> assetPropertyValuesList = new ArrayList<R5AssetPropertyValues>();
		R5AssetPropertyValues assetPropertyValues = new R5AssetPropertyValues();
		List<R5PropertyValues> propertyValuesList = receiptMapper.getAssetRecvItem(map);
		assetPropertyValues.setPropertyList(propertyValuesList);
		assetPropertyValues.setReceiptNo((String) map.get(MapKey.RECEIPT_NO));
		assetPropertyValuesList.add(assetPropertyValues);
		return assetPropertyValuesList;
	}

	public List<R5AssetPropertyValues> getAssetRecvItems(HashMap<String, Object> map) {
		List<R5AssetPropertyValues> assetPropertyValuesList = new ArrayList<R5AssetPropertyValues>();
		String[] assetCodeList = receiptMapper.getAssetCodeByReceiptNoMapping(map);
		List<R5PropertyValues> propertyValuesList = receiptMapper.getAssetRecvItems(map);
		for(int i=0;i<assetCodeList.length;i++){
			R5AssetPropertyValues assetPropertyValues = new R5AssetPropertyValues();
			List<R5PropertyValues> propertyValuesList1 = new ArrayList<>();
			for(int j=0; j<propertyValuesList.size(); j++){
				if(assetCodeList[i].equals(propertyValuesList.get(j).getObj_code())){
					propertyValuesList1.add(propertyValuesList.get(j));
				}
				assetPropertyValues.setPropertyList(propertyValuesList1);
			}
			assetPropertyValues.setObjCode(assetCodeList[i]);
			assetPropertyValues.setReceiptNo((String) map.get(MapKey.RECEIPT_NO));
			assetPropertyValuesList.add(assetPropertyValues);
		}
		return assetPropertyValuesList;
	}

	public int checkRecvLineItemExisted(HashMap<String, Object> map) {
		return receiptMapper.checkRecvLineItemExisted(map);
	}

	public int clearRecvItems(HashMap<String, Object> map) {
		return receiptMapper.clearRecvItems(map);
	}

	public int setAssetRecvItems(HashMap<String, Object> map) {
		return receiptMapper.setAssetRecvItems(map);
	}

	public AliResultProcedure CreateBatchReceipt(BatchReceiptHeader batchReceiptHeader) {
		return receiptMapper.CreateBatchReceipt(batchReceiptHeader);
	}

    public BatchReceiptHeader getBatchReceiptHeader(HashMap<String, String> map) {
		BatchReceiptHeader batchReceiptHeader =  receiptMapper.getBatchReceiptHeader(map);
		ArrayList<Attachment> files = receiptMapper.queryEventFiles(batchReceiptHeader.getReceiptNo());
		Attachment dataFile = receiptMapper.queryBatchDataFile(batchReceiptHeader.getReceiptNo());
		Attachment bpmsURL =  receiptMapper.queryBpmsUrl(batchReceiptHeader.getReceiptNo());
		batchReceiptHeader.setAttachments(files);
		batchReceiptHeader.setDataFile(dataFile);
		batchReceiptHeader.setBpmsUrl(bpmsURL);
		return batchReceiptHeader;
    }

	public void cleanBatchDataFile(String receiptNo) {
		receiptMapper.cleanBatchDataFile(receiptNo);
	}

	public void insertBatchDataFile(Attachment dataFile) {
		receiptMapper.insertBatchDataFile(dataFile);
	}

	public int cleanBatchAssets(HashMap<String, Object> map) {
		return receiptMapper.cleanBatchAssets(map);
	}

	public int insertBatchAsset(HashMap<String, Object> map) {
		return receiptMapper.insertBatchAsset(map);
	}

	public ArrayList<BatchAsset> getBatchAssets(HashMap<String, Object> map) {
		return receiptMapper.getBatchAssets(map);
	}

	public void insertEventFiles(Attachment a) {
		receiptMapper.insertEventFiles(a);
	}

	public void cleanEventFiles(String evtCode) {
		receiptMapper.cleanEventFiles(evtCode);
	}

	public void cleanBpmsURL(String evtCode) {
		receiptMapper.cleanBpmsURL(evtCode);
	}

	public void insertBpmsURL(Attachment bpmsUrl) {
		receiptMapper.insertBpmsURL(bpmsUrl);
	}

	public int getBatchAssetCount(HashMap<String, Object> map) {
		return receiptMapper.getBatchAssetCount(map);
	}

	public List<BatchReceiptHeader> queryReceiptList(HashMap<String, Object> map) {
		List<BatchReceiptHeader> receiptList = receiptMapper.queryReceiptList(map);
		List<String> evtCodeList = receiptList.stream().map(BatchReceiptHeader::getReceiptNo).collect(Collectors.toList());
		map.put("evtCodeList",evtCodeList);
		List<Attachment> urlList = receiptMapper.queryBpmsUrlList(map);
		for(BatchReceiptHeader brh : receiptList){
			for(Attachment a : urlList){
				if(brh.getReceiptNo().equals(a.getEventCode())){
					if(brh.getBpmsUrl()==null
							||"".equals(brh.getBpmsUrl().getLine())
							||Integer.parseInt(a.getLine())>Integer.parseInt(brh.getBpmsUrl().getLine())){
						brh.setBpmsUrl(a);
					}
				}
			}
		}
		return receiptList;
	}

	public int getReceiptListNum(HashMap<String, Object> map) {
		return receiptMapper.getReceiptListNum(map);
	}

	public List<U5AssetDetails> getBatchAssetDetails(HashMap<String, Object> map) {
		return receiptMapper.getBatchAssetDetails(map);
	}

	public AliResultProcedure createCategoryMapping(CategoryMapping categoryMapping) {
		return receiptMapper.createCategoryMapping(categoryMapping);
	}

	public AliResultProcedure createAssetReceipt_BU(Receipt_CreateHeader header){
		return receiptMapper.createAssetReceipt_BU(header);
	}

	public AliResultProcedure createRecvLine_BU(Receipt_CreateLine recvLine){
		return receiptMapper.createRecvLine_BU(recvLine);
	}

	public AliResultProcedure newReceiptHeader_BU(Receipt_CreateHeader header) {
		return receiptMapper.newReceiptHeader_BU(header);
	}
}
