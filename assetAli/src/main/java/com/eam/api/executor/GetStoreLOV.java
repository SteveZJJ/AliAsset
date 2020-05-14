package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月19日 下午1:00:43
 * @version 1.0
 */

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.R5Stores;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings(value = { "rawtypes" })
public class GetStoreLOV implements Callable {
	
	HashMap <String, String> map = new HashMap <>();
	HttpServletResponse response;
	
	public GetStoreLOV(String workNo, String corp,String search,String language,String noCheckFA,HttpServletResponse response){
		map.put(MapKey.WORKNO,workNo);
		map.put(MapKey.ORG,corp);
		map.put(MapKey.SEARCH_DESC,search);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put("noCheckFA",noCheckFA);
		this.response = response;
	}
	
	@Override
	public Object call(){
		List <R5Stores> details = GetService.getInstance().getMybatisReceiptService().getStoreLOV(map);
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,details)));
		return null;
	}
	
}
