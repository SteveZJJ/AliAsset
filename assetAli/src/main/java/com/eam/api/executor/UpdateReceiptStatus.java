package com.eam.api.executor;

import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponse;
import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class UpdateReceiptStatus implements Callable <Object> {
	
	HttpServletResponse response;
	HashMap <String, String> map = new HashMap <>();
	
	public UpdateReceiptStatus(String receiptNo,String language,String approvalResult,HttpServletResponse response){
		map.put(MapKey.EVT_CODE,receiptNo);
		map.put(MapKey.LANGUAGE,language);
		map.put(MapKey.STATUS,approvalResult);
		this.response = response;
		
	}
	
	@Override
	public Object call(){
		AliResultProcedure res1 = GetService.getInstance().getMybatisReceiptService().updateReceiptStatus(map);
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(new AliResponse(res1.getFlag(),res1.getMessage(),res1.getCode())));
		return null;
	}
	
}
