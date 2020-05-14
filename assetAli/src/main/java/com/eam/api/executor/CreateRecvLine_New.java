package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月23日 下午3:26:09
 * @version 1.0
 */

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.model.Receipt_CreateLine;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

@SuppressWarnings(value = { "rawtypes" })
public class CreateRecvLine_New implements Callable {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Receipt_CreateLine recvLine;
	HttpServletResponse response;

	public CreateRecvLine_New(Receipt_CreateLine recvLine, HttpServletResponse response){
		this.recvLine = recvLine;
		this.response = response;
	}
	
	@Override
	public Object call(){
		logger.info("Create "+recvLine.getQuantity()+" Asset Codes");
		AliResultProcedure res1 = GetService.getInstance().getMybatisReceiptService().createRecvLine_New(recvLine);
		HashMap <String, String> map = new HashMap <>();
		if ( Constants.INTERFACE_SUCCESS.equals(res1.getFlag()) ) {
			map.put(MapKey.RECEIPT_NO,recvLine.getReceiptNo());
			map.put(MapKey.ACCEPTANCE_LINE_ID,recvLine.getAcceptanceLineId());
		}
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(new AliResponseData(res1.getFlag(),res1.getMessage(),map)));
		return null;
	}
	
}
