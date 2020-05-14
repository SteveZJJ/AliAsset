package com.eam.api;

import com.eam.context.Constants;
import com.eam.context.ExceptionDetails;
import com.eam.context.MapKey;
import com.eam.context.UserDefinedField;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * @Author JasonZhao
 * @Description //资产新增接口清单在这里
 * @Date 2018/8/20 6:57
 * @Param
 * @Return
 **/

@Controller
public class QueryCodeDescController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @Author JasonZhao
	 * @Description getAssetCate //获取物料编码
	 * @Date 2018/8/20 19:35
	 * @Param [corp, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getAssetCate", method = { RequestMethod.GET })
	public @ResponseBody
	Object getAssetCate(String corp,String search,String language,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP,StringUtils.isEmpty(corp) ? Constants.NO : corp);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		logger.info(map.toString());
		try {
			List <U5CodeValue> checkList = GetService.getInstance().getMybatisCodeDescService().getAssetCate(map);
			ResponseUtils.writeResponse(response,
										JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,checkList)));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @Author JasonZhao
	 * @Description getUserDefinedField //获取R5USERDEFINEDFIELDLOVVALS
	 * @Date 2018/8/20 下午8:07
	 * @Param [search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getUserDefinedField", method = { RequestMethod.GET })
	public @ResponseBody
	Object getUserDefinedField(String fieldType,String search,String language,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.FIELD,MyUtils.getDefinedField(fieldType).getField());
		map.put(MapKey.ENTITY,MyUtils.getDefinedField(fieldType).getEntity());
		map.put(MapKey.SEARCH_DESC,search);
		try {
			List <U5CodeValue> checkList = GetService.getInstance().getMybatisCodeDescService().getUserDefinedField(map);
			//当查询新增类型的时候，排除S01
			//2018-10-23 21:43:25 展华明确不需要排除S01
			//if ( UserDefinedField.IntialType.name().equals(fieldType) )
				//checkList.removeIf(o -> "S01".equals(o.getCode()));
			ResponseUtils.writeSuccessResponse(response
					,checkList);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/***
	 * @Author JasonZhao
	 * @Description getLocation //获取位置信息
	 * @Date 2018/8/20 下午9:02
	 * @Param [corp, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getLocation", method = { RequestMethod.GET })
	public @ResponseBody
	Object getLocation(String corp,String locationClass, String search,String language,String noCheckFA,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP
				,StringUtils.isEmpty(corp) ? Constants.NO : corp);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put("locationClass",locationClass);
		map.put(MapKey.SEARCH_DESC,search);
		map.put("noCheckFA",noCheckFA);
		logger.info(map.toString());
		try {
			List <U5CodeValue> locations = GetService.getInstance().getMybatisCodeDescService().getLocation(map);
			ResponseUtils.writeSuccessResponse(response
					,locations);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}








	/***
	 * @Author JasonZhao
	 * @Description getLocationInfo //获取位置详细信息
	 * @Date 2018/8/20 下午9:02
	 * @Param [corp, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getLocationInfo", method = { RequestMethod.GET })
	public @ResponseBody
	Object getLocationInfo(String ouCode, String corp, String locationCode,String language,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP
				,StringUtils.isEmpty(corp) ? Constants.NO : corp);
		map.put(MapKey.LOCATION,locationCode);
		map.put(MapKey.OUCODE,ouCode);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));

		logger.info(map.toString());
		try {
			LocationInfo locationInfo = GetService.getInstance().getMybatisCodeDescService().getLocationInfo(map);
			ResponseUtils.writeSuccessResponse(response
					,locationInfo);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}



	/**
	 * @Author JasonZhao
	 * @Description getCurrency //获取币种
	 * @Date 2018/8/22 上午10:45
	 * @Param [search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getCurrency", method = { RequestMethod.GET })
	public @ResponseBody
	Object getCurrency(String search,String language,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		logger.info(map.toString());
		try {
			List <U5CodeValue> locations = GetService.getInstance().getMybatisCodeDescService().getCurrency(map);
			ResponseUtils.writeSuccessResponse(response,locations);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
		
	}
	
	/***
	 * @Author JasonZhao
	 * @Description getBrandByPart //根据物资编码(资产目录)获取品牌
	 * @Date 2018/8/22 上午10:47
	 * @Param [partCode, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getBrandsByPart", method = { RequestMethod.GET })
	public @ResponseBody
	Object getBrandByPart(String partCode,String search,String language,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		String brandText = (language.equals("EN")?"Brand":"品牌");
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.PART_CODE,partCode);
		map.put(MapKey.SEARCH_DESC,search);
		logger.info(map.toString());
		try {
			List <U5CodeValue> brands = GetService.getInstance().getMybatisCodeDescService().getBrandsByPart(map);
			Map<String, Object> modelsMap = new HashMap<>();
			modelsMap.put("assetCatelogue",brands);

			modelsMap.put("name", brandText);
			ResponseUtils.writeSuccessResponse(response,modelsMap);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
		
	}
	
	
	/***
	 * @Author JasonZhao
	 * @Description getModelsByBrand //根据资产类目和品牌代码获取型号
	 * @Date 2018/8/22 上午10:49
	 * @Param [partCode, brand, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getModelsByBrand", method = { RequestMethod.GET })
	public @ResponseBody
	Object getModelsByBrand(String partCode,String brand,String search,String language,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		String modelText = (language.equals("EN")?"Model":"型号");
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.PART_CODE,partCode);
		map.put(MapKey.BRAND,brand);
		map.put(MapKey.SEARCH_DESC,search);
		logger.info(map.toString());
		try {
			List <U5CodeValue> models = GetService.getInstance().getMybatisCodeDescService().getModelsByBrand(map);
			Map<String, Object> modelsMap = new HashMap<>();
			modelsMap.put("assetCatelogue",models);

			modelsMap.put("name", modelText);
			ResponseUtils.writeSuccessResponse(response,modelsMap);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
		
	}
	
	/***
	 * @Author JasonZhao
	 * @Description getDrilldownLocation // 获取分级地点
	 * @Date 2018/8/24 18:24:23
	 * @Param [corp, parentCode, locationClass, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getDrilldownLocation", method = { RequestMethod.GET })
	public @ResponseBody
	Object getDrilldownLocation(String corp,String parentCode,String locationClass,String search,String language,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP,StringUtils.isEmpty(corp) ? Constants.NO : corp);
		map.put(MapKey.OBJ_PARENT,parentCode);
		map.put(MapKey.OBJ_CLASS,StringUtils.isEmpty(locationClass) ? Constants.NO : locationClass);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		logger.info(map.toString());
		try {
			List <R5Locations> locations = GetService.getInstance().getMybatisCodeDescService().getDrilldownLocation(map);
			ResponseUtils.writeSuccessResponse(response,locations);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @Author JasonZhao
	 * @Description //获取检查项类别
	 * @Date 2018/8/24 19:53:10
	 * @Param
	 * @Return
	 **/
	@RequestMapping(value = "/getBorrowType", method = { RequestMethod.GET })
	public @ResponseBody
	Object getBorrowType(String corp,String search,String language,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP,StringUtils.isEmpty(corp) ? Constants.NO : corp);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		logger.info(map.toString());
		try {
			List <BorrowType> borrowTypes = GetService.getInstance().getMybatisCodeDescService().getBorrowType(map);
			ResponseUtils.writeSuccessResponse(response,borrowTypes);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @Author JasonZhao
	 * @Description getAvailableAssets // 获取可以借用、领用的资产
	 * @Date 2018/8/27 15:56:55
	 * @Param [corp, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getAvailableAssets", method = { RequestMethod.GET })
	public @ResponseBody
	Object getAvailableAssets(
			String corp,String location,String assetCategory,String model,String process,String language,String search,HttpServletResponse response
							 ){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP,StringUtils.isEmpty(corp) ? Constants.NO : corp);
		map.put(MapKey.LOCATION,location);
		map.put(MapKey.PART_CODE,assetCategory);
		map.put(MapKey.NORMAL_MODEL,model);
		map.put(MapKey.PROCESS,process);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		logger.info(map.toString());
		try {
			List <PickupOrReturnAsset> assets = GetService.getInstance().getMybatisCodeDescService().getAvailableAssets(map);
			logger.info(assets.toString());
			ResponseUtils.writeSuccessResponse(response,assets);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @Author JasonZhao
	 * @Description relatedUsermodels // 获取流程的常用型号接口
	 * @Date 2018/9/14 11:15:17
	 * @Param [corp, location, assetCategory, model, process, language, search, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/relatedUserModels", method = { RequestMethod.GET })
	public @ResponseBody
	Object relatedUserModels(
			String corp,String location,String assetCategory,String model,String process,String language,String search,HttpServletResponse response
							){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP,StringUtils.isEmpty(corp) ? Constants.NO : corp);
		map.put(MapKey.LOCATION,location);
		map.put(MapKey.PART_CODE,assetCategory);
		map.put(MapKey.NORMAL_MODEL,model);
		map.put(MapKey.PROCESS,process);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		logger.info(map.toString());
		try {
			List <PickupOrReturnAsset> assets = GetService.getInstance().getMybatisCodeDescService().relatedUsermodels(map);
			logger.info(assets.toString());
			ResponseUtils.writeSuccessResponse(response,assets);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	@RequestMapping(value = "/getOwnedAssets", method = { RequestMethod.GET })
	public @ResponseBody
	Object getOwnedAssets(
			String corp,String person,String usage,String search,String fixAssetNo,String assetCode,String sn,String language
			,HttpServletResponse response
						 ){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP,corp);
		map.put(MapKey.USAGE,usage);
		map.put(MapKey.PERSON,StringUtils.isEmpty(person) ? Constants.NO : person);
		map.put(MapKey.FIXED_ASSET_NO,fixAssetNo);
		map.put(MapKey.ASSET_CODE,assetCode);
		map.put(MapKey.SN,sn);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		//logger.info(person);
		//logger.warn(map.toString());
		try {
			List <PickupOrReturnAsset> assets = GetService.getInstance().getMybatisCodeDescService().getOwnedAssets(map);
			ResponseUtils.writeSuccessResponse(response,assets);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	@RequestMapping(value = "/getOwnedAssetCodes", method = { RequestMethod.GET })
	public @ResponseBody
	Object getOwnedAssetCodes(
			String corp,String person,String search,String assetCode
			,HttpServletResponse response
	){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.CORP,corp);
//		map.put(MapKey.USAGE,usage);
		map.put(MapKey.PERSON,StringUtils.isEmpty(person) ? Constants.NO : person);
//		map.put(MapKey.FIXED_ASSET_NO,fixAssetNo);
		map.put(MapKey.ASSET_CODE,assetCode);
//		map.put(MapKey.SN,sn);
//		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		//logger.info(person);
		logger.warn("------------------------getOwnedAssetsCodes--------------------");
		try {
			List <PickupOrReturnAsset> assets = GetService.getInstance().getMybatisCodeDescService().getOwnedAssetCodes(map);
			ResponseUtils.writeSuccessResponse(response,assets);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


	
	/***
	 * @Author JasonZhao
	 * @Description queryConfirmation //查询工作流
	 * @Date 2018/9/3 11:35:27
	 * @Param [corp, taskId, transId, ownerCode, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/queryConfirmation", method = { RequestMethod.GET })
	public @ResponseBody
	Object queryConfirmation(TodoWorkFlow query,HttpServletResponse response){
		try {
			query.setLanguage(MyUtils.getRealLanguage(query.getLanguage()));
			logger.info(query.toString());
			TodoWorkFlow confirm = GetService.getInstance().getMybatisCodeDescService().queryConfirmation(query);
			ResponseUtils.writeSuccessResponse(response,confirm);
		}
		catch (AliEamException e) {
			ResponseUtils
					.writeErrorResponse(response,MyUtils.getErrorMsg(e.getError(),query.getLanguage()));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @Author JasonZhao
	 * @Description getUserGroup // 获取用户组
	 * @Date 2018/9/5 10:35:00
	 * @Param [query, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getUserGroup", method = { RequestMethod.GET })
	public @ResponseBody
	Object getUserGroup(String userId,String language,String corp,HttpServletResponse response){
		Map <String, String> map = new HashMap <>();
		try {
			map.put(MapKey.USERID,StringUtils.isEmpty(userId) ? Constants.NO : userId.toUpperCase());
			map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
			map.put(MapKey.CORP,corp);
			logger.info(map.toString());
			R5Users
					user =
					GetService.getInstance().getMybatisCodeDescService().queryUserMessage(map);
			user.setGroups(GetService.getInstance().getMybatisCodeDescService().queryUserGroups(map));
			ResponseUtils.writeSuccessResponse(response,user);
		}
		catch (Exception e) {
			//exception 则表示查询失败，报错误
			ResponseUtils.writeErrorResponse(response,
											 MyUtils.getErrorMsg(
													 ExceptionDetails.USER_NOT_EXISTS
													 ,map.get(MapKey.LANGUAGE)));
		}
		return null;
	}
	
	/***
	 * @Author JasonZhao
	 * @Description  //
	 * @Date 2018/9/18 15:46:01
	 * @Param
	 * @Return
	 **/
	@RequestMapping(value = "/getTaskDetails", method = { RequestMethod.GET })
	public @ResponseBody
	Object getTaskDetails(String eventCode,String language,String assetMark,HttpServletResponse response){
		Map <String, String> map = new HashMap <>();
		try {
			map.put(MapKey.EVT_CODE,StringUtils.isEmpty(eventCode) ? Constants.NO : eventCode);
			map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
			logger.info(map.toString());
			R5EventsCommon
					events =
					GetService.getInstance().getMybatisCodeDescService().getTaskDetails(map);
			if ( ! isNull(events) ) {
				events.setItem(GetService.getInstance()
									   .getMybatisCodeDescService()
									   .getEventDetails(map,events.getJobType(),assetMark));
				events.setAttachment(GetService.getInstance().getMybatisCodeDescService().getAttachment(eventCode));
			}
			ResponseUtils.writeSuccessResponse(response,events);
		}
		catch (Exception e) {
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * @Author JasonZhao
	 * @Description relatedEvenCode // 获取EAM中的任务单状态历史
	 * @Date  2018/9/18 15:47:46
	 * @Param  [eventCode, language, response]
	 * @Return java.lang.Object
	 **/
	
	@RequestMapping(value = "/relatedEventCode", method = { RequestMethod.GET })
	public @ResponseBody
	Object relatedEventCode(String eventCode,String language,HttpServletResponse response){
		Map <String, String> map = new HashMap <>();
		try {
			map.put(MapKey.EVT_CODE,StringUtils.isEmpty(eventCode) ? Constants.NO : eventCode);
			map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
			logger.info(map.toString());
			List <U5EventsApproveList>
					approveLists =
					GetService.getInstance().getMybatisCodeDescService().getRelatedEventCode(map);
			ResponseUtils.writeSuccessResponse(response,approveLists);
		}
		catch (Exception e) {
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}


	/**
	 * @Author Steve Zhang
	 * @Description 	getAssetsListByCode 根据资产编号列表或固定资产编号列表查询资产信息（盘点用）
	 * @Date 2018-10-19 10:53:17
	 * @Param List<String> 资产编号列表/固定资产编号列表
	 * @Return List<U5AssetDetails> 资产详情列表
	 *
	 */

	@RequestMapping(value = "/getAssetsListByCode", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getAssetsListByCode(HttpServletRequest request, String language, HttpServletResponse response) {
		String body = ResponseUtils.ReadAsChars(request);
		logger.info(body);

		AssetCodeList data = (AssetCodeList)JsonUtils.getObjectFromJson(body, AssetCodeList.class);
		language = "ZH";
		List<String> assetCodeList = data.getCodes();
		Map<String,Object> map = new HashMap<>();

		try{
			map.put(MapKey.OBJ_CODE_LIST,assetCodeList);
			map.put(MapKey.LANGUAGE,language);

			List<U5MyAsset> AssetDetailList = GetService.getInstance().getMybatisCodeDescService().getAssetsListByCodeList(map);
			ResponseUtils.writeSuccessResponse(response,AssetDetailList);
		} catch (Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description 	getAssetsListByTDNCode 根据资产编号列表查询资产信息（天忘需求）
	 * @Date 2018-10-19 10:53:17
	 * @Param List<String> 资产编号列表
	 * @Return List<U5AssetDetails> 资产详情列表
	 *
	 */

	@RequestMapping(value = "/getAssetsListByTDNCode", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getAssetsListByTDNCode(HttpServletRequest request, HttpServletResponse response) {
		String body = ResponseUtils.ReadAsChars(request);
		logger.info(body);

		AssetCodeList data = (AssetCodeList)JsonUtils.getObjectFromJson(body, AssetCodeList.class);

		List<String> assetCodeList = data.getCodes();
		Map<String,Object> map = new HashMap<>();

		try{
			map.put(MapKey.OBJ_CODE_LIST,assetCodeList);
			map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(data.getLanguage()));

			List<U5MyAsset> AssetDetailList = GetService.getInstance().getMybatisCodeDescService().getAssetsListByTDNCodeList(map);
			ResponseUtils.writeSuccessResponse(response,AssetDetailList);
		} catch (Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;
	}


	/**
	 * @Author Steve Zhang
	 * @Description getHEMALineByLineId 根据LineId获取HEMA行行ID
	 * @Date 2018-10-25 15:56:32
	 * @Param String Receipt
	 * @Return List<String> HEMALineId 盒马行行ID列表
	 */

	@RequestMapping(value = "/getHEMALineByLineId", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getHEMALineByLineId(String lineId, HttpServletRequest request, String language, HttpServletResponse response){

		try {
			List<String> HEMALineId =
					GetService.getInstance().getMybatisCodeDescService().getHEMALineByLineId(lineId);

			ResponseUtils.writeSuccessResponse(response,HEMALineId);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description getAssetCorpByCode 根据资产号或员工号查询资产组织
	 * @Date 2019年10月31日10:33:11
	 * @Param String Receipt
	 * @Return 当根据资产查询时为 String 当根据员工号查询时为List<U5CodeValue>
	 */

	@RequestMapping(value = "/getAssetCorpByCode", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getAssetCorpByCode(String assetCode, String workNo, HttpServletRequest request, String language, HttpServletResponse response){

		HashMap<String,String> map = new HashMap<>();
		map.put(MapKey.WORKNO,workNo);
		map.put(MapKey.ASSET_CODE,assetCode);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		try {

			List<U5CodeValue> corp =
					GetService.getInstance().getMybatisCodeDescService().getAssetCorpByCode(map);
			if(assetCode!=null)
				ResponseUtils.writeSuccessResponse(response,corp.get(0).getCode());
			else
				ResponseUtils.writeSuccessResponse(response,corp);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description getScrapTypes 获取报废类型列表
	 * @Date 2019年4月22日15:35:59
	 * @Param String language
	 * @Return List <U5CodeValue> ScrapTypes 报废类型列表
	 */
	@RequestMapping(value = "/getScrapTypes", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object getScrapTypes(String language, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		try{
			List <U5CodeValue> ScrapTypes = GetService.getInstance().getMybatisCodeDescService().getScrapTypes(map);
			ResponseUtils.writeSuccessResponse(response,ScrapTypes);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description getDisposeTypes 获取处置类型列表
	 * @Date 2019年4月22日15:35:59
	 * @Param String language
	 * @Return List <U5CodeValue> DisposeTypes 处置类型列表
	 */
	@RequestMapping(value = "/getDisposeTypes", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object getDisposeTypes(String language, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		try{
			List <U5CodeValue> DisposeTypes = GetService.getInstance().getMybatisCodeDescService().getDisposeTypes(map);
			ResponseUtils.writeSuccessResponse(response,DisposeTypes);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description getSecureTypes 获取安全处理类型列表
	 * @Date 2019年4月22日15:35:59
	 * @Param String language
	 * @Return List <U5CodeValue> SecureTypes 处置类型列表
	 */
	@RequestMapping(value = "/getSecureTypes", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object getSecureTypes(String language, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		try{
			List <U5CodeValue> DisposeTypes = GetService.getInstance().getMybatisCodeDescService().getSecureTypes(map);
			ResponseUtils.writeSuccessResponse(response,DisposeTypes);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}


	/**
	 * @Author Steve Zhang
	 * @Description getSuppliers
	 * @Date 2019年5月31日15:50:41
	 * @Param String language 语言标识
	 * @Return List <U5CodeValue> SupplierList 供应商值列表
	 */
	@RequestMapping(value = "/getSuppliers", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object getSuppliers(String language, String search, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		try{
			List <U5CodeValue> Suppliers = GetService.getInstance().getMybatisCodeDescService().getSuppliers(map);
			ResponseUtils.writeSuccessResponse(response,Suppliers);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;
	}


	/**
	 * @Author Steve Zhang
	 * @Description getSuppliers
	 * @Date 2019年5月31日15:50:41
	 * @Param String language 语言标识
	 * @Return List <U5CodeValue> SupplierList 供应商值列表
	 */
	@RequestMapping(value = "/getTaxRates", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object getTaxRates(String language, String search, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		try{
			List <U5CodeValue> taxRates = GetService.getInstance().getMybatisCodeDescService().getTaxRates(map);
			ResponseUtils.writeSuccessResponse(response,taxRates);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;
	}






	@RequestMapping(value = "/testAPI", method = { RequestMethod.GET })
	public @ResponseBody
	Object testAPI(
			String evtCode
			,HttpServletResponse response
	){
		HashMap <String, String> map = new HashMap <>();
//		map.put(MapKey.CORP,corp);
//		map.put(MapKey.USAGE,usage);
//		map.put(MapKey.PERSON,StringUtils.isEmpty(person) ? Constants.NO : person);
//		map.put(MapKey.FIXED_ASSET_NO,fixAssetNo);
//		map.put(MapKey.ASSET_CODE,assetCode);
//		map.put(MapKey.SN,sn);
//		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
//		map.put(MapKey.SEARCH_DESC,search);
		map.put(MapKey.EVT_CODE,evtCode);
		//logger.info(person);
		logger.info("----------------testAPI-----------------");
		try {
			//List <String> assetCodes = GetService.getInstance().getMybatisCodeDescService().getTestData(map);
			ResponseUtils.writeSuccessResponse(response,"testApi");

		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description getAssetStatusbyLimited
	 * @Date 2019年12月18日10:37:36
	 * @Param String language 语言标识
	 * @Return List <U5CodeValue> assetStatus 根据传入流程所限制的资产状态筛选
	 */
	@RequestMapping(value = "/getAssetStatusbyLimited", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object getAssetStatusbyLimited(String language, String process, String search, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<>();
		map.put(MapKey.PROCESS,process);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		try{
			List <U5CodeValue> assetStatus = GetService.getInstance().getMybatisCodeDescService().getAssetStatusbyLimited(map);
			ResponseUtils.writeSuccessResponse(response,assetStatus);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description getAssetClass
	 * @Date 2019年12月18日10:37:36
	 * @Param String language 语言标识
	 * @Return List <U5CodeValue> assetClass 获取资产分类
	 */
	@RequestMapping(value = "/getAssetClassList", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object getAssetClassList(String language, String search, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.SEARCH_DESC,search);
		try{
			List <U5CodeValue> assetClass = GetService.getInstance().getMybatisCodeDescService().getAssetClassList(map);
			ResponseUtils.writeSuccessResponse(response,assetClass);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Description getAssetCate
	 * @Date 2019年12月18日10:37:36
	 * @Param String language 语言标识
	 * @Return List <U5CodeValue> assetCate 获取资产种类
	 */
	@RequestMapping(value = "/getAssetCateList", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object getAssetCateList(String language, String objClass, String search, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put("objClass",objClass);
		map.put(MapKey.SEARCH_DESC,search);
		try{
			List <U5CodeValue> assetCate = GetService.getInstance().getMybatisCodeDescService().getAssetCateList(map);
			ResponseUtils.writeSuccessResponse(response,assetCate);
		} catch(Exception e){
			//exception 则表示查询失败，报错误
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;
	}

	/***
	 * @Author JasonZhao
	 * @Description getMrcInfo //获取部门详细信息
	 * @Date 2018/8/20 下午9:02
	 * @Param [corp, search, language, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/getMrcInfo", method = { RequestMethod.GET })
	public @ResponseBody
	Object getMrcInfo(String mrcCode, String language,HttpServletResponse response){
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.MRC_CODE,mrcCode);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));

		logger.info(map.toString());
		try {
			R5Mrcs mrc = GetService.getInstance().getMybatisCodeDescService().getMrcInfo(map);
			ResponseUtils.writeSuccessResponse(response
					,mrc);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}



}
