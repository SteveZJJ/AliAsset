package com.eam.mybatis.dao;

import com.eam.mybatis.model.*;

import java.util.HashMap;
import java.util.List;

public interface ScrapDisposeMapper {

    AliResultProcedure createScrapEvent(ScrapOrDisposeMessage scrap);

	void insertEventFiles(Attachment s);

	void cleanDetailEvents(HashMap<String, Object> map);

	void updateDetailEvents(HashMap<String, Object> map);

	void registerExpressNo(HashMap<String, String> map);

    ScrapOrDisposeMessage queryEventHeader(HashMap<String, String> map);

	List<ScrapLine> queryEventItems(HashMap<String, String> map);

    List<Attachment> queryEventFiles(HashMap<String, String> map);

	void insertQuotationsLines(Quotation s);

	List<Quotation> queryEventQuotations(HashMap<String, String> map);

	List<Attachment> queryQuotationFiles(HashMap<String, String> map);

    int updateFAScrapResult(HashMap<String, String> map);

	void cleanQuotationsLines(String evtCode);

	void cleanQuatationFiles (String evtCode);

	void cleanEventFiles(String evtCode);
}
