package com.eam.api.executor;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponse;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class UpdatePartOfReceipts implements Callable <Object> {
	
	HttpServletResponse response;
	HashMap <String, String> map = new HashMap <>();
	
	public UpdatePartOfReceipts(String receiptNo,String storeCode,String partCode,HttpServletResponse response){
		
		map.put(MapKey.EVT_CODE,receiptNo);
		map.put(MapKey.PART_CODE,partCode);
		map.put(MapKey.STORE_CODE,storeCode);
		this.response = response;
	}
	
	@Override
	public Object call(){
		try {
			GetService.getInstance().getMybatisReceiptService().updatePartOfReceipts(map);
			ResponseUtils.writeResponse(response,
										JsonUtils.getJsonFromObject(new AliResponse(Constants.INTERFACE_SUCCESS,null)));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
}
