package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月23日 下午3:59:31
 * @version 1.0
 */

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.ReceiptDetails;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

@SuppressWarnings(value = { "rawtypes" })
public class GetReceiptDetails implements Callable {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	HashMap <String, String> map = new HashMap <>();
	HttpServletResponse response;
	
	public  GetReceiptDetails(String receiptNo,String language,HttpServletResponse response){
		map.put(MapKey.RECEIPT_NO,receiptNo);
		map.put(MapKey.LANGUAGE,language);
		this.response = response;
	}
	
	@Override
	public Object call(){
		ReceiptDetails details = GetService.getInstance().getMybatisReceiptService().getReceiptDetails(map);
		if ( details!=null ) {
			details.setItem(GetService.getInstance().getMybatisReceiptService().getReceiptsItem(map));
			logger.info(details.toString());
		}
		
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,details)));
		return null;
	}
	
}
