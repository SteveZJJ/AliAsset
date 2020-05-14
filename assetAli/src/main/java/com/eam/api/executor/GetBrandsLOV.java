package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月19日 上午11:36:18
 * @version 1.0
 */

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.Brands;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings(value = { "rawtypes" })
public class GetBrandsLOV implements Callable {
	
	HttpServletResponse response;
	HashMap <String, String> map = new HashMap <>();
	
	public GetBrandsLOV(String sCode,String search,String language,HttpServletResponse response){
		map.put("sCode",sCode);
		map.put(MapKey.SEARCH_DESC,search);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		this.response = response;
	}
	
	@Override
	public Object call(){
		// 获取资产目录
		String catalogue = GetService.getInstance().getMybatisReceiptService().getCatByPartCode(map);
		// 获取品牌值列表
		List <String> brands = GetService.getInstance().getMybatisReceiptService().getBrandsByParCode(map);
		ResponseUtils.writeResponse(response,JsonUtils.getJsonFromObject(
				new AliResponseData(Constants.INTERFACE_SUCCESS,null,new Brands(catalogue,brands))));
		return null;
	}
	
}
