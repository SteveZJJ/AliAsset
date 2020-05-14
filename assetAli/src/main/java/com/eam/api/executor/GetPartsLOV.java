package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月18日 下午7:37:47
 * @version 1.0
 */

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.R5Parts;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings(value = { "rawtypes" })
public class GetPartsLOV implements Callable {
	
	HttpServletResponse response;
	HashMap <String, String> map = new HashMap <>();
	
	public GetPartsLOV(String catalogue,String usageCode,String language,HttpServletResponse response){
		
		map.put("categoryCode",catalogue);
		map.put("usageCode",usageCode);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		this.response = response;
	}
	
	public Object call(){
		List <R5Parts> checkList = GetService.getInstance().getMybatisReceiptService().getPartsLOV(map);
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,checkList)));
		return null;
	}
	
}
