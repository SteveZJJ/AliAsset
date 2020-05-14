package com.eam.api.executor;

import com.eam.context.Constants;
import com.eam.mybatis.model.AliResponse;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**************************************
 *@ClassName UpdateFixedAssetInfo
 *@Description 更新资产编号
 *@Author jason
 *@Date 2018/8/4 下午6:59
 *@Version 1.0
 **************************************/
public class UpdateFixedAssetInfo implements Callable <Object> {
	
	HashMap <String, String> map = new HashMap <>();
	HttpServletResponse response;
	
	public UpdateFixedAssetInfo(String assetCode,String fixedAssetCode,HttpServletResponse response){
		map.put("assetCode",assetCode);
		map.put("fixedAssetCode",fixedAssetCode);
		this.response = response;
	}
	
	@Override
	public Object call(){
		try {
			System.out.println(map);
			GetService.getInstance().getMybatisReceiptService().updateFixedAssetInfo(map);
			
			ResponseUtils.writeResponse(response,JsonUtils.getJsonFromObject(new AliResponse(Constants.INTERFACE_SUCCESS,null)));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
}
