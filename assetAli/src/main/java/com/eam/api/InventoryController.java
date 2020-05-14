package com.eam.api;

import clojure.lang.Obj;
import com.eam.context.*;
import com.eam.mybatis.model.*;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.eam.context.EventDefault.*;
import static java.util.Objects.isNull;


/**************************************
 *@ClassName InventoryController
 *@Description 盘点模块
 *@Author Steve
 *@Date 2019年12月19日18:00:32
 *@Version 1.0
 **************************************/
@Controller
public class InventoryController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Author Steve Zhang
	 * @Description createOrUpdateInventory // 创建更新盘点单头
	 * @Date 2019年12月19日18:12:17
	 * @Param [request, response]
	 * @Return java.lang.Objct
	 **/
	@RequestMapping(value = "/createOrUpdateInventory", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody
	Object createOrUpdateInventory(HttpServletRequest request, HttpServletResponse response) {
		InventoryHeader header = getDataFromRequest(request);
		moreDataDefault(header, PD);
		logger.info("header::" + header.toString());
		try {
			AliResultProcedure resp = new AliResultProcedure();
			HashMap<String, Object> respMap = new HashMap<>();
			if(isNull(header.getEvtCode())) { //创建盘点单
				resp = GetService.getInstance().getMybatisInventoryService().createOrUpdateInventory(header);
				header.setEvtCode(resp.getParam1());
				GetService.getInstance().getMybatisInventoryService().createInventoryParam(header);
			}else{ //更新盘点单
				if(PD.getEvtJobType().equals(GetService.getInstance().getMybatisInventoryService().getInventoryStatus(header))){
					//只有PD10的状态下可以更新参数列表
					//只有传入有值的字段会进行更新，为NULL字段保留原过滤条件
					GetService.getInstance().getMybatisInventoryService().deleteInventoryParam(header);
					GetService.getInstance().getMybatisInventoryService().createInventoryParam(header);
				}
				resp = GetService.getInstance().getMybatisInventoryService().createOrUpdateInventory(header);
			}



			if (Constants.INTERFACE_SUCCESS.equals(resp.getFlag())) {
				respMap.put("evtCode", resp.getParam1());
				respMap.put("corp", resp.getParam2());
				respMap.put("num", resp.getParam3());
				//导入附件
				if (!isNull(header.getAttachments())) {
					HashMap<String, Object> map1 = new HashMap<>();
					map1.put("TableId", "U5EVENTFILE");
					GetService.getInstance().getMybatisReceiptService().cleanEventFiles(resp.getParam1());
					for (Attachment a : header.getAttachments()) {
						a.setEventCode(resp.getParam1());
						String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
						a.setLine(lineId);
						GetService.getInstance().getMybatisReceiptService()
								.insertEventFiles(a);
					}
				}
				//导入用户上传Excel文件
				if (!isNull(header.getDataFile())) {
					HashMap<String, Object> map1 = new HashMap<>();
					map1.put("TableId", "U5EVENTFILE");
					GetService.getInstance().getMybatisReceiptService().cleanBatchDataFile(resp.getParam1());
					String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
					header.getDataFile().setEventCode(resp.getParam1());
					header.getDataFile().setLine(lineId);
					GetService.getInstance().getMybatisReceiptService().insertBatchDataFile(header.getDataFile());
				}
			}
			ResponseUtils.writeResponse(response, JsonUtils.getJsonFromObject(new AliResponseData(resp.getFlag(), resp.getMessage(), respMap)));

		} catch (Exception e) {
			ResponseUtils.writeErrorResponse(response, e.getMessage());
		}


		return null;
	}


	/**
	 * @Author JasonZhao
	 * @Description getDataFromRequest // 获取body信息
	 * @Date 2018/8/27 11:38:39
	 * @Param [request]
	 * @Return com.eam.mybatis.model.PickupOrReturnMessage
	 **/
	private InventoryHeader getDataFromRequest(HttpServletRequest request) {
		String body = ResponseUtils.ReadAsChars(request);
		logger.info(body);
		return (InventoryHeader)
				JsonUtils.getObjectFromJson(body, InventoryHeader.class);
	}

	/***
	 * @Author JasonZhao
	 * @Description moreDataDefault // 更多配置
	 * @Date 2018/8/27 11:38:04
	 * @Param [data, evtDefault]
	 * @Return void
	 **/
	private void moreDataDefault(InventoryHeader header, EventDefault evtDefault) {
		if(isNull(header.getEvtCode())){
			header.setEvtStatus(evtDefault.getEvtStatus());
		}
	}

	/**
	 * @Author Steve Zhang
	 * @Date 2019年12月23日14:01:45
	 * @Description 根据查询盘点单筛选条件查询筛选资产条目数量
	 * @Return object
	 */
	@RequestMapping(value = "/queryInventoryAssetNum", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody
	Object queryInventoryAssetNum(HttpServletRequest request, HttpServletResponse response) {
		InventoryHeader header = getDataFromRequest(request);
		HashMap<String,Object> map = new HashMap<>();

		try{
			int num = GetService.getInstance().getMybatisInventoryService().queryInventoryAssetNum(header);
			map.put("queryNum",num);
			ResponseUtils.writeSuccessResponse(response, map);
		}catch(Exception e){
			ResponseUtils.writeErrorResponse(response, e.getMessage());
		}

		return null;

	}

	/**
	 * @Author Steve Zhang
	 * @Date 2019年12月24日16:56:07
	 * @Description 查询盘点单头及筛选信息
	 */
	@RequestMapping(value = "/queryInventoryHeader", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody
	Object queryInventoryHeader(String evtCode, String corp, String language, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> map = new HashMap<>();
		map.put(MapKey.EVT_CODE,evtCode);
		map.put(MapKey.CORP,corp);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		try{
			InventoryHeader header = GetService.getInstance().getMybatisInventoryService().queryInventoryHeader(map);
			ResponseUtils.writeSuccessResponse(response, header);
		}catch(Exception e){
			ResponseUtils.writeErrorResponse(response, e.getMessage());
		}
		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Date 2019年12月24日16:56:07
	 * @Description 查询盘点单下筛选出的资产列表及责任人列表
	 */
	@RequestMapping(value = "/queryInventoryDetails", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody
	Object queryInventoryDetails(String evtCode, String corp, @RequestParam() String workNo, String queryAll, String language, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> map = new HashMap<>();
		map.put(MapKey.EVT_CODE,evtCode);
		map.put(MapKey.CORP,corp);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		map.put(MapKey.WORKNO,workNo);
		try{
			List<InventoryList> inventoryList = new ArrayList<InventoryList>();
			if("Y".equals(queryAll)){
				inventoryList = GetService.getInstance().getMybatisInventoryService().queryInventoryLineList(map);
			}else{
				inventoryList = GetService.getInstance().getMybatisInventoryService().queryInventoryDetails(map);
			}
			map.put("inventoryList",inventoryList);
			ResponseUtils.writeSuccessResponse(response,map);

		}catch(Exception e){
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}

	/**
	 * @Author Steve Zhang
	 * @Date 2019年12月 24日16:56:07
	 * @Description 盘点反馈
	 */
	@RequestMapping(value = "/feedbackInventoryList", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody
	Object feedbackInventoryList(HttpServletRequest request, HttpServletResponse response) {
		InventoryList list = (InventoryList)JsonUtils.getObjectFromJson(ResponseUtils.ReadAsChars(request), InventoryList.class);
		HashMap<String,Object> map = new HashMap<>();
		try{
			InventoryHeader header = new InventoryHeader();
			header.setEvtCode(list.getEvtCode());
			String evtStatus = GetService.getInstance().getMybatisInventoryService().getInventoryStatus(header);
			if(!"PD20".equals(evtStatus)){
				ResponseUtils.writeErrorResponse(response,"盘点单状态为"+evtStatus+", 不允许对盘点清单进行反馈。");
			}else {
				int num = GetService.getInstance().getMybatisInventoryService().feedbackInventoryList(list);
				map.put("updateNum",num);
				ResponseUtils.writeSuccessResponse(response,map);
			}

		}catch(Exception e){
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
		return null;
	}


}

