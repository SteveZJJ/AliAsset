package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月18日 下午7:37:47
 * @version 1.0
 */

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings(value = { "rawtypes" })
public class GetModelsLOV implements Callable {
	
	HttpServletResponse response;
	HashMap <String, String> map = new HashMap <>();
	
	public GetModelsLOV(String brand,String search,String language,HttpServletResponse response){
		
		map.put(MapKey.BRAND,brand);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		this.response = response;
	}
	
	@Override
	public Object call(){
		HashMap <String, Object> resMap = new HashMap <>();
		List <String> catalogue = GetService.getInstance().getMybatisReceiptService().getModelsLOV(map);
		resMap.put("assetCatalogue",catalogue);
		resMap.put("name","型号");
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,resMap)));
		return null;
	}
	
}
