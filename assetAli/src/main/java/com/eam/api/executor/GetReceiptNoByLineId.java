package com.eam.api.executor;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class GetReceiptNoByLineId implements Callable <Object> {
	
	HttpServletResponse response;
	private String acceptanceLineId;
	private String acceptanceLineOfHEMA;
	private String corp;
	
	public GetReceiptNoByLineId(String acceptanceLineId,String acceptanceLineOfHEMA,String corp,HttpServletResponse response){
		this.acceptanceLineId = acceptanceLineId;
		this.acceptanceLineOfHEMA = acceptanceLineOfHEMA;
		this.corp = corp;
		this.response = response;
	}
	
	@Override
	public Object call(){
		
		String receiptNo = GetService.getInstance().getMybatisReceiptService().getReceiptNoByLineId(acceptanceLineId,acceptanceLineOfHEMA,corp);
		
		HashMap <String, String> map = new HashMap <>();
		if ( ! StringUtils.isEmpty(receiptNo) ) {
			map.put(MapKey.RECEIPT_NO,receiptNo);
			map.put(MapKey.STATUS,GetService.getInstance().getMybatisReceiptService().getEvtStatus(receiptNo));
			map.put(MapKey.ACCEPTANCE_LINE_ID,acceptanceLineId);
			ResponseUtils.writeResponse(response,
										JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,map)));
		} else {
			ResponseUtils.writeResponse(response,
										JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_ERROR,"验收单信息不存在",map)));
		}
		return null;
	}
	
}
