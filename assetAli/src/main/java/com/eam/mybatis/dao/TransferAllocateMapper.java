package com.eam.mybatis.dao;

import com.eam.mybatis.model.*;

import java.util.HashMap;
import java.util.List;

public interface TransferAllocateMapper {

    AliResultProcedure createTransferEvent(TransferOrAllocateMessage scrap);

	void insertEventFiles(Attachment s);

	void cleanDetailEvents(HashMap<String, String> map);

	void updateDetailEvents(TransferLine line);

	void registerExpressNo(HashMap<String, String> map);

    TransferOrAllocateMessage queryEventHeader(HashMap<String, String> map);

	List<TransferLine> queryEventItems(HashMap<String, String> map);

	void updateRequistionItems(HashMap<String, String> map);

	TransferOrAllocateMessage queryRequiHeader(HashMap<String, String> map);

	List<TransferLine> queryRequiItems(HashMap<String, String> map);

    List<LineSummary> queryRequiSummary(HashMap<String, String> map);

	List<LineSummary> queryEventSummary(HashMap<String, String> map);

    String getEvtJobtype(String evtCode);

	void cleanRequistionLines(HashMap<String, String> map);

    AliResultProcedure updateEventLine(TransferLine line);

	List<TransferLine> queryEventLines(HashMap<String, String> map);

	List<Attachment> queryEventFiles(HashMap<String, String> map);
}
