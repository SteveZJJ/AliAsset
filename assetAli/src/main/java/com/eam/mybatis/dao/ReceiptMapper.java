package com.eam.mybatis.dao;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月4日 下午5:47:51
 * @version 1.0
 */

import com.eam.mybatis.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReceiptMapper {
	
	List <R5Parts> getPartsLOV(HashMap <String, String> map);
	
	List <ReceiptBase> getListOfReceipts(HashMap <String, String> map);
	
	ReceiptDetails getReceiptDetails(HashMap <String, String> map);
	
	List <ReceiptItem> getReceiptsItem(HashMap <String, String> map);
	
	String getCatByPartCode(HashMap <String, String> map);
	
	List <String> getBrandsByParCode(HashMap <String, String> map);
	
	List <String> getModelsLOV(HashMap <String, String> map);
	
	List <R5Mrcs> getDepartmentLOV(HashMap <String, String> map);
	
	List <R5Stores> getStoreLOV(HashMap <String, String> map);
	
	List <R5ActChecklists> getChecklists(HashMap <String, String> map);
	
	AliResultProcedure createAssetReceipt(Receipt_CreateHeader header);
	
	AliResultProcedure createRecvLine(Receipt_CreateLine recvLine);
	
	void updatePartOfReceipts(HashMap <String, String> map);
	
	AliResultProcedure updateReceiptStatus(HashMap <String, String> map);
	
	AliResultProcedure newAssetDetails(R5Objects object);
	
	AliResultProcedure newReceiptDetails(R5ActChecklists checklists);
	
	String getReceiptNoByLineId(HashMap <String, String> map);
	
	List <U5AssetDetails> getAssetDetails(HashMap <String, String> map);
	
	void updateFixedAssetInfo(HashMap <String, String> map);
	
	String getEvtStatus(String receiptNo);
	
	List <U5MyAsset> getMyAsset(HashMap <String, Object> map);
	
	int getMyAssetCount(HashMap <String, Object> map);
	
	U5MyAsset getMyAssetDetails(HashMap <String, String> map);
	
	AliResultProcedure createAssetReceiptManual(Manual_CreateHeader header);
	
	AliResultProcedure newReceiptDetailsManual(ReceiptDetailsManual detail);
	
	ReceiptDraft getReceiptDraft(HashMap <String, String> map);
	
	String[] getAssetCodeByReceiptNo(HashMap <String, String> map);
	
	AliResponse deleteReceipt(String receiptNo);
	
	void updateConfirmation(TodoWorkFlow upd);
	
	List <ReceiptBase> getReceiptList(HashMap <String, String> map);

	List<String> getManualTDNCode(HashMap<String, Object> map);

	AliResultProcedure createAssetReceiptManual_New(Manual_CreateHeader header);

	AliResultProcedure createManualRecvLine(HashMap<String, String> map);

	int checkRecvLineStatus(HashMap<String, String> map);

    AliResultProcedure newReceiptHeader(Receipt_CreateHeader header);

	String[] getAssetCodeByReceiptNoMapping(HashMap<String, Object> map);

	List<U5PropertyResult> getErrorProperty(HashMap<String, String> map);

    int getNextFlagId(HashMap<String,Object> map);

	int insertAssetCodes(HashMap<String, Object> map);

	AliResultProcedure VerifyTDNList(HashMap<String, Object> map);

	int getPassedAssetsNum(HashMap<String, Object> map);

	int getFailedAssetsNum(HashMap<String, Object> map);

	List<U5MyAsset> getVerifiedAssets(HashMap<String, Object> map);

    List<FailAsset> getFailedAssetsList(HashMap<String, Object> map);

    ArrayList<String> getMyAssetCodes(HashMap<String, Object> map);

    List<U5MyAsset> getCheckedAssets(HashMap<String, Object> map);

	AliResultProcedure createRecvLine_New(Receipt_CreateLine recvLine);

	List<R5PropertyValues> getAssetRecvItem(HashMap<String, Object> map);

	List<R5PropertyValues> getAssetRecvItems(HashMap<String, Object> map);

	int checkRecvLineItemExisted(HashMap<String, Object> map);

	int clearRecvItems(HashMap<String, Object> map);

	int setAssetRecvItems(HashMap<String, Object> map);

    AliResultProcedure CreateBatchReceipt(BatchReceiptHeader batchReceiptHeader);

    BatchReceiptHeader getBatchReceiptHeader(HashMap<String, String> map);

	void cleanBatchDataFile(String receiptNo);

	void insertBatchDataFile(Attachment dataFile);

	ArrayList<Attachment> queryEventFiles(String receiptNo);

	Attachment queryBatchDataFile(String receiptNo);

    int cleanBatchAssets(HashMap<String, Object> map);

	int insertBatchAsset(HashMap<String, Object> map);

	ArrayList<BatchAsset> getBatchAssets(HashMap<String, Object> map);

    void insertEventFiles(Attachment a);

	void cleanEventFiles(String evtCode);

	void cleanBpmsURL(String evtCode);

	void insertBpmsURL(Attachment bpmsUrl);

	Attachment queryBpmsUrl(String receiptNo);

	int getBatchAssetCount(HashMap<String, Object> map);

	List<BatchReceiptHeader> queryReceiptList(HashMap<String, Object> map);

	List<Attachment> queryBpmsUrlList(HashMap<String, Object> map);

	int getReceiptListNum(HashMap<String, Object> map);

    List<U5AssetDetails> getBatchAssetDetails(HashMap<String, Object> map);

	AliResultProcedure createCategoryMapping(CategoryMapping categoryMapping);

    AliResultProcedure createAssetReceipt_BU(Receipt_CreateHeader header);

	AliResultProcedure createRecvLine_BU(Receipt_CreateLine recvLine);

    AliResultProcedure newReceiptHeader_BU(Receipt_CreateHeader header);
}
