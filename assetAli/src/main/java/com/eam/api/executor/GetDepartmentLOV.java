package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月19日 下午12:51:51
 * @version 1.0
 */

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.R5Mrcs;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings(value = { "rawtypes" })
public class GetDepartmentLOV implements Callable {
	
	HttpServletResponse response;
	HashMap <String, String> map = new HashMap <>();
	
	public GetDepartmentLOV(String language,String search,HttpServletResponse response){
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		this.response = response;
	}
	
	@Override
	public Object call(){
		List <R5Mrcs> mrcs = GetService.getInstance().getMybatisReceiptService().getDepartmentLOV(map);
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,mrcs)));
		return null;
	}
	
}
