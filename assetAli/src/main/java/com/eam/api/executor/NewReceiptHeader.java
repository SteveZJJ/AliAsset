package com.eam.api.executor;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.*;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public class NewReceiptHeader implements Callable <Object> {

	//R5ActChecklists checklists;
	Receipt_CreateHeader header = new Receipt_CreateHeader();
	HttpServletResponse response;



	public NewReceiptHeader(Receipt_CreateHeader header , HttpServletResponse response ) {
		this.header = header;
		this.response = response;
	}
	
	@Override
	public Object call() throws Exception {
		AliResultProcedure res1 = GetService.getInstance().getMybatisReceiptService().newReceiptHeader(header);
		HashMap<String, String> map = new HashMap <>();
		if ( Constants.INTERFACE_SUCCESS.equals(res1.getFlag()) ) {
			map.put(MapKey.RECEIPT_NO,res1.getParam1());
		}
		ResponseUtils.writeResponse(response,
				JsonUtils.getJsonFromObject(new AliResponseData(res1.getFlag(),res1.getMessage(),map)));
		return null;
	}
	
}
