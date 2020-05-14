package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月18日 下午5:35:18
 * @version 1.0
 */

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.ReceiptBase;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public class GetListOfReceipts implements Callable <String> {
	HashMap <String, String> map = new HashMap <>();
	HttpServletResponse response;
	
	public GetListOfReceipts(String receiver,String corp,String language,HttpServletResponse response){
		map.put("receiver",receiver);
		map.put(MapKey.CORP,corp);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		this.response = response;
	}
	
	@Override
	public String call(){
		List <ReceiptBase> checkList = GetService.getInstance().getMybatisReceiptService().getListOfReceipts(map);
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,checkList)));
		return null;
	}
	
}
