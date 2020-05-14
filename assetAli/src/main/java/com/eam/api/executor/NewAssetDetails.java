package com.eam.api.executor;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.model.R5Objects;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class NewAssetDetails implements Callable <Object> {
	
	R5Objects object;
	HttpServletResponse response;
	
	public NewAssetDetails(R5Objects object,HttpServletResponse response){
		this.object = object;
		this.response = response;
	}
	
	@Override
	public Object call(){
		AliResultProcedure res1 = GetService.getInstance().getMybatisReceiptService().newAssetDetails(object);
		HashMap <String, String> map = new HashMap <>();
		if ( Constants.INTERFACE_SUCCESS.equals(res1.getFlag()) ) {
			map.put("assetCode",res1.getParam2());
			map.put(MapKey.RECEIPT_NO
					,res1.getParam1());
		}
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(new AliResponseData(res1.getFlag(),res1.getMessage(),map)));
		return null;
	}
	
}
