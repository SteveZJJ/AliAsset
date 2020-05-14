package com.eam.api;

import clojure.lang.Cons;
import com.eam.api.executor.*;
import com.eam.context.ConfigService;
import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.context.UserDefinedField;
import com.eam.mybatis.model.*;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月18日 下午5:24:25
 * @version 1.0
 */

@Controller
@SuppressWarnings(value = { "unchecked","rawtypes","unused" })
public class ReceiptController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	/***
	 * --------------------------------------------------------------
	 *
	 * @Author Jason.Zhao
	 * @Date 2018年7月26日 上午11:33:12
	 * @Description (函数功能 ： 创建验收单信息)
	 * @Param request
	 * @Param response
	 * @return java.lang.Object --------------------------------------------------------------
	 */
	@RequestMapping(value = "/createAssetReceipt", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createAssetReceipt(HttpServletRequest request,HttpServletResponse response){
		try {
			
			Receipt_CreateHeader header = (Receipt_CreateHeader) JsonUtils
																		 .getObjectFromJson(ResponseUtils.ReadAsChars(request),Receipt_CreateHeader.class);
			logger.info(header.toString());
			Callable task = new CreateAssetReceipt(header,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


	/***
	 *
	 * @Author Jason.Zhao
	 * @Date 2018年7月26日 上午11:32:42
	 * @Description (函数功能 ： 创建验收行信息)
	 * @Param request
	 * @Param response
	 * @return java.lang.Object
	 */
	@RequestMapping(value = "/createRecvLine", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createRecvLine(HttpServletRequest request,HttpServletResponse response){
		try {
			
			Receipt_CreateLine recvLine = (Receipt_CreateLine) ResponseUtils.ReadAsObjects(request,Receipt_CreateLine.class);
			logger.info(recvLine.toString());
			Callable task = new CreateRecvLine(recvLine,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	/***
	 * ----------------性能优化----------------
	 * @Author Steve Zhang
	 * @Date 2019年8月1日16:43:43
	 * @Description (函数功能 ： 创建验收行信息)
	 * @Param request
	 * @Param response
	 * @return java.lang.Object
	 */
	@RequestMapping(value = "/createRecvLine_New", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createRecvLine_New(HttpServletRequest request,HttpServletResponse response){
		try {

			Receipt_CreateLine recvLine = (Receipt_CreateLine) ResponseUtils.ReadAsObjects(request,Receipt_CreateLine.class);
			logger.info(recvLine.toString());
			Callable task = new CreateRecvLine_New(recvLine,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/***
	 *
	 * @Param receiptNo
	 *            验收单号
	 * @Param storeCode
	 *            仓库ID
	 * @Param partCode
	 *            物料ID
	 * @Param response
	 * @return java.lang.Object
	 */
	@RequestMapping(value = "/updatePartOfReceipts", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object updatePartOfReceipts(
			String receiptNo,String storeCode,String partCode,HttpServletRequest request,
			HttpServletResponse response
							   ){
		try {
//			UpdateRecvInfo info = (UpdateRecvInfo)JsonUtils.getObjectFromJson(ResponseUtils.ReadAsChars(request),UpdateRecvInfo.class);
//			Callable task = new UpdatePartOfReceipts(info,response);
			Callable task = new UpdatePartOfReceipts(receiptNo,storeCode,partCode,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @return java.lang.Object
	 * @Param workNo   验收人
	 * @Param corp     组织
	 * @Param language 语言 EN/ZH
	 * @Param response
	 * @author Jason.Zhao
	 * @Description :( 获取待处理验收单列表)
	 * @since 2018年7月18日 下午5:34:05
	 */
	@RequestMapping(value = "/getListOfReceipts", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getListOfReceipts(
			String workNo,String corp,String language,
			HttpServletResponse response
							){
		try {
			logger.info(corp);
			Callable task = new GetListOfReceipts(workNo,corp,language,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/***
	 *
	 * @author Jason.Zhao
	 * @since 2018年7月23日 下午4:48:58
	 * @Description :( 获取验收单详细信息)
	 * @Param receiptNo 验收单号
	 * @Param language
	 *            //语言
	 * @Param response
	 * @return java.lang.Object
	 */
	@RequestMapping(value = "/getReceiptDetails", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getReceiptDetails(String receiptNo,String language,HttpServletResponse response){
		try {
			Callable task = new GetReceiptDetails(receiptNo,language,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}



	/***
	 *
	 * @author Steve Zhang
	 * @since 2018-12-21 16:06:56
	 * @Description :（测试新手工验收单生成计划）
	 * @Param receiptNo 验收单号
	 * @Param language
	 *            //语言
	 * @Param response
	 * @return java.lang.Object
	 */
	@RequestMapping(value = "/getReceiptManualDetails", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getReceiptManualDetails(String receiptNo,String language,String single,HttpServletResponse response){
		try {
			Callable task = new GetReceiptManualDetails(receiptNo,language,single,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}



	/**
	 * @return java.lang.Object
	 * @Param categoryCode //采购类目(描述)
	 * @Param user         //用户,
	 * @Param language     // 语言 。中文/英文 zh,en
	 * @Param response
	 * @author Jason.Zhao
	 * @Description :( 根据采购类目获取对应的物资编码)
	 * @since 2018年7月18日 下午7:48:00
	 */
	@RequestMapping(value = "/getPartsLOV", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getPartsLOV(
			String categoryCode,String usageCode,String user,String language,
			HttpServletResponse response
					  ){
		try {
			logger.info(String.format("categoryCode:[%s] usageCode:[%s]",categoryCode,usageCode));
			Callable task = new GetPartsLOV(categoryCode,usageCode,language,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @return java.lang.Object
	 * @Param partCode-- EAM编码
	 * @Param language   -- 语言
	 * @Param search     -- 模糊搜索
	 * @Param response
	 * @author Jason.Zhao
	 * @Description :( 根据资产，获取资产类目及品牌值列表)
	 * @since 2018年7月19日 上午11:38:09
	 */
	
	@RequestMapping(value = "/getBrandsLOV", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getBrandsLOV(String partCode,String language,String search,HttpServletResponse response){
		try {
			Callable task = new GetBrandsLOV(partCode,search,language,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/****
	 *
	 * @author Jason.Zhao
	 * @since 2018年7月19日 下午12:17:01
	 * @Description :( 与产品联动的型号值列表)
	 * @Param brand
	 * @Param language
	 * @Param response
	 * @return java.lang.Object
	 */
	@RequestMapping(value = "/getModelsLOV", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getModelsLOV(String brand,String search,String language,HttpServletResponse response){
		try {
			Callable task = new GetModelsLOV(brand,search,language,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	@RequestMapping(value = "/getChecklists", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getChecklists(
			String corp,String assetCode,String receiptNo,String language,
			HttpServletResponse response
						){
		try {
			Callable task = new GetChecklists(corp,assetCode,receiptNo,language,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	@RequestMapping(value = "/getDepartmentLOV", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getDepartmentLOV(String language,String search,HttpServletResponse response){
		try {
			Callable task = new GetDepartmentLOV(language,search,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	@RequestMapping(value = "/getStoreLOV", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getStoreLOV(String workNo, String corp,String search,String language,String noCheckFA,HttpServletResponse response){
		try {
			Callable task = new GetStoreLOV(workNo, corp,search,language,noCheckFA,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateReceiptStatus", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object updateReceiptStatus(String receiptNo,String approvalResult,String language,HttpServletResponse response){
		try {
			Callable task = new UpdateReceiptStatus(receiptNo,language,approvalResult,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	
	/**
	 * @return java.lang.Object java.lang.Object
	 * @Author JasonZhao
	 * @Description newAssetDetails //(函数功能 ： 更新资产信息)
	 * @Date 2018/8/21 下午12:08
	 * @Param [request, response]
	 **/
	@RequestMapping(value = "/newAssetDetails", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object newAssetDetails(HttpServletRequest request,HttpServletResponse response){
		try {
			String body = ResponseUtils.ReadAsChars(request);
			logger.info("body:" + body);
			R5Objects object = (R5Objects) JsonUtils.getObjectFromJson(body,R5Objects.class);
			Callable task = new NewAssetDetails(object,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}



	/**
	 * @return java.lang.Object java.lang.Object
	 * @Author
	 * @Description newReceiptHeader--更新验收单头信息
	 * @Date 2018/8/3 下午6:02
	 * @Param [request , response]
	 **/
	@RequestMapping(value = "/newReceiptHeader", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object newReceiptHeader(HttpServletRequest request,HttpServletResponse response){
		try {
			String body = ResponseUtils.ReadAsChars(request);
			logger.info("body-:" + body);
			Receipt_CreateHeader object = (Receipt_CreateHeader) JsonUtils.getObjectFromJson(body,Receipt_CreateHeader.class);
			Callable task = new NewReceiptHeader(object,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}



	/**
	 * @return java.lang.Object java.lang.Object
	 * @Author
	 * @Description newReceiptDetails--更新检查项信息
	 * @Date 2018/8/3 下午6:02
	 * @Param [request , response]
	 **/
	@RequestMapping(value = "/newReceiptDetails", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object newReceiptDetails(HttpServletRequest request,HttpServletResponse response){
		try {
			String body = ResponseUtils.ReadAsChars(request);
			logger.info("body-:" + body);
			R5ActChecklists object = (R5ActChecklists) JsonUtils.getObjectFromJson(body,R5ActChecklists.class);
			Callable task = new NewReceiptDetails(object,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		 }
		return null;
	}
	
	/**
	 * @return java.lang.Object java.lang.Object
	 * @Author
	 * @Description newReceiptDetails--更新检查项信息-批量提交
	 * @Date 2018/8/3 下午6:02
	 * @Param [request , response]
	 **/
	@RequestMapping(value = "/newReceiptDetails_L", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object newReceiptDetails_L(HttpServletRequest request,HttpServletResponse response){
		try {
			String body = ResponseUtils.ReadAsChars(request);
			logger.info("body:" + body);
			List <R5ActChecklists> object = (java.util.List
													 <com.eam.mybatis.model.R5ActChecklists>)
													JsonUtils.getObjectFromJson(body,new TypeReference <ArrayList <R5ActChecklists>>() {
													});
			Callable task = new NewReceiptDetails(object,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/***
	 * @Author JasonZhao
	 * @Description getReceiptNoByLineId //根据发运行ID获取验收单行号
	 * @Date 2018/8/21 下午12:09
	 * @Param [acceptanceLineId, response]
	 * @return java.lang.Object java.lang.Object
	 * 增加入参  acceptanceLineOfHEMA，Corp
	 **/
	@RequestMapping(value = "/getReceiptNoByLineId", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getReceiptNoByLineId(String acceptanceLineId,String acceptanceLineOfHEMA,String corp,HttpServletResponse response){
		try {
			Callable task = new GetReceiptNoByLineId(acceptanceLineId,isNull(acceptanceLineOfHEMA
																			) ? "" : acceptanceLineOfHEMA,corp,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @return java.lang.Object java.lang.Object
	 * @Author
	 * @Description getAssetDetails //根据PO，发运行 获取资产信息
	 * @Date 2018/8/4 下午6:45
	 * @Param [acceptanceLineId , poNumber, language, response]
	 * --2018 09 14 add acceptanceLineOfHEMA
	 **/
	@RequestMapping(value = "/getAssetDetails", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getAssetDetails(String assetCode, String acceptanceLineId,String poNumber,String acceptanceLineOfHEMA,String receiptNo,String corp,String language,HttpServletResponse response){
		try {
			logger.info("Controller:" + receiptNo);
			Callable task = new GetAssetDetails(acceptanceLineId,corp,poNumber,language,receiptNo,acceptanceLineOfHEMA,assetCode,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/***
	 * @Author
	 * @Description updateFixedAssetInfo // 更新固定资产信息
	 * @Date 2018/8/4 下午6:55
	 * @Param [assetCode 大阿里编号, fixedAssetCode 资产编码, response]
	 * @return java.lang.Object java.lang.Object
	 **/
	@RequestMapping(value = "/updateFixedAssetInfo", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object updateFixedAssetInfo(String assetCode,String fixedAssetCode,HttpServletResponse response){
		try {
			Callable task = new UpdateFixedAssetInfo(assetCode,fixedAssetCode,response);
			Future result = taskExecutor.submit(task);
			
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @return java.lang.Object java.lang.Object
	 * @Author JasonZhao
	 * @Description getAssetList //获取我的资产，支持分页
	 * @Date 2018/8/21 下午12:09
	 * @Param [workNo, language, limit, start, response]
	 **/
	@RequestMapping(value = "/getAssetList", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getAssetList(String manageDept, String manageType,String assetCode,String assetName,String faNo,String serialNo,String workNo,String corp,String usage,String language,String limit,String start,HttpServletResponse response){
		int started;
		int limited;
		try {
			started = StringUtils.isEmpty(start) ? 1 : Integer.valueOf(start) + 1;
			limited = StringUtils.isEmpty(limit) ? started + 50 : started + Integer.valueOf(limit);
		}
		catch (Exception e) {
			started = 1; limited = 51;
		}
		List<String> manageTypeList = new ArrayList<>();
		if(!isNull(manageType)) {
			String[] manageTypes = manageType.split(",");
			for (int i = 0; i < manageTypes.length; i++) {
				manageTypeList.add(manageTypes[i]);
			}
		}
		HashMap <String, Object> map = new HashMap <>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.MANAGETYPE,manageType);
		map.put(MapKey.MANAGETYPELIST,manageTypeList);
		map.put(MapKey.OBJ_CODE,assetCode);
		map.put(MapKey.SEARCH_DESC,assetName);
		map.put(MapKey.WORKNO,workNo);
		map.put(MapKey.CORP,corp);
		map.put(MapKey.SN,serialNo);
		map.put(MapKey.FIXED_ASSET_NO,faNo);
		map.put(MapKey.USAGE,usage);
		map.put(MapKey.PAGE_START,String.valueOf(started));
		map.put(MapKey.PAGE_END,String.valueOf(limited));
		logger.info(map.toString());
		try {
			List <U5MyAsset> assets = GetService.getInstance().getMybatisReceiptService().getMyAsset(map);
			int totalCount = GetService.getInstance().getMybatisReceiptService().getMyAssetCount(map);
			HashMap resmap = new HashMap();
			resmap.put("assets",assets);
			resmap.put(MapKey.PAGE_COUNT,totalCount);
			ResponseUtils.writeSuccessResponse(response,resmap);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/***
	 * @Author JasonZhao
	 * @Description getAssetDetails //查看资产详细信息
	 * @Date 2018/8/21 下午2:48
	 * @Param [assetCode, language, response]
	 * @return java.lang.Object java.lang.Object
	 **/
	@RequestMapping(value = "/getMyAssetDetails", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getMyAssetDetails(String assetCode,String language,HttpServletResponse response){
		
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.ASSET_CODE,assetCode);
		logger.info(map.toString());
		try {
			U5MyAsset assetDetails = GetService.getInstance().getMybatisReceiptService().getMyAssetDetails(map);
			List <U5CodeValue> propertyList = GetService.getInstance().getMybatisCodeDescService().getPropertyValuesByAssetCode(map);
			HashMap hashMap = new HashMap();
			hashMap.put(MapKey.ASSET_DETAILS,assetDetails);
			hashMap.put(MapKey.CHECK_LIST,propertyList);
			ResponseUtils.writeSuccessResponse(response,hashMap);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	@RequestMapping(value = "/demokpi", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object demokpi(HttpServletRequest request,HttpServletResponse response){
		logger.info(ConfigService.getInstance().getConfig().getJdbcUrl());
		ResponseUtils.writeResponse(response,"TestDemo");
		return null;
	}
	
	/***
	 * --------------------------------------------------------------
	 *
	 * @Author Steve.Zhang
	 * @Date 2018-8-22 20:24:50
	 * @Description (函数功能 ： 手动创建验收单信息)
	 * @Param request
	 * @Param response
	 * @return java.lang.Object --------------------------------------------------------------
	 */
	@RequestMapping(value = "/createAssetReceiptManual", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createAssetReceiptManual(HttpServletRequest request,HttpServletResponse response){
		try {
			
			Manual_CreateHeader header = (Manual_CreateHeader) JsonUtils
																	   .getObjectFromJson(ResponseUtils.ReadAsChars(request),Manual_CreateHeader.class);
			logger.info(header.toString());
			Callable task = new CreateAssetReceiptManual(header,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


	/***
	 * --------------------------------------------------------------
	 *
	 * @Author Steve.Zhang
	 * @Date 2018-12-22 12:45:01
	 * @Description (函数功能 ： 手动创建验收单信息)
	 * @Param request
	 * @Param response
	 * @return java.lang.Object --------------------------------------------------------------
	 */
	@RequestMapping(value = "/createAssetReceiptManual_New", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createAssetReceiptManual_New(HttpServletRequest request,HttpServletResponse response){
		try {

			Manual_CreateHeader header = (Manual_CreateHeader) JsonUtils
					.getObjectFromJson(ResponseUtils.ReadAsChars(request),Manual_CreateHeader.class);
			logger.info(header.toString());
			Callable task = new CreateAssetReceiptManual_New(header,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	/***
	 * --------------------------------------------------------------
	 *
	 * @Author Steve.Zhang
	 * @Date 2018-12-22 13:08:37
	 * @Description (函数功能 ： 手动创建验收单信息)
	 * @Param request
	 * @Param response
	 * @return java.lang.Object --------------------------------------------------------------
	 */
	@RequestMapping(value = "/createManualRecvLine", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createManualRecvLine(String receiptNo, String language, HttpServletRequest request,HttpServletResponse response){
		try {
			Callable task = new CreateManualRecvLine(receiptNo,language, response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


	/***
	 * --------------------------------------------------------------
	 *
	 * @Author Steve.Zhang
	 * @Date 2018-12-22 13:08:37
	 * @Description (函数功能 ：查看资产是否创建完成)
	 * @Param request
	 * @Param response
	 * @return java.lang.Object --------------------------------------------------------------
	 */
	@RequestMapping(value = "/checkRecvLineStatus", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object checkRecvLineStatus(String receiptNo, String language, HttpServletRequest request,HttpServletResponse response){
		try {
			HashMap <String, String> map = new HashMap <>();
			map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
			map.put(MapKey.RECEIPT_NO,receiptNo);
			int left = GetService.getInstance().getMybatisReceiptService().checkRecvLineStatus(map);
			String object = (left > 0)?"N":"C";
			ResponseUtils.writeResponse(response,
					JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,object)));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	
	
	/**
	 * @return java.lang.Object java.lang.Object
	 * @Author Steve Zhang
	 * @Description newReceiptDetails--更新检查项信息
	 * @Date 2018-8-23 11:31:50
	 * @Param [request , response]
	 **/
	@RequestMapping(value = "/newReceiptDetailsManual", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object newReceiptDetailsManual(HttpServletRequest request,HttpServletResponse response){
		try {
			String body = ResponseUtils.ReadAsChars(request);
			logger.info("body:" + body);
			ReceiptDetailsManual object = (ReceiptDetailsManual) JsonUtils.getObjectFromJson(body,ReceiptDetailsManual.class);
			Callable task = new NewReceiptDetailsManual(object,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	
	/**
	 * @return java.lang.Object
	 * @Author Steve Zhang
	 * @Description GetReceiptDraft--获取验收单草稿
	 * @Date 2018-8-30 19:58:28
	 * @Param [request,response]
	 */
	@RequestMapping(value = "/getReceiptDraft", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getReceiptDraft(String receiptNo,String language,HttpServletRequest request,HttpServletResponse response){
		try {
			DecimalFormat df = new DecimalFormat("#.00");

			HashMap <String, String> map = new HashMap <>();
			map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
			map.put(MapKey.RECEIPT_NO,receiptNo);
			ReceiptDraft object = GetService.getInstance().getMybatisReceiptService().getReceiptDraft(map);
//			if(object.getUnitPrice()!=null&&!"".equals(object.getUnitPrice())) {
//				String unitPrice = df.format(Double.parseDouble(object.getUnitPrice()));
//				object.setUnitPrice(unitPrice);
//			}
			String[] assetCodes = GetService.getInstance().getMybatisReceiptService().getAssetCodeByReceiptNo(map);
			object.setAssetCode(assetCodes);
			ResponseUtils.writeResponse(response,
										JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS,null,object)));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		
		return null;
	}
	
	/**
	 * @return java.lang.Object
	 * @Author Steve Zhang
	 * @Description DeleteReceipt--获取验收单草稿
	 * @Date 2018-8-31 17:11:53
	 * @Param [request,response]
	 */
	@RequestMapping(value = "/deleteReceipt", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object deleteReceipt(String receiptNo,HttpServletRequest request,HttpServletResponse response){
		try {
			AliResponse resp = GetService.getInstance().getMybatisReceiptService().deleteReceipt(receiptNo);
			ResponseUtils.writeResponse(response,
										JsonUtils.getJsonFromObject(resp));
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateConfirmation", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object updateConfirmation(TodoWorkFlow upd,HttpServletResponse response){
		try {
			logger.info("提交的数据信息：" + upd.toString());
			GetService.getInstance().getMybatisReceiptService().updateConfirmation(upd);
			ResponseUtils.writeSuccessResponse(response,null);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}
	
	/**
	 * @Author Steve Zhang
	 * @Date 2018-9-4 17:37:35
	 * @Description 根据验收人 EVT_ORIGIN，手动新增标识 evt_udfchkbox03，验收单状态 evt_status
	 */
	@RequestMapping(value = "/getReceiptList", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getReceiptList(String workNo,String mark,String status,String language,HttpServletResponse response){
		try {
			HashMap <String, String> map = new HashMap <>();
			map.put(MapKey.LANGUAGE,language);
			map.put(MapKey.WORKNO,workNo);
			map.put(MapKey.MARK,mark);
			map.put(MapKey.STATUS,status);
			logger.info("查询信息：" + map.toString());
			List <ReceiptBase> receiptList = GetService.getInstance().getMybatisReceiptService().getReceiptList(map);
			ResponseUtils.writeSuccessResponse(response,receiptList);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


	/**
	 * --------------性能优化-------------
	 * @Author Steve Zhang
	 * @Date 2018-9-4 17:37:35
	 * @Description 验收人 EVT_ORIGIN，手动新增标识 evt_udfchkbox03，验收单状态 evt_status
	 */
	@RequestMapping(value = "/getAssetRecvItems", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getAssetRecvItems(String receiptNo,String language,HttpServletResponse response){
		try {
			HashMap <String, Object> map = new HashMap <>();
			map.put(MapKey.LANGUAGE,language);
			map.put(MapKey.RECEIPT_NO,receiptNo);
			List<R5AssetPropertyValues> propertyValuesList = new ArrayList<>();
			logger.info("查询信息：" + map.toString());
			int itemNum = GetService.getInstance().getMybatisReceiptService().checkRecvLineItemExisted(map);
			if(itemNum<=0) {
				propertyValuesList = GetService.getInstance().getMybatisReceiptService().getAssetRecvItem(map);
			}else{
				propertyValuesList = GetService.getInstance().getMybatisReceiptService().getAssetRecvItems(map);
			}
			Map<String,Object> respMap = new HashMap<>();
			respMap.put("propertyValuesList",propertyValuesList);
			ResponseUtils.writeSuccessResponse(response,respMap);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


	/**
	 * @Author Steve Zhang
	 * @Date 2018-9-4 17:37:35
	 * @Description 根据验收人 EVT_ORIGIN，手动新增标识 evt_udfchkbox03，验收单状态 evt_status
	 */
	@RequestMapping(value = "/setAssetRecvItems", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object setAssetRecvItems(HttpServletRequest request,HttpServletResponse response){
		try {
			String flag = Constants.INTERFACE_ERROR;
			String message = null;
			int num = 500;
            int ins = 0;

			HashMap<String, Object> map = new HashMap<>();
			R5PropertyValuesContent PropertyValuesContent = (R5PropertyValuesContent)JsonUtils.getObjectFromJson(ResponseUtils.ReadAsChars(request),R5PropertyValuesContent.class);
            map.put(MapKey.RECEIPT_NO,PropertyValuesContent.getReceiptNo());
            map.put(MapKey.CORP,PropertyValuesContent.getCorp());
            map.put(MapKey.LANGUAGE,PropertyValuesContent.getLanguage());


            //插入资产动态验收项List
			//获取要插入的所有TDN的List
			List<String> TDNCodeList = GetService.getInstance().getMybatisReceiptService().getManualTDNCode(map);
			//获取该单据类目下所有动态验收项模板
			List<R5AssetPropertyValues> recvItemModels = GetService.getInstance().getMybatisReceiptService().getAssetRecvItem(map);
			List<R5PropertyValues> recvItemModel = recvItemModels.get(0).getPropertyList();
			//生成要插入的资产动态验收项List
			List<R5PropertyValues> propertyValuesList = new ArrayList<>();


			for(String TDN : TDNCodeList){ //遍历TDN编号列表

				for(R5PropertyValues propertyValues0 : recvItemModel) { //遍历所有验收项模板，并给模板中的ObjCode赋值TDN
//				for(int i=0; i<recvItemModel.size();i++){
//			        R5PropertyValues propertyValues = new R5PropertyValues();
//					propertyValues = recvItemModel.get(i);
					R5PropertyValues propertyValues = (R5PropertyValues) propertyValues0.clone();
					logger.info(propertyValues.toString());
					propertyValues.setObj_code(TDN);

					//遍历接口传入的验收项信息
					for(R5PropertyValues propertyValues1 : PropertyValuesContent.getPropertyValuesList()){
						//如果传入了该TDN编号下的该验收项的信息，则赋值
						if(propertyValues1.getObj_code().equals(TDN)
								&&propertyValues1.getPrv_property().equals(propertyValues.getPrv_property())){
							propertyValues.setPrv_value(propertyValues1.getPrv_value());
							propertyValues.setPrv_dvalue(propertyValues1.getPrv_dvalue());
							propertyValues.setPrv_nvalue(propertyValues1.getPrv_nvalue());
						}
					}
					//把组装好的验收项放入动态验收项List
					propertyValuesList.add(propertyValues);
				}
			}

			logger.info(propertyValuesList.toString());


			int size = propertyValuesList.size();
			logger.info("Size:"+size);
            int k = size/num;

            //先清除所有验收项
			GetService.getInstance().getMybatisReceiptService().clearRecvItems(map);

			for(int i=0;i<k;i++){
                map.put("propertyValuesList",propertyValuesList.subList(i*num,(i+1)*num));
                ins += GetService.getInstance().getMybatisReceiptService().setAssetRecvItems(map);
                map.remove("propertyValuesList");
            }
			if(propertyValuesList.subList(k*num,size).size()>0) {
				map.put("propertyValuesList", propertyValuesList.subList(k * num, size));
				ins += GetService.getInstance().getMybatisReceiptService().setAssetRecvItems(map);
			}
            logger.info("INS = "+ins);
			if(ins>0)
				flag = Constants.INTERFACE_SUCCESS;
			if(ins<size)
				message = "Part of Receipt items create failed.";
			ResponseUtils.writeResponse(response,
					JsonUtils.getJsonFromObject(new AliResponseData(flag,message,ins)));

		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Date 2019年1月18日09:45:18
	 * @Description 获取预生成大阿里编号列表
	 */
	@RequestMapping(value = "/getTDNCodeList", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getTDNCodeList(String receiptNo,HttpServletResponse response){
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.setHeader("Cache-Control","no-cache");
			HashMap <String, Object> map = new HashMap <>();
			map.put(MapKey.RECEIPT_NO,receiptNo);
			List<String> TDNCodeList = new ArrayList<>();
			logger.info("查询信息：" + map.toString());
			TDNCodeList = GetService.getInstance().getMybatisReceiptService().getManualTDNCode(map);
			HashMap <String, Object> respMap = new HashMap <>();
			respMap.put("TDNCodeList",TDNCodeList);
			ResponseUtils.writeSuccessResponse(response,respMap);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Date 2019年1月18日09:45:18
	 * @Description 获取校验后报错属性行
	 */
	@RequestMapping(value = "/getErrorProperty", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object getErrorProperty(String receiptNo,HttpServletResponse response){
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.setHeader("Cache-Control","no-cache");
			HashMap <String, String> map = new HashMap <>();
			map.put(MapKey.RECEIPT_NO,receiptNo);
			logger.info("查询信息：" + map.toString());
			List<U5PropertyResult> resultList =  GetService.getInstance().getMybatisReceiptService().getErrorProperty(map);
			U5PropertyResultList propertyResultList  = new U5PropertyResultList();
			propertyResultList.setPropertyResultList(resultList);
			propertyResultList.setReceiptNo(receiptNo);
			HashMap <String, Object> respMap = new HashMap <>();
			respMap.put("propertyResultList",propertyResultList);
			ResponseUtils.writeSuccessResponse(response,respMap);
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


	/**
	 * @Author Steve Zhang
	 * @Description  checkObjectList 批量导入大阿里编号进行查询校验
	 * @Date 2019年4月23日19:13:52
	 * @Param List <String> TDNCodes, Process
	 * @Return List <U5MyAsset> AssetList
	 */
	@RequestMapping(value = "/checkObjectList", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object checkObjectList(HttpServletRequest request,HttpServletResponse response) {
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> respMap = new HashMap<>();
		ArrayList<String> assetCodes = new ArrayList<>();
		int totalnum = 0;
		int passnum = 0;
		int failnum =0;

		int start;
		int limit;
		final String TableId = "U5OBJBATCERESULTFLAGID";
		try {
			CheckProcessTDNCodes checklist = (CheckProcessTDNCodes)JsonUtils.getObjectFromJson(ResponseUtils.ReadAsChars(request),CheckProcessTDNCodes.class);
			start = 0;
			limit = 999999;


			//  获取一个临时FlagId
			int FlagId = 0;
			map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(checklist.getLanguage()));
			map.put("TableId",TableId);
			FlagId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map);
			map.put("FlagId",FlagId);
			map.put("process",checklist.getProcess());
			map.put(MapKey.WORKNO,checklist.getWorkNo());
			map.put(MapKey.RECEIVER,checklist.getReceiverNo());

			if(isNull(checklist.getTDNCodes())){
				assetCodes = GetService.getInstance().getMybatisReceiptService().getMyAssetCodes(map);
				logger.info(assetCodes.toString());

			}else {
				assetCodes = checklist.getTDNCodes();
			}
			//不做分页限制
			limit = assetCodes.size()+1;

			int total = assetCodes.size();
			int size = 500;
			int k = total/size;

			for(int i=0; i<k; i++){
				map.put(MapKey.OBJ_CODE_LIST, assetCodes.subList(i*size,(i+1)*size));
				totalnum += GetService.getInstance().getMybatisReceiptService().insertAssetCodes(map);
				map.remove(MapKey.OBJ_CODE_LIST);
			}

			if(assetCodes.subList(k*size,total).size()>0) {
				map.put(MapKey.OBJ_CODE_LIST, assetCodes.subList(k * size, total));
				//	向临时表插入数据
				totalnum += GetService.getInstance().getMybatisReceiptService().insertAssetCodes(map);
			}
			map.put(MapKey.PAGE_START,start);
			map.put(MapKey.PAGE_END,limit);
			map.put(MapKey.CORP,checklist.getCorp());
			map.put("receiverCorp",checklist.getReceiverCorp());
			map.put(MapKey.DEPARTMENT,checklist.getDepNo());
			map.put(MapKey.FROM_STORE,checklist.getFromStore());
			map.put(MapKey.TO_STORE,checklist.getToStore());
			map.put(MapKey.FROM_LOCATION,checklist.getFromLocation());
			map.put(MapKey.TO_LOCATION,checklist.getToLocation());
			map.put(MapKey.TRANSFERMODE,checklist.getTransferMode());
			map.put(MapKey.FROM_OUCODE,checklist.getFromOUCode());
			//  根据流程发起校验
			AliResultProcedure result = GetService.getInstance().getMybatisReceiptService().VerifyTDNList(map);
			if(Constants.INTERFACE_SUCCESS.equals(result.getFlag())){
				//  返回校验通过的资产详情和失败的资产编号
				passnum = GetService.getInstance().getMybatisReceiptService().getPassedAssetsNum(map);
				failnum = GetService.getInstance().getMybatisReceiptService().getFailedAssetsNum(map);
				List<U5MyAsset> AssetList = GetService.getInstance().getMybatisReceiptService().getVerifiedAssets(map);
				List<FailAsset> FailList = GetService.getInstance().getMybatisReceiptService().getFailedAssetsList(map);
				respMap.put("totalNum",totalnum);
				respMap.put("passNum",passnum);
				respMap.put("failNum",failnum);
				respMap.put("assetList",AssetList);
				respMap.put("failList",FailList);
				ResponseUtils.writeSuccessResponse(response,respMap);
			}else{
				ResponseUtils.writeErrorResponse(response,"Insert Data Error: The Asset Codes cannot be inserted into Database.");
			}

		} catch (Exception e) {
			ResponseUtils.writeResponse(response, e);
		}
		return null;


	}


//	/**
//	 * @Author Steve Zhang
//	 * @Description  getcheckedMyAssetList 批量导入大阿里编号进行查询校验
//	 * @Date 2019年4月23日19:13:52
//	 * @Param List <String> TDNCodes, Process
//	 * @Return List <U5MyAsset> AssetList
//	 */
//	@RequestMapping(value = "/getcheckMyAssetList", method = { RequestMethod.GET,RequestMethod.POST })
//	public @ResponseBody
//	Object getcheckedMyAssetList(String manageDept, String manageType,
//								 String assetCode,String assetName,
//								 String faNo,String serialNo,
//								 String workNo,String corp,
//								 String usage,String language,
//								 String limit,String start,
//								 String process,
//								 HttpServletResponse response) {
//		int started;
//		int limited;
//		final String TableId = "U5OBJBATCERESULTFLAGID";
//		try {
//			started = StringUtils.isEmpty(start) ? 1 : Integer.valueOf(start) + 1;
//			limited = StringUtils.isEmpty(limit) ? started + 50 : started + Integer.valueOf(limit);
//		}
//		catch (Exception e) {
//			started = 1; limited = 51;
//		}
//		List<String> manageTypeList = new ArrayList<>();
//		String[] manageTypes = manageType.split(",");
//		for(int i=0;i<manageTypes.length;i++){
//			manageTypeList.add(manageTypes[i]);
//		}
//		HashMap <String, Object> map = new HashMap <>();
//		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
//		map.put(MapKey.MANAGETYPE,manageType);
//		map.put(MapKey.MANAGETYPELIST,manageTypeList);
//		map.put(MapKey.OBJ_CODE,assetCode);
//		map.put(MapKey.SEARCH_DESC,assetName);
//		map.put(MapKey.WORKNO,workNo);
//		map.put(MapKey.CORP,corp);
//		map.put(MapKey.SN,serialNo);
//		map.put(MapKey.FIXED_ASSET_NO,faNo);
//		map.put(MapKey.USAGE,usage);
//		map.put(MapKey.PROCESS,process);
//		map.put(MapKey.PAGE_START,String.valueOf(started));
//		map.put(MapKey.PAGE_END,String.valueOf(limited));
//		logger.info(map.toString());
//		try {
//			ArrayList<String> assetCodes = GetService.getInstance().getMybatisReceiptService().getMyAssetCode(map);
//			map.put("TableId",TableId);
//			FlagId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map);
//			map.put("FlagId",FlagId);
//			map.put(MapKey.OBJ_CODE_LIST,assetCodes);
//			totalnum = GetService.getInstance().getMybatisReceiptService().insertAssetCodes(map);
//
//
//
//
//			HashMap resmap = new HashMap();
//			resmap.put("assets",assets);
//			resmap.put(MapKey.PAGE_COUNT,totalCount);
//			ResponseUtils.writeSuccessResponse(response,resmap);
//		}
//		catch (Exception e) {
//			ResponseUtils.writeResponse(response,e);
//		}
//
//
//
//		return null;
//	}


	/**
	 * @author Steve ZHANG
	 * @Date  2019年12月6日10:54:55
	 * @Description  批量录入资产 生成/更新单据接口
	 */
	@RequestMapping(value = "/createBatchReceipt",method = { RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object createBatchReceipt(HttpServletRequest request, HttpServletResponse response){
		HashMap<String,String> map = new HashMap<>();
		try {
			BatchReceiptHeader batchReceiptHeader = (BatchReceiptHeader) JsonUtils.getObjectFromJson(ResponseUtils.ReadAsChars(request), BatchReceiptHeader.class);

			AliResultProcedure resp = GetService.getInstance().getMybatisReceiptService().CreateBatchReceipt(batchReceiptHeader);

			if (Constants.INTERFACE_SUCCESS.equals(resp.getFlag())) {
				map.put("receiptNo", resp.getParam1());
				map.put("corp", resp.getParam2());
				//导入附件
				if(!isNull(batchReceiptHeader.getAttachments())) {
					HashMap<String,Object> map1 = new HashMap<>();
					map1.put("TableId", "U5EVENTFILE");
					GetService.getInstance().getMybatisReceiptService().cleanEventFiles(resp.getParam1());
					for (Attachment a : batchReceiptHeader.getAttachments()) {
						a.setEventCode(resp.getParam1());
						String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
						a.setLine(lineId);
						GetService.getInstance().getMybatisReceiptService()
								.insertEventFiles(a);
					}
				}
				if(!isNull(batchReceiptHeader.getDataFile())){
					HashMap<String,Object> map1 = new HashMap<>();
					map1.put("TableId", "U5EVENTFILE");
					GetService.getInstance().getMybatisReceiptService().cleanBatchDataFile(resp.getParam1());
					String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
					batchReceiptHeader.getDataFile().setEventCode(resp.getParam1());
					batchReceiptHeader.getDataFile().setLine(lineId);
					GetService.getInstance().getMybatisReceiptService().insertBatchDataFile(batchReceiptHeader.getDataFile());
				}
				if(!isNull(batchReceiptHeader.getBpmsUrl())){
					HashMap<String,Object> map1 = new HashMap<>();
					map1.put("TableId", "U5EVENTFILE");
					GetService.getInstance().getMybatisReceiptService().cleanBpmsURL(resp.getParam1());
					String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
					batchReceiptHeader.getBpmsUrl().setEventCode(resp.getParam1());
					batchReceiptHeader.getBpmsUrl().setLine(lineId);
					GetService.getInstance().getMybatisReceiptService().insertBpmsURL(batchReceiptHeader.getBpmsUrl());
				}

			}
			ResponseUtils.writeResponse(response,
					JsonUtils.getJsonFromObject(new AliResponseData(resp.getFlag(), resp.getMessage(), map)));
		} catch(Exception e){
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	/**
	 * @author  Steve ZHANG
	 * @Date 2019年12月10日17:55:03
	 * @Description  批量录入资产 生成单据详情行
	 */
	@RequestMapping(value = "/createBatchAssets",method = { RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object createBatchAssets(HttpServletRequest request, HttpServletResponse response){
		HashMap<String,Object> map = new HashMap<>();
		ArrayList<BatchAsset> failList = new ArrayList<BatchAsset>();
		ArrayList<BatchAsset> successList = new ArrayList<BatchAsset>();
		try {
			//解析资产行List
			BatchRecvLine batchRecvLine = (BatchRecvLine) JsonUtils.getObjectFromJson(ResponseUtils.ReadAsChars(request), BatchRecvLine.class);

			//执行校验
			VerifyBatchList(batchRecvLine,failList,successList);
			//List Insert数据库
			map.put("receiptNo", batchRecvLine.getEvtCode());
			map.put("corp", batchRecvLine.getCorp());
			GetService.getInstance().getMybatisReceiptService().cleanBatchAssets(map);


			int totalnum = 0;
			int total = batchRecvLine.getRcvLines().size();
			int size = 500;
			int k = total / size;
			for (int i = 0; i < k; i++) {
				map.put(MapKey.BATCH_ASSETS, batchRecvLine.getRcvLines().subList(i * size, (i + 1) * size));
				totalnum += GetService.getInstance().getMybatisReceiptService().insertBatchAsset(map);
				map.remove(MapKey.BATCH_ASSETS);
			}
			if (batchRecvLine.getRcvLines().subList(k * size, total).size() > 0) {
				map.put(MapKey.BATCH_ASSETS, batchRecvLine.getRcvLines().subList(k * size, total));
				totalnum += GetService.getInstance().getMybatisReceiptService().insertBatchAsset(map);
			}

			//返回成功数目失败数目及失败信息
			HashMap<String,Object> respMap = new HashMap<>();
			respMap.put("totalNum",total);
			respMap.put("insertNum", totalnum);
			respMap.put("failNum",failList.size());
			respMap.put("successNum", successList.size());
			//respMap.put("failList",failList);
			//respMap.put("successList",successList);
			ResponseUtils.writeSuccessResponse(response,respMap);

		} catch(Exception e){
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}

	private void VerifyBatchList(BatchRecvLine batchRecvLine, ArrayList<BatchAsset> failList, ArrayList<BatchAsset> successList) {
		//从数据库获取需要校验的内容到内存中
		ArrayList<R5Parts> partList = GetService.getInstance().getMybatisCodeDescService().getPartList();
		ArrayList<R5Manufactures> brandList = GetService.getInstance().getMybatisCodeDescService().getBrandList();
		ArrayList<Models> modelList = GetService.getInstance().getMybatisCodeDescService().getModelList();
		ArrayList<ObjStatus> objStatusList = GetService.getInstance().getMybatisCodeDescService().getobjStatusList();
		ArrayList<R5Currency> currencyList = GetService.getInstance().getMybatisCodeDescService().getCurrencyList();
		ArrayList<R5Orgnazition> corpList = GetService.getInstance().getMybatisCodeDescService().getCorpList();
		ArrayList<R5Mrcs> mrcList = GetService.getInstance().getMybatisCodeDescService().getMrcList();
		ArrayList<R5Personnel> perList = GetService.getInstance().getMybatisCodeDescService().getPersonList();
		ArrayList<String> assetCodeList = GetService.getInstance().getMybatisCodeDescService().getAssetCodeList();



		HashMap<String, String> queryMap = new HashMap<>();
		queryMap.put(MapKey.LANGUAGE,"ZH");
		queryMap.put(MapKey.ORG,batchRecvLine.getCorp());
		queryMap.put(MapKey.CORP,batchRecvLine.getCorp());
		List<U5CodeValue> locList = GetService.getInstance().getMybatisCodeDescService().getLocationAll(queryMap);
		List<R5Stores> storeList = GetService.getInstance().getMybatisReceiptService().getStoreLOV(queryMap);

		queryMap.put(MapKey.FIELD, UserDefinedField.IntialType.getField());
		queryMap.put(MapKey.ENTITY,UserDefinedField.IntialType.getEntity());
		List<U5CodeValue> newTypeList = GetService.getInstance().getMybatisCodeDescService().getUserDefinedField(queryMap);
		queryMap.replace(MapKey.FIELD,UserDefinedField.OwnerShip.getField());
		queryMap.replace(MapKey.ENTITY,UserDefinedField.OwnerShip.getEntity());
		List<U5CodeValue> ownerShipList = GetService.getInstance().getMybatisCodeDescService().getUserDefinedField(queryMap);
		queryMap.replace(MapKey.FIELD,UserDefinedField.Usage.getField());
		queryMap.replace(MapKey.ENTITY,UserDefinedField.Usage.getEntity());
		List<U5CodeValue> usageList = GetService.getInstance().getMybatisCodeDescService().getUserDefinedField(queryMap);
		List<U5CodeValue> classList = GetService.getInstance().getMybatisCodeDescService().getAssetClassList(queryMap);
		List<AssetCategory> cateList = GetService.getInstance().getMybatisCodeDescService().getAssetCategoryList();

		//2019年12月30日20:42:43 元行需求添加校验资产编号是否重复
		for(int i=0;i<batchRecvLine.getRcvLines().size();i++){
			String errMsg = "";
			String flag = Constants.INTERFACE_SUCCESS;
			if(!isNull(batchRecvLine.getRcvLines().get(i).getResult())) {
				flag = batchRecvLine.getRcvLines().get(i).getResult();
			}
			if(batchRecvLine.getRcvLines().get(i).getAssetCode()!=null){
				for(int j=i+1;j<batchRecvLine.getRcvLines().size();j++){
					if(batchRecvLine.getRcvLines().get(j).getAssetCode()!=null){
						if(batchRecvLine.getRcvLines().get(i).getAssetCode().equals(batchRecvLine.getRcvLines().get(j).getAssetCode())){
							errMsg += "资产编号与上传内容中其他资产编号产生重复。";
							flag = Constants.INTERFACE_ERROR;
							break;
						}
					}
				}
			}
			if(Constants.INTERFACE_ERROR.equals(flag)) {
				errMsg += batchRecvLine.getRcvLines().get(i).getErrMsg();
				batchRecvLine.getRcvLines().get(i).setErrMsg(errMsg);
				batchRecvLine.getRcvLines().get(i).setResult(flag);
			}
		}


		//依次校验
		for (BatchAsset ba : batchRecvLine.getRcvLines()) {
			String errMsg = "";
			String flag = Constants.INTERFACE_SUCCESS;
			//先获取前台传入的校验结果
			if(!isNull(ba.getResult())) {
				flag = ba.getResult();
			}
			/*Jason 2019-Dec-24  修改处理内容
			 *每行设置通用单据号和组织号
			 */
			ba.setEvtCode(batchRecvLine.getEvtCode()); //增加对于每一行的单号赋值
			ba.setCorp(batchRecvLine.getCorp());

			//资产编号重复检查
			if(!isNull(ba.getAssetCode())&&assetCodeList.stream().anyMatch(k -> k.equals(ba.getAssetCode()))){
				errMsg += "资产编号已存在,";
			}
			//组织校验 组织为空或者组织代码不存在
			if ( isNull(ba.getCorp()) || !corpList.stream().anyMatch(k -> k.getOrg_code().equals(ba.getCorp())) ) {
				errMsg += "资产组织,";
			}
			//System.out.println(locList.stream().findAny(e->{}));
			//资产类目校验
			if ( isNull(ba.getPartCode()) ||!partList.stream().anyMatch(k -> k.getPartCode().equals(ba.getPartCode())) ) {
				errMsg += "资产类目,";
			}
			//资产状态校验
			if ( isNull(ba.getAssetStatus()) ||!objStatusList.stream().anyMatch(k -> k.getObjStatusCode().equals(ba.getAssetStatus())) ) {
				errMsg += "资产状态,";

			} else if("I".equals(ba.getAssetStatus().substring(0,1))){
				if(isNull(ba.getLocationCode())||!locList.stream().anyMatch(k ->k.getCode().equals(ba.getLocationCode()))){
					errMsg+= "位置信息,";
				}
				if(!isNull(ba.getStoreCode())){
					errMsg+= "仓库,";
				}
				if(isNull(ba.getUserCode())){
					ba.setUserCode(ba.getRespCode());
				}
			} else if ("C".equals((ba.getAssetStatus().substring(0, 1)))) {
				if(isNull(ba.getStoreCode())||!storeList.stream().anyMatch(k->k.getStoreCode().equals(ba.getStoreCode()))){
					errMsg+="仓库,";
				}
				if(!isNull(ba.getUserCode())){
					errMsg += "使用人,";
				}
			}
			//资产品牌校验
			if (!isNull(ba.getBrand()) &&!brandList.stream().anyMatch(k -> k.getMfg_code().equals(ba.getBrand())) ) {
				errMsg += "品牌,";
			}
			//资产型号校验
			if ( ! isNull(ba.getModel())&&!modelList.contains(new Models(ba.getModel(),ba.getBrand(),ba.getPartCode())) ) {
				errMsg += "型号,";
			}
			//资产责任人校验
			if ( isNull(ba.getRespCode())||!perList.stream().anyMatch(k -> k.getPer_code().equals(ba.getRespCode())) ) {
				errMsg += "责任人,";
			}
			//资产使用人校验
			if (!isNull(ba.getUserCode()) &&!perList.stream().anyMatch(k -> k.getPer_code().equals(ba.getUserCode())) ) {
				errMsg += "使用人,";
			}
			if(!isNull(ba.getUsageCode())&&!usageList.stream().anyMatch(k ->k.getCode().equals(ba.getUsageCode()))){
				errMsg += "使用说明,";
			}
			if(isNull(ba.getOrientationCode())||!newTypeList.stream().anyMatch(k->k.getCode().equals(ba.getOrientationCode()))){
				errMsg += "新增类型,";
			}
			if(isNull(ba.getOwnerShipCode())||!ownerShipList.stream().anyMatch((k->k.getCode().equals(ba.getOwnerShipCode())))){
				errMsg += "所有权,";
			}
			if(isNull(ba.getAcceptDate())||!MyUtils.isLegalDateFormat(ba.getAcceptDate())){
				errMsg += "启用日期格式,";
			}
			if(isNull(ba.getObjClass())||!classList.stream().anyMatch(k -> k.getCode().equals(ba.getObjClass()))){
				errMsg += "资产分类,";
			}
			if(isNull(ba.getObjCate())||!cateList.stream().anyMatch(k -> k.getCategoryClass().equals(ba.getObjClass())&&k.getCategoryCode().equals(ba.getObjCate()))){
				errMsg += "资产种类,";
			}
			if(("+".equals(ba.getIfSync())||isNull(ba.getFaAssetCode()))&&isNull(ba.getFaLocationCode())){
			    errMsg += "财务地点Code,";
            }
			if(isNull(ba.getAssetDesc())){
				errMsg += "资产名称,";
			}
			// 上面校验如果有错误发生。则完善报错信息
			// 如果校验有错误。则将Flag 标记为E
			if (!StringUtils.isEmpty(errMsg)) {
				errMsg += "校验未通过，请检查并重新填写。";
				flag = Constants.INTERFACE_ERROR;
			}
			ba.setResult(flag);
			//未通过校验的资产行添加到失败List中，并附带errMsg
			if (Constants.INTERFACE_ERROR.equals(flag)) {
				if(!isNull(ba.getErrMsg())) {
					errMsg += ba.getErrMsg();
				}
				ba.setErrMsg(errMsg);
				failList.add(ba);
			}
			//通过校验的资产行添加到成功List中
			if (Constants.INTERFACE_SUCCESS.equals(flag)) {
				successList.add(ba);
			}

		}
	}


	/**
	 * @author Steve ZHANG
	 * @Date 2019年12月12日11:12:24
	 * @Description  查询批量验收单据头
	 */
	@RequestMapping(value = "/queryBatchReceipt",method = { RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object queryBatchReceipt(String receiptNo, String corp, String language, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<>();
		map.put("receiptNo",receiptNo);
		map.put("corp",corp);
		map.put("language", language);
		try {
			BatchReceiptHeader batchReceiptHeader = GetService.getInstance().getMybatisReceiptService().getBatchReceiptHeader(map);
			ResponseUtils.writeSuccessResponse(response, batchReceiptHeader);
		} catch(Exception e){
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}

	/**
	 * @author Steve ZHANG
	 * @Date 2019年12月13日14:01:42
	 * @Description  查询批量验收单行
	 */
	@RequestMapping(value = "/queryBatchAssets", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object queryBatchAssets(String receiptNo, String corp, String language, int end,int start,HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> map = new HashMap<>();
		map.put("receiptNo",receiptNo);
		map.put("corp",corp);
		map.put("language", language);
		map.put(MapKey.PAGE_START,start);
		map.put(MapKey.PAGE_END,end);
		try {
			HashMap<String,Object> respMap = new HashMap<>();
			ArrayList<BatchAsset> batchAssets = GetService.getInstance().getMybatisReceiptService().getBatchAssets(map);
			BatchRecvLine batchRecvLine = new BatchRecvLine(receiptNo,corp,batchAssets);
			int total = GetService.getInstance().getMybatisReceiptService().getBatchAssetCount(map);
			respMap.put(MapKey.PAGE_START,start);
			respMap.put(MapKey.PAGE_END,end);
			respMap.put(MapKey.PAGE_COUNT,total);
			respMap.put("batchRecvLine",batchRecvLine);
			ResponseUtils.writeSuccessResponse(response, respMap);
		} catch(Exception e){
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}

	/**
	 * @author Steve ZHANG
	 * @description 筛选查询历史新增单据
	 * @Date 2019年12月18日11:02:40
	 */
	@RequestMapping(value = "/queryListofReceipts", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object queryListofReceipts(String receiptNo,
													String corp,
													String status,
													String system,
													String PONumber,
													String creator,
													String jobType,
													String startDate,
													String endDate,
													int start,
													int end,
													String workNo,
													String language,
													HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> map = new HashMap<>();
		List<String> receiptNoList = null,corpList = null,systemList = null,PONumberList = null,creatorList = null,jobTypeList = null, statusList = null;
		if(!isNull(receiptNo)) {
			receiptNoList= Arrays.asList(receiptNo.split(","));
		}
		if(!isNull(corp)) {
			corpList = Arrays.asList(corp.split(","));
		}
		if(!isNull(system)) {
			systemList = Arrays.asList(system.split(","));
		}
		if(!isNull(PONumber)) {
			PONumberList = Arrays.asList(PONumber.split(","));
		}
		if(!isNull(creator)) {
			creatorList = Arrays.asList(creator.split(","));
		}
		if(!isNull(jobType)){
			jobTypeList = Arrays.asList(jobType.split(","));
		}
		if(!isNull(status)){
			statusList = Arrays.asList(status.split(","));
		}
		map.put("receiptNoList",receiptNoList);
		map.put("corpList",corpList);
		map.put("systemList",systemList);
		map.put("PONumberList",PONumberList);
		map.put("creatorList",creatorList);
		map.put("jobTypeList",jobTypeList);
		map.put("statusList",statusList);
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		map.put(MapKey.WORKNO,workNo);
		map.put(MapKey.PAGE_START,start);
		map.put(MapKey.PAGE_END,end);

		try{
			HashMap<String,Object> respMap = new HashMap<>();
			List<BatchReceiptHeader> receiptList = GetService.getInstance().getMybatisReceiptService().queryReceiptList(map);
			int total = GetService.getInstance().getMybatisReceiptService().getReceiptListNum(map);
			respMap.put(MapKey.PAGE_START,start);
			respMap.put(MapKey.PAGE_END,end);
			respMap.put(MapKey.PAGE_COUNT,total);
			respMap.put("receiptList",receiptList);
			ResponseUtils.writeSuccessResponse(response, respMap);

		} catch(Exception e){
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}

		return null;

	}

	/**
	 * @author Steve ZHANG
	 * @description 查询批量新增资产详情
	 * @Date 2019年12月18日11:02:40
	 */
	@RequestMapping(value = "/getBatchAssetDetails", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object getBatchAssetDetails(String receiptNo, String assetCode,	HttpServletRequest request, HttpServletResponse response){
		HashMap<String,Object> map = new HashMap<>();
		map.put(MapKey.ASSET_CODE,assetCode);
		map.put(MapKey.RECEIPT_NO,receiptNo);
		try{
			List<U5AssetDetails> assetDetails = GetService.getInstance().getMybatisReceiptService().getBatchAssetDetails(map);
			HashMap<String,Object> respMap = new HashMap<>();
			respMap.put("assetDetails",assetDetails);
			ResponseUtils.writeSuccessResponse(response, respMap);
		}catch(Exception e){
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}


	/**
	 * @author Steve ZHANG
	 * @description 查询批量新增资产详情
	 * @Date 2019年12月18日11:02:40
	 */
	@RequestMapping(value = "/createCategoryMapping", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object createCategoryMapping(HttpServletRequest request, HttpServletResponse response){

		CategoryMapping categoryMapping = (CategoryMapping)JsonUtils.getObjectFromJson(ResponseUtils.ReadAsChars(request), CategoryMapping.class);
		HashMap<String,Object> map = new HashMap<>();
		try{
			AliResultProcedure resp = GetService.getInstance().getMybatisReceiptService().createCategoryMapping(categoryMapping);
			HashMap<String,Object> respMap = new HashMap<>();
			respMap.put(MapKey.PART_CODE,resp.getParam1());
			ResponseUtils.writeSuccessResponse(response, respMap);
		}catch(Exception e){
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}


	/***
	 * --------------------------------------------------------------
	 *
	 * @Author Steve Zhang
	 * @Date 2020年1月8日19:02:58
	 * @Description (函数功能 ： BU资产 创建验收单信息)
	 * @Param request
	 * @Param response
	 * @return java.lang.Object --------------------------------------------------------------
	 */
	@RequestMapping(value = "/createAssetReceipt_BU", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createAssetReceipt_BU(HttpServletRequest request,HttpServletResponse response){
		try {

			Receipt_CreateHeader header = (Receipt_CreateHeader) JsonUtils
					.getObjectFromJson(ResponseUtils.ReadAsChars(request),Receipt_CreateHeader.class);
			logger.info(header.toString());
			Callable task = new CreateAssetReceipt_BU(header,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


	/***
	 *
	 * @Author Steve Zhang
	 * @Date 2020年1月8日19:01:55
	 * @Description (函数功能 ： BU资产 创建验收行信息)
	 * @Param request
	 * @Param response
	 * @return java.lang.Object
	 */
	@RequestMapping(value = "/createRecvLine_BU", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createRecvLine_BU(HttpServletRequest request,HttpServletResponse response){
		try {

			Receipt_CreateLine recvLine = (Receipt_CreateLine) ResponseUtils.ReadAsObjects(request,Receipt_CreateLine.class);
			logger.info(recvLine.toString());
			Callable task = new CreateRecvLine_BU(recvLine,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}

	/**
	 * @return java.lang.Object java.lang.Object
	 * @Author
	 * @Description newReceiptHeader--更新验收单头信息
	 * @Date 2018/8/3 下午6:02
	 * @Param [request , response]
	 **/
	@RequestMapping(value = "/newReceiptHeader_BU", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object newReceiptHeader_BU(HttpServletRequest request,HttpServletResponse response){
		try {
			String body = ResponseUtils.ReadAsChars(request);
			logger.info("body-:" + body);
			Receipt_CreateHeader object = (Receipt_CreateHeader) JsonUtils.getObjectFromJson(body,Receipt_CreateHeader.class);
			Callable task = new NewReceiptHeader_BU(object,response);
			Future result = taskExecutor.submit(task);
			result.get();
		}
		catch (Exception e) {
			ResponseUtils.writeResponse(response,e);
		}
		return null;
	}


}
