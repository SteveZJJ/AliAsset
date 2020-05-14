package com.eam.api;

import com.eam.context.*;
import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.model.PickupOrReturnMessage;
import com.eam.mybatis.model.U5CodeValue;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.eam.context.EventDefault.*;
import static java.util.Objects.isNull;


/**************************************
 *@ClassName PickupOrLeaveReturnControllor
 *@Description 入职领用单和离职归还单
 *@Author jason
 *@Date 2018/8/23 上午10:40
 *@Version 1.0D
 **************************************/
@Controller
public class PickupOrReturnController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @Author JasonZhao
	 * @Description createOrUpdatePickupList // 入职领用单
	 * @Date 2018/8/25 12:39:59
	 * @Param [request, response]
	 * @Return java.lang.Objct
	 **/
	@RequestMapping(value = "/createOrUpdatePickupList", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createOrUpdatePickupList(HttpServletRequest request,HttpServletResponse response){
		PickupOrReturnMessage data = getDataFromRequest(request);
		moreDataDefault(data,RZ);
		logger.info("data::" + data.toString());
		doPickupList(response,data);
		return null;
	}
	
	/**
	 * @Author JasonZhao
	 * @Description createOrUpdateLeaveReturn // 离职归还单
	 * @Date 2018/8/25 12:40:23
	 * @Param [request, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/createOrUpdateLeaveReturn", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createOrUpdateLeaveReturn(HttpServletRequest request,HttpServletResponse response){
		PickupOrReturnMessage data = getDataFromRequest(request);
		moreDataDefault(data,LZ);
		logger.info("data::" + data.toString());
		doLeaveReturn(response,data);
		return null;
	}

	/**
	 * @Author SteveZhang
	 * @Description createOrUpdateLeaveOrTransferReturn // 转岗单
	 * @Date 2018/8/25 12:40:23
	 * @Param [request, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/createOrUpdateHRTransferEvent", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object createOrUpdateHRTransferEvent(HttpServletRequest request,HttpServletResponse response){
		PickupOrReturnMessage data = getDataFromRequest(request);
		moreDataDefault(data,ZG);
		logger.info("data::" + data.toString());
		doTransferReturn(response,data);
		return null;
	}
	
	/**
	 * @Author JasonZhao
	 * @Description routinePickup //日常领用、借用
	 * @Date 2018/8/25 12:40:50
	 * @Param [request, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/routinePickup", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object routinePickup(HttpServletRequest request,HttpServletResponse response){
		PickupOrReturnMessage data = getDataFromRequest(request);
		moreDataDefault(data,LY);
		logger.info("routinePickup::" + data.toString());
		dailyPickupReturn(response,data);
		return null;
	}
	
	/**
	 * @Author JasonZhao
	 * @Description borrowDurationExtension // 日常续借
	 * @Date 2018/8/25 12:41:03
	 * @Param [request, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/borrowDurationExtension", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object borrowDurationExtension(HttpServletRequest request,HttpServletResponse response){
		PickupOrReturnMessage data = getDataFromRequest(request);
		moreDataDefault(data,XJ);
		logger.info("borrowDurationExtension::" + data.toString());
		dailyPickupReturn(response,data);
		return null;
	}
	
	/**
	 * @Author JasonZhao
	 * @Description routineReturn // 日常归还
	 * @Date 2018/8/25 12:41:21
	 * @Param [request, response]
	 * @Return java.lang.Object
	 **/
	@RequestMapping(value = "/routineReturn", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object routineReturn(HttpServletRequest request,HttpServletResponse response){
		PickupOrReturnMessage data = getDataFromRequest(request);
		moreDataDefault(data,GH);
		logger.info("routineReturn::" + data.toString());
		dailyPickupReturn(response,data);
		return null;
	}
	
	/***
	 * @Author JasonZhao
	 * @Description dailyPickupReturn // 日常归还、续借、借用
	 * @Date 2018/8/27 10:26:07
	 * @Param [response, data]
	 * @Return void
	 **/
	private void dailyPickupReturn(HttpServletResponse response,PickupOrReturnMessage data){
		try {
			HashMap <String, String> map = new HashMap <>();
			String errMsg = null;
			checkItem(data);
			switch(data.getActionType()) {
				case "I":
					AliResultProcedure res1 = GetService.getInstance()
													  .getMybatisPickupReturnService()
													  .createDailyEvent(data);
					if ( Constants.INTERFACE_SUCCESS.equals(res1.getFlag()) ) {
						map.put("eventCode",res1.getParam1());
						GetService.getInstance()
								.getMybatisPickupReturnService()
								.updateDailyItem(res1.getParam1(),data.getItem(),data.getDetailType());
						if ( ! isNull(data.getAttachments()) )
							data.getAttachments().forEach(
									s -> {
										s.setEventCode(res1.getParam1());
										GetService.getInstance().getMybatisPickupReturnService()
												.insertEventFiles(s);
									}
														 );
					} else
						errMsg = res1.getMessage();
					break;
				case "UH":
					checkReqCode(data.getReqCode()
							,data.getEvtJobType()
							,data.getLanguage());
					GetService.getInstance()
							.getMybatisPickupReturnService()
							.createDailyEvent(data);
					break;
				case "UL":
					GetService.getInstance()
							.getMybatisPickupReturnService()
							.updateDailyItem(
									checkReqCode(data.getReqCode(),data.getEvtJobType(),data.getLanguage())
									,data.getItem()
									,data.getDetailType());
					break;
				default:
					errMsg = " actionType not in :I/UH/UL";
					break;
			}
			if ( StringUtils.isEmpty(errMsg) ) {
				ResponseUtils.writeSuccessResponse(response,map);
			} else
				ResponseUtils.writeErrorResponse(response,errMsg);
		}
		catch (Exception e) {
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
	}
	
	/***
	 * @Author JasonZhao
	 * @Description doPickupList // 入职领用
	 * @Date 2018/8/27 10:26:25
	 * @Param [response, pickup]
	 * @Return void
	 **/
	private void doPickupList(HttpServletResponse response,PickupOrReturnMessage pickup){
		
		try {
			HashMap <String, String> map = new HashMap <>();
			String errMsg = null;
			checkItem(pickup);
			logger.info(pickup.toString());
			switch(pickup.getActionType()) {
				case "I":
					AliResultProcedure res1 = GetService.getInstance()
													  .getMybatisPickupReturnService()
													  .createPickupEvent(pickup);
					if ( Constants.INTERFACE_SUCCESS.equals(res1.getFlag()) ) {
						map.put("eventCode",res1.getParam1());
						GetService.getInstance()
								.getMybatisPickupReturnService()
								.updatePickupItem(res1.getParam1(),pickup.getItem());
						
						if ( ! isNull(pickup.getAttachments()) ) {
							
							pickup.getAttachments().forEach(
									s -> {
										s.setEventCode(res1.getParam1());
										GetService.getInstance().getMybatisPickupReturnService()
												.insertEventFiles(s);
									}
														   );
						}
					} else
						errMsg = res1.getMessage();
					break;
				case "UH":
					checkReqCode(pickup.getReqCode(),pickup.getEvtJobType(),pickup.getLanguage());
					GetService.getInstance()
							.getMybatisPickupReturnService()
							.createPickupEvent(pickup);
					break;
				case "UL":
					GetService.getInstance()
							.getMybatisPickupReturnService()
							.updatePickupItem(
									checkReqCode(pickup.getReqCode(),pickup.getEvtJobType(),pickup.getLanguage())
									,pickup.getItem());
					break;
				default:
					errMsg = " actionType not in :I/UH/UL";
					break;
			}
			if ( StringUtils.isEmpty(errMsg) ) {
				ResponseUtils.writeSuccessResponse(response,map);
			} else
				ResponseUtils.writeErrorResponse(response,errMsg);
		}
		catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
	}
	
	private void doLeaveReturn(HttpServletResponse response,PickupOrReturnMessage pickup){

		try {
			HashMap <String, String> map = new HashMap <>();
			String errMsg = null;
			switch(pickup.getActionType()) {
				case "I":
					AliResultProcedure res1 = GetService.getInstance()
							.getMybatisPickupReturnService()
							.createPickupEvent(pickup);
					if ( Constants.INTERFACE_SUCCESS.equals(res1.getFlag()) ) {
						map.put("eventCode",res1.getParam1());
					} else
						errMsg = res1.getMessage();
					break;
				case "U":
					checkReqCode(pickup.getReqCode(),pickup.getEvtJobType(),pickup.getLanguage());
					GetService.getInstance()
							.getMybatisPickupReturnService()
							.createPickupEvent(pickup);
					break;

				default:
					errMsg = " actionType not in :I/U";
					break;
			}
			if ( StringUtils.isEmpty(errMsg) ) {
				ResponseUtils.writeSuccessResponse(response,map);
			} else
				ResponseUtils.writeErrorResponse(response,errMsg);
		}
		catch (Exception e) {
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
	}


	private void doTransferReturn(HttpServletResponse response,PickupOrReturnMessage pickup){

		try {
			HashMap <String, String> map = new HashMap <>();
			String errMsg = null;
			switch(pickup.getActionType()) {
				case "I":
					AliResultProcedure res1 = GetService.getInstance()
							.getMybatisPickupReturnService()
							.createTransferReturnEvent(pickup);
					if ( Constants.INTERFACE_SUCCESS.equals(res1.getFlag()) ) {
						map.put("eventCode",res1.getParam1());
					} else
						errMsg = res1.getMessage();
					break;
				//根据天忘，目前只会收到一次创建转岗单的信息
//				case "U":
//					//根据天忘，HR无法传入转岗单号，不进行检查
//					//checkReqCode(pickup.getReqCode(),pickup.getEvtJobType(),pickup.getLanguage());
//					GetService.getInstance()
//							.getMybatisPickupReturnService()
//							.createPickupEvent(pickup);
//					break;

				default:
					errMsg = " actionType not in :I/U";
					break;
			}
			if ( StringUtils.isEmpty(errMsg) ) {
				ResponseUtils.writeSuccessResponse(response,map);
			} else
				ResponseUtils.writeErrorResponse(response,errMsg);
		}
		catch (Exception e) {
			ResponseUtils.writeErrorResponse(response,e.getMessage());
		}
	}



	private String checkReqCode(String reqCode,String jobType,String language) throws Exception{
		String evtCode = GetService.getInstance().getMybatisCodeDescService().getPickupEvtCodeByReqCode(reqCode,jobType);
		if ( StringUtils.isEmpty(evtCode) )
			throw new Exception(MyUtils.getErrorMsg(ExceptionDetails.PICKUP_NOT_EXISTS,language));
		return evtCode;
	}
	
	private void checkItem(PickupOrReturnMessage pickup) throws Exception{
		//如果是归还或者续借，则需要判断资产状态是否满足条件
		if ( ArrayUtils.contains(
				new String[]{ GH.getEvtJobType(),XJ.getEvtJobType() }
				,pickup.getEvtJobType()) ) {checkedObjectOk(pickup);}
		//检查三级科目是否存在
		else checkedAssetCategoryOk(pickup);
		
		if ( ! isNull(pickup.getAttachments()) ) {
			AtomicInteger k = new AtomicInteger(1);
			pickup.getAttachments().forEach(
					s -> {
						s.setLine(String.valueOf(k.get()));
						k.set(k.get() + 1);
					});
		}
		
	}
	
	private void checkedObjectOk(PickupOrReturnMessage pickup) throws Exception{
		String errorCheck = GetService.getInstance().getMybatisCodeDescService().checkedObjectOk(pickup);
		if ( ! "".equals(errorCheck) )
			throw new Exception(MyUtils.getErrorMsg(ExceptionDetails.OBJECT_REFUSED_ACTION,pickup.getLanguage()) + errorCheck);
	}
	
	private void checkedAssetCategoryOk(PickupOrReturnMessage pickup) throws Exception{
		logger.info("checkedAssetCategoryOk" + pickup.toString());
		AtomicBoolean checkFlag = new AtomicBoolean(false);
		StringBuilder checkString = new StringBuilder(MyUtils.getErrorMsg(ExceptionDetails.ASSET_NOT_EXISTS,pickup.getLanguage()));
		AtomicInteger k = new AtomicInteger(1);
		try {
			
			pickup.getItem().forEach(
					s -> {
						///如果人力没有赋值行号，则需要重新刷一下行号
						if ( StringUtils.isEmpty(s.getReqLine()) ) {
							s.setReqLine(String.valueOf(k.get() * 10));
							k.set(k.get() + 1);
						}
						//---add by Jason ,如果是入职，则根据传入的常用型号来获取资产类目
						if ( pickup.getEvtJobType().equals(RZ.getEvtJobType()) )
							s.setAssetCategory(
									GetService.getInstance().getMybatisCodeDescService().checkDefaultPartByNormal(s.getNormalModel()));
						//检查资产类目是否存在
						if ( GetService.getInstance().getMybatisCodeDescService().checkPartExists(s.getAssetCategory())==0 ) {
							checkFlag.set(true);
							checkString.append(s.getAssetCategory()).append(",");
						}
						
					});
			
		}
		catch (Exception ignored) {
		
		}
		if ( checkFlag.get() )
			throw new Exception(checkString.toString());
		
		//for (int k = 0 ; k < pickup.getItem().size() ; k++) {
		//	PickupLine line = pickup.getItem().get(k);
		//	///如果人力没有赋值行号，则需要重新刷一下行号
		//	if ( StringUtils.isEmpty(line.getReqLine()) )
		//		pickup.getItem().get(k).setReqLine(String.valueOf(k * 10));
		//	//---add by Jason ,如果是入职，则根据传入的常用型号来获取资产类目
		//	if ( pickup.getEvtJobType().equals(RZ.getEvtJobType()) )
		//		pickup.getItem().get(k).setAssetCategory(
		//				GetService.getInstance().getMybatisCodeDescService().checkDefaultPartByNormal(line.getNormalModel()));
		//	//检查资产类目是否存在
		//	if ( GetService.getInstance().getMybatisCodeDescService().checkPartExists(line.getAssetCategory())==0 ) {
		//		checkFlag = true;
		//		checkString.append(line.getAssetCategory()).append(",");
		//	}
		//}
		//if ( checkFlag )
		//	throw new Exception(checkString.toString());
	}
	
	
	@RequestMapping(value = "/registerExpressNo", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody
	Object registerExpressNo(String eventCode,String expressNo,HttpServletResponse response){
		try {
			GetService.getInstance()
					.getMybatisPickupReturnService().registerExpressNo(eventCode,expressNo);
			ResponseUtils.writeSuccessResponse(response,null);
		}
		catch (Exception e) {
			ResponseUtils.writeErrorResponse(response,e.getMessage());
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
	private PickupOrReturnMessage getDataFromRequest(HttpServletRequest request){
		String body = ResponseUtils.ReadAsChars(request);
		logger.info(body);
		return (PickupOrReturnMessage)
					   JsonUtils.getObjectFromJson(body,PickupOrReturnMessage.class);
	}
	
	/***
	 * @Author JasonZhao
	 * @Description moreDataDefault // 更多配置
	 * @Date 2018/8/27 11:38:04
	 * @Param [data, evtDefault]
	 * @Return void
	 **/
	private void moreDataDefault(PickupOrReturnMessage data,EventDefault evtDefault){
		data.setEvtJobType(evtDefault.getEvtJobType());
		data.setEvtStatus(evtDefault.getEvtStatus());
		data.setOnBehalfMark("Y".equals(data.getOnBehalfMark()) ? Constants.CHECKBOX_CHECKED : Constants.CHECKBOX_UNCHECKED);
		data.setDetailType(evtDefault.getDetailType());
		data.setLanguage(MyUtils.getRealLanguage(data.getLanguage()));
		if(isNull(data.getPersonName())){
			String personName = GetService.getInstance().getMybatisPickupReturnService().getPersonNamebyCode(data.getPersonCode());
			data.setPersonName(personName);
		}
		if(isNull(evtDefault)) return;
		
		switch(evtDefault) {
			case RZ:
				data.setEvtDesc(String.format(RZ.getEvtDesc(),data.getPersonName(),
						//data.getDueDate()
						"")); //入职领用单命名规则更改
				break;
			case LZ:
				data.setEvtDesc(String.format(LZ.getEvtDesc(),data.getPersonName(),data.getDueDate()));
				break;
			case LY:
				//data.setEvtDesc(String.format(LY.getEvtDesc(),data.getResponsibleName(),
				//							  StringUtils.isEmpty(data.getUsage()) ? "" : data.getUsage()));
				//领用的时候，需要区分是否是借用和领用
				
				data.setEvtDesc(String.format(LY.getEvtDesc(),data.getResponsibleName(),
											  StringUtils.isEmpty(data.getUsage()) ? "" : getUserDefinedValue(UserDefinedField.Usage,data.getUsage(),"")));
				break;
			case XJ:
				data.setEvtDesc(String.format(XJ.getEvtDesc(),data.getResponsibleName(),
											  StringUtils.isEmpty(data.getUsage()) ? "" : data.getUsage()));
				break;
			case GH:
				data.setEvtDesc(String.format(GH.getEvtDesc(),data.getResponsibleName(),
											  StringUtils.isEmpty(data.getUsage()) ? "" : data.getUsage()));
				break;
			case ZG:
				data.setEvtDesc(String.format(ZG.getEvtDesc(),data.getPersonName(),
						StringUtils.isEmpty(data.getUsage()) ? "" : data.getUsage()));

			default:
				break;
		}
	}
	
	private String getUserDefinedValue(UserDefinedField field,String checkCode,String checkDesc){
		
		HashMap <String, String> map = new HashMap <>();
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage("ZH"));
		map.put(MapKey.FIELD,field.getField());
		map.put(MapKey.ENTITY,field.getEntity());
		map.put(MapKey.SEARCH_DESC,checkDesc);
		try {
			List <U5CodeValue> checkList = GetService.getInstance().getMybatisCodeDescService().getUserDefinedField(map);
			for (U5CodeValue o : checkList) {
				if ( o.getCode().equals(checkCode) )
					return o.getValue();
			}
		}
		catch (Exception e) {
			return "";
		}
		return "";
		
	}
}
