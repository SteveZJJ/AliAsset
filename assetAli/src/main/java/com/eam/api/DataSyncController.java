package com.eam.api;

import com.eam.context.Constants;
import com.eam.context.ExceptionDetails;
import com.eam.context.MapKey;
import com.eam.mybatis.model.*;
import com.eam.mybatis.service.GetService;
import com.eam.utils.AliEamException;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * @Author SteveZhang
 * @Description //数据同步接口如下
 * @Date 2019年1月25日18:52:56
 * @Param
 * @Return
 **/

@Controller
public class DataSyncController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Author Steve Zhang
	 * @Description 人员数据增量同步  --------------停用
	 * @Date 2019年1月25日18:54:29
	 * @Param [request, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/createPersonInfo", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createPersonInfo(HttpServletRequest request,HttpServletResponse response){
		String body = ResponseUtils.ReadAsChars(request);
		String flag = Constants.INTERFACE_ERROR;
		Map<String,Object> map = new HashMap<>();
		R5Personnel person = (R5Personnel)JsonUtils.getObjectFromJson(body,R5Personnel.class);
		ArrayList<R5Personnel> perList = new ArrayList<>();
		perList.add(person);
		try {
			//flag = GetService.getInstance().getMybatisDataSyncService().createPersonInfo(person);
			map.put("person",perList);
			int ins = GetService.getInstance().getMybatisDataSyncService().newPersonInfo(map);
			logger.info("INS = "+ins);
			if(ins>0)
				flag = Constants.INTERFACE_SUCCESS;
			ResponseUtils.writeResponse(response,
					JsonUtils.getJsonFromObject(new AliResponseData(flag,null,person.getPer_code())));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


	/**
	 * @Author Steve Zhang
	 * @Description 人员数据全量同步
	 * @Date 2019年1月25日23:01:35
	 * @Param [corp, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/newPersonInfo", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object newPersonInfo(HttpServletRequest request,HttpServletResponse response){
		String body = ResponseUtils.ReadAsChars(request);
		String flag = Constants.INTERFACE_ERROR;
		R5PersonnelList person = (R5PersonnelList)JsonUtils.getObjectFromJson(body,R5PersonnelList.class);
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("person",person.getPersonList());
			int ins = GetService.getInstance().getMybatisDataSyncService().newPersonInfo(map);
			logger.info("INS = "+ins);
			if(ins>0)
				flag = Constants.INTERFACE_SUCCESS;
			ResponseUtils.writeResponse(response,
					JsonUtils.getJsonFromObject(new AliResponseData(flag,null,ins-1)));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description 部门数据增量同步
	 * @Date 2019年1月25日18:54:29
	 * @Param [request, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/createDepartmentInfo", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createDepartmentInfo(HttpServletRequest request,HttpServletResponse response){
		String body = ResponseUtils.ReadAsChars(request);
		String flag = Constants.INTERFACE_ERROR;
		Map<String,Object> map = new HashMap<>();
		ArrayList<R5Mrcs> mrcList = new ArrayList<>();
		R5Mrcs department = (R5Mrcs)JsonUtils.getObjectFromJson(body,R5Mrcs.class);
		mrcList.add(department);
		try {
			/*停用旧的Service方法*/
//			String flag = GetService.getInstance().getMybatisDataSyncService().createDepartmentInfo(department);
			map.put("department",mrcList);
			int ins = GetService.getInstance().getMybatisDataSyncService().newDepartmentInfo(map);
			logger.info("INS = "+ins);
			if(ins>0)
				flag = Constants.INTERFACE_SUCCESS;
			ResponseUtils.writeResponse(response,
					JsonUtils.getJsonFromObject(new AliResponseData(flag,null,department.getMrc_code())));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description 部门数据全量同步
	 * @Date 2019年1月25日23:01:35
	 * @Param [corp, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/newDepartmentInfo", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object newDepartmentInfo(HttpServletRequest request,HttpServletResponse response){
		String body = ResponseUtils.ReadAsChars(request);
		String flag = Constants.INTERFACE_ERROR;
		R5MrcList department = (R5MrcList)JsonUtils.getObjectFromJson(body,R5MrcList.class);
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("department",department.getMrcList());
			int ins = GetService.getInstance().getMybatisDataSyncService().newDepartmentInfo(map);
			logger.info("INS = "+ins);
			if(ins>0)
				flag = Constants.INTERFACE_SUCCESS;
			ResponseUtils.writeResponse(response,
					JsonUtils.getJsonFromObject(new AliResponseData(flag,null,ins-1)));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


//	/**
//	 * @Author Steve Zhang
//	 * @Description 	getAssetsListByCode 根据资产编号列表或固定资产编号列表查询资产信息（盘点用）
//	 * @Date 2018-10-19 10:53:17
//	 * @Param List<String> 资产编号列表/固定资产编号列表
//	 * @Return List<U5AssetDetails> 资产详情列表
//	 *
//	 */
//
//	@RequestMapping(value = "/getAssetsListByCode", method = { RequestMethod.GET,RequestMethod.POST })
//	public @ResponseBody
//	Object getAssetsListByCode(HttpServletRequest request, String language, HttpServletResponse response) {
//		String body = ResponseUtils.ReadAsChars(request);
//		logger.info(body);
//
//		AssetCodeList data = (AssetCodeList)JsonUtils.getObjectFromJson(body, AssetCodeList.class);
//		language = "ZH";
//		List<String> assetCodeList = data.getCodes();
//		Map<String,Object> map = new HashMap<>();
//
//		try{
//			map.put(MapKey.OBJ_CODE_LIST,assetCodeList);
//			map.put(MapKey.LANGUAGE,language);
//
//			List<U5MyAsset> AssetDetailList = GetService.getInstance().getMybatisCodeDescService().getAssetsListByCodeList(map);
//			ResponseUtils.writeSuccessResponse(response,AssetDetailList);
//		} catch (Exception e){
//			//exception 则表示查询失败，报错误
//			e.printStackTrace();
//			ResponseUtils.writeErrorResponse(response,e.getMessage());
//		}
//
//		return null;
//	}




}
