package com.eam.api;

import com.eam.context.Constants;
import com.eam.context.EventDefault;
import com.eam.context.MapKey;
import com.eam.mybatis.model.*;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.zone.ZoneRules;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.eam.context.EventDefault.*;
import static java.util.Objects.isNull;


@Controller
public class TransferAndAllocateController {

    private final Logger logger = LoggerFactory.getLogger(getClass());



    /**
     * @Author Steve Zhang
     * @Description transferApply // 转移申请
     * @Date 2019年4月29日15:45:36
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/transferApply", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object transferApply(HttpServletRequest request, HttpServletResponse response){
        TransferOrAllocateMessage data = getDataFromRequest(request);
        //data.setTransferMode("OFFICE");
        moreDataDefault(data,ZY);
        logger.info("transferApply::" + data.toString());
        for(TransferLine tl:data.getItem()){
            tl.setLineStatus("success");
        }
        doTransfer(response,data);
        return null;
    }

    /**
     * @Author Steve Zhang
     * @Description allocateApply // 调拨申请
     * @Date 2019年4月29日15:45:43
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/allocateApply", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object allocateApply(HttpServletRequest request, HttpServletResponse response){
        TransferOrAllocateMessage data = getDataFromRequest(request);
        moreDataDefault(data,DB);
        logger.info("allocateApply::" + data.toString());
        doAllocate(response,data);
        return null;
    }

    /**
     * @Author Steve Zhang
     * @Description transferApply // 转让申请
     * @Date 2019年4月29日15:45:36
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/assignmentApply", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object assignmentApply(HttpServletRequest request, HttpServletResponse response){
        TransferOrAllocateMessage data = getDataFromRequest(request);
        moreDataDefault(data,ZR);
        logger.info("assignmentApply::" + data.toString());
        doTransfer(response,data);
        return null;
    }


    /**
     * @Author Steve Zhang
     * @Description HEMAAssignApply // 盒马转移转让申请
     * @Date 2019年4月29日15:45:36
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/HEMAAssignApply", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object HEMAAssignApply(HttpServletRequest request, HttpServletResponse response){
        TransferOrAllocateMessage data = getDataFromRequest(request);
        //盒马转移转让调用时赋值transferMode为“SHOP”
        data.setTransferMode("SHOP");
        //如果前台传入操作类型为空则报错
        if(data.getActionType()==null){
            ResponseUtils.writeErrorResponse(response, "actionType cannot be null");
        }else if(data.getActionType().equals("I")) { //如果操作类型为I 创建单据
            if (data.getFromOUCode().equals(data.getToOUCode())) { //与天忘沟通，确认从前台传入的fromOU和toOU来判断是转移还是转让
                moreDataDefault(data, ZY);
                doTransfer(response, data);
            } else {
                moreDataDefault(data, ZR);
                doAssignment(response, data);
//            if(!isNull(data.getFromStore())&&!isNull(data.getToStore())) {
//                moreDataDefault(data, DB);
//                doAllocate(response,data);
//            }
            }
        }else{
            if(data.getEvtCode()==null) { //如果操作类型为更新，则工单号为必填，不填报错
                ResponseUtils.writeErrorResponse(response, "Event Code cannot be null");
            }else{
                String jobType = GetService.getInstance()
                        .getMybatisTransferAllocateService()
                        .getEvtJobtype(data.getEvtCode());
                if(jobType==null){ //检查传入更新单据为转移单或转让单，若未填单据类型，则报错
                    ResponseUtils.writeErrorResponse(response, "Event Code is invalid");
                }else{ //根据jobType判断走哪一种单据
                    data.setEvtJobType(jobType);
                    if(data.getEvtJobType().equals(ZY.getEvtJobType())){
                        moreDataDefault(data, ZY);
                        doTransfer(response, data); //转移单
                    }else if(data.getEvtJobType().equals(ZR.getEvtJobType())){
                        moreDataDefault(data, ZR);
                        doAssignment(response, data); //转让单
                    }else{
                        ResponseUtils.writeErrorResponse(response, "The event Code not belongs to transfer or allocate event");
                    }
                }

            }

        }

        logger.info("HEMAAssignApply::" + data.toString());

        return null;
    }



    /***
     * @Author Steve Zhang
     * @Description doTransfer 转移
     * @Date 2019年4月17日11:09:22
     * @Param [response, data]
     * @Return void
     **/
    private void doTransfer(HttpServletResponse response, TransferOrAllocateMessage data){
        try {
            HashMap<String, String> map = new HashMap<>();
            String errMsg = null;
            HashMap<String, Object> map1 = checkItem(data);
            if (!StringUtils.isEmpty(map1.get("errMsg"))) {
                ResponseUtils.writeResponse(response, JsonUtils.getJsonFromObject(new AliResponseErrData(Constants.INTERFACE_ERROR, (String) map1.get(MapKey.ERR_MSG), null, map1)));
            } else {
                logger.info(data.getActionType());
                switch (data.getActionType()) {
                    case "I":
                        AliResultProcedure res1 = GetService.getInstance()
                                .getMybatisTransferAllocateService()
                                .createTransferEvent(data);
                        if (Constants.INTERFACE_SUCCESS.equals(res1.getFlag())) {
                            map.put("eventCode", res1.getParam1());
                            GetService.getInstance()
                                    .getMybatisTransferAllocateService()
                                    .updateApplyItem(res1.getParam1(), data.getItem(), data.getDetailType());
                            if (!isNull(data.getAttachments())) {
                                map1.put("TableId", "U5EVENTFILE");
                                data.getAttachments().forEach(
                                        s -> {
                                            s.setEventCode(res1.getParam1());
                                            String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
                                            s.setLine(lineId);
                                            GetService.getInstance().getMybatisScrapDisposeService()
                                                    .insertEventFiles(s);
                                        }
                                );
                            }
                        } else
                            errMsg = res1.getMessage();
                        break;
                    case "UH":
                        if ("".equals(data.getEvtCode()) || isNull(data.getEvtCode()))
                            errMsg = "Require event code when update event header.";
                        AliResultProcedure res2 = GetService.getInstance()
                                .getMybatisTransferAllocateService()
                                .createTransferEvent(data);
                        if (Constants.INTERFACE_SUCCESS.equals(res2.getFlag())) {
                            if (!isNull(data.getAttachments())) {
                                map1.put("TableId", "U5EVENTFILE");
                                GetService.getInstance().getMybatisScrapDisposeService().cleanEventFiles(data.getEvtCode());
                                data.getAttachments().forEach(
                                        s -> {
                                            s.setEventCode(data.getEvtCode());
                                            String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
                                            s.setLine(lineId);
                                            GetService.getInstance().getMybatisScrapDisposeService()
                                                    .insertEventFiles(s);
                                        }
                                );
                            }
                        } else
                            errMsg = res2.getMessage();
                        break;
                    case "UL":
                        GetService.getInstance()
                                .getMybatisTransferAllocateService()
                                .updateApplyItem(
                                        data.getEvtCode()
                                        , data.getItem()
                                        , data.getDetailType());
                        break;
                    default:
                        errMsg = " actionType not in :I/UH/UL";
                        break;
                }

                if (StringUtils.isEmpty(errMsg)) {
                    ResponseUtils.writeSuccessResponse(response, map);
                } else
                    ResponseUtils.writeErrorResponse(response, errMsg);
            }
        }
        catch (Exception e) {
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }
    }



    /***
     * @Author Steve Zhang
     * @Description doAllocate 调拨
     * @Date 2019年4月29日16:58:04
     * @Param [response, data]
     * @Return void
     **/
    private void doAllocate(HttpServletResponse response, TransferOrAllocateMessage data){
        try {
            HashMap<String, String> map = new HashMap<>();
            String errMsg = null;
            HashMap<String, Object> map1 = checkItem(data);
            if (!StringUtils.isEmpty(map1.get("errMsg"))) {
                logger.info(map1.toString());
                AliResponseErrData ARE = new AliResponseErrData(Constants.INTERFACE_ERROR, (String) map1.get(MapKey.ERR_MSG), "V", null, map1);
                logger.info(ARE.toString());
                logger.info(JsonUtils.getJsonFromObject(ARE));
                ResponseUtils.writeResponse(response, JsonUtils.getJsonFromObject(ARE));
            } else {
                switch (data.getActionType()) {
                    case "I":
                        AliResultProcedure res1 = GetService.getInstance()
                                .getMybatisTransferAllocateService()
                                .createTransferEvent(data);
                        if (Constants.INTERFACE_SUCCESS.equals(res1.getFlag())) {
                            map.put("eventCode", res1.getParam1());
                            GetService.getInstance()
                                    .getMybatisTransferAllocateService()
                                    .updateApplyItem(res1.getParam1(), data.getItem(), data.getDetailType());
                            if (!isNull(data.getAttachments())) {
                                map1.put("TableId", "U5EVENTFILE");
                                data.getAttachments().forEach(
                                        s -> {
                                            s.setEventCode(res1.getParam1());
                                            String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
                                            s.setLine(lineId);
                                            GetService.getInstance().getMybatisScrapDisposeService()
                                                    .insertEventFiles(s);
                                        }
                                );
                            }
                        } else
                            errMsg = res1.getMessage();
                        break;
                    case "UH":
                        if ("".equals(data.getEvtCode()) || isNull(data.getEvtCode()))
                            errMsg = "Require event code when update event header.";
                        AliResultProcedure res2 = GetService.getInstance()
                                .getMybatisTransferAllocateService()
                                .createTransferEvent(data);
                        if (Constants.INTERFACE_SUCCESS.equals(res2.getFlag())) {
                            if (!isNull(data.getAttachments())) {
                                map1.put("TableId", "U5EVENTFILE");
                                data.getAttachments().forEach(
                                        s -> {
                                            s.setEventCode(data.getEvtCode());
                                            String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
                                            s.setLine(lineId);
                                            GetService.getInstance().getMybatisScrapDisposeService()
                                                    .insertEventFiles(s);
                                        }
                                );
                            }
                        } else
                            errMsg = res2.getMessage();
                        break;
                    case "UL":
                        GetService.getInstance()
                                .getMybatisTransferAllocateService()
                                .updateApplyItem(
                                        data.getEvtCode()
                                        , data.getItem()
                                        , data.getDetailType());
                        break;
                    default:
                        errMsg = " actionType not in :I/UH/UL";
                        break;
                }

                if (StringUtils.isEmpty(errMsg)) {
                    ResponseUtils.writeSuccessResponse(response, map);
                } else
                    ResponseUtils.writeErrorResponse(response, errMsg);
            }
        }
        catch (Exception e) {
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }
    }

    /***
     * @Author Steve Zhang
     * @Description doAssignment 跨OU转让
     * @Date 2019年4月17日11:09:22
     * @Param [response, data]
     * @Return void
     **/
    private void doAssignment(HttpServletResponse response, TransferOrAllocateMessage data){
        try {
            HashMap<String, String> map = new HashMap<>();
            String errMsg = null;
            HashMap<String, Object> map1 = checkItem(data);
            if (!StringUtils.isEmpty(map1.get("errMsg"))) {
                ResponseUtils.writeResponse(response, JsonUtils.getJsonFromObject(new AliResponseErrData(Constants.INTERFACE_ERROR, (String) map1.get(MapKey.ERR_MSG), "V", null, map1)));
            } else {
                switch (data.getActionType()) {
                    case "I":
                        AliResultProcedure res1 = GetService.getInstance()
                                .getMybatisTransferAllocateService()
                                .createTransferEvent(data);
                        if (Constants.INTERFACE_SUCCESS.equals(res1.getFlag())) {
                            map.put("eventCode", res1.getParam1());
                            GetService.getInstance()
                                    .getMybatisTransferAllocateService()
                                    .updateApplyItem(res1.getParam1(), data.getItem(), data.getDetailType());
                            if (!isNull(data.getAttachments())) {
                                map1.put("TableId", "U5EVENTFILE");
                                data.getAttachments().forEach(
                                        s -> {
                                            s.setEventCode(res1.getParam1());
                                            String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
                                            s.setLine(lineId);
                                            GetService.getInstance().getMybatisScrapDisposeService()
                                                    .insertEventFiles(s);
                                        }
                                );
                            }
                        } else
                            errMsg = res1.getMessage();
                        break;
                    case "UH":
                        if ("".equals(data.getEvtCode()) || isNull(data.getEvtCode()))
                            errMsg = "Require event code when update event header.";
                        AliResultProcedure res2 = GetService.getInstance()
                                .getMybatisTransferAllocateService()
                                .createTransferEvent(data);
                        if (Constants.INTERFACE_SUCCESS.equals(res2.getFlag())) {
                            if (!isNull(data.getAttachments())) {
                                map1.put("TableId", "U5EVENTFILE");
                                GetService.getInstance().getMybatisScrapDisposeService().cleanEventFiles(data.getEvtCode());
                                data.getAttachments().forEach(
                                        s -> {
                                            s.setEventCode(data.getEvtCode());
                                            String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1) + "";
                                            s.setLine(lineId);
                                            GetService.getInstance().getMybatisScrapDisposeService()
                                                    .insertEventFiles(s);
                                        }
                                );
                            }
                        } else
                            errMsg = res2.getMessage();
                        break;
                    case "UL":
                        GetService.getInstance()
                                .getMybatisTransferAllocateService()
                                .updateApplyItem(
                                        data.getEvtCode()
                                        , data.getItem()
                                        , data.getDetailType());
                        break;
                    default:
                        errMsg = " actionType not in :I/UH/UL";
                        break;
                }

                if (StringUtils.isEmpty(errMsg)) {
                    ResponseUtils.writeSuccessResponse(response, map);
                } else
                    ResponseUtils.writeErrorResponse(response, errMsg);
            }
        }
        catch (Exception e) {
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }
    }



    /**
     * @Author Steve Zhang
     * @Description queryTransferEvent // 查询转移单据
     * @Date 2019年5月7日14:38:37
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/queryTransferEvent", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object queryTransferEvent(String evtCode, String language, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        map.put(MapKey.EVT_CODE,evtCode);
        map.put(MapKey.LANGUAGE,language);
        map.put(MapKey.EVT_JOB_TYPE,ZY.getEvtJobType());
        try{
            TransferOrAllocateMessage event = queryEvent(map);
            ResponseUtils.writeSuccessResponse(response,event);
        } catch (Exception e){
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }

        return null;
    }

    /**
     * @Author Steve Zhang
     * @Description queryAllocateEvent // 查询调拨单据
     * @Date 2019年4月29日17:20:54
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/queryAllocateEvent", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object queryAllocateEvent(String evtCode, String language, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        map.put(MapKey.EVT_CODE,evtCode);
        map.put(MapKey.LANGUAGE,language);
        map.put(MapKey.EVT_JOB_TYPE,DB.getEvtJobType());
        map.put(MapKey.EVT_DETAILTYPE,DB.getDetailType());
        try{
            TransferOrAllocateMessage event = queryEvent(map);
            ResponseUtils.writeSuccessResponse(response,event);
        } catch (Exception e){
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }

        return null;
    }


    /**
     * @Author Steve Zhang
     * @Description queryAssignmentEvent // 查询转让单据
     * @Date 2019年7月15日10:52:08
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/queryAssignmentEvent", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object queryAssignmentEvent(String evtCode, String language, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        map.put(MapKey.EVT_CODE,evtCode);
        map.put(MapKey.LANGUAGE,language);
        map.put(MapKey.EVT_JOB_TYPE,ZR.getEvtJobType());
        try{
            TransferOrAllocateMessage event = queryEvent(map);
            ResponseUtils.writeSuccessResponse(response,event);
        } catch (Exception e){
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }

        return null;
    }

    /**
     * @Author Steve Zhang
     * @Description queryHEMAAssignmentEvent // 查询转让单据
     * @Date 2019年7月15日10:52:08
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/queryHEMAAssignmentEvent", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object queryHEMAAssignmentEvent(String evtCode, String language, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        map.put(MapKey.EVT_CODE,evtCode);
        map.put(MapKey.LANGUAGE,language);
        map.put(MapKey.EVT_JOB_TYPE,ZR.getEvtJobType());
        try{
            TransferOrAllocateMessage event = queryEvent(map);
            if(isNull(event)){
                map.remove(MapKey.EVT_JOB_TYPE);
                map.put(MapKey.EVT_JOB_TYPE,ZY.getEvtJobType());
                event = queryEvent(map);
            }
            ResponseUtils.writeSuccessResponse(response,event);
        } catch (Exception e){
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }

        return null;
    }



    /**
     * @Author Steve Zhang
     * @Description getDataFromRequest // 获取body信息
     * @Date 2019年4月29日17:20:59
     * @Param [request]
     * @Return com.eam.mybatis.model.TransferOrAllocateMessage
     **/
    private TransferOrAllocateMessage getDataFromRequest(HttpServletRequest request){
        String body = ResponseUtils.ReadAsChars(request);
        logger.info(body);
        return (TransferOrAllocateMessage)
                JsonUtils.getObjectFromJson(body, TransferOrAllocateMessage.class);
    }


    /***
     * @Author Steve Zhang
     * @Description moreDataDefault // 更多配置
     * @Date 2019年4月16日16:17:14
     * @Param [data, evtDefault]
     * @Return void
     **/
    private void moreDataDefault(TransferOrAllocateMessage data, EventDefault evtDefault){
        data.setEvtJobType(evtDefault.getEvtJobType());
        if(isNull(data.getEvtStatus())||"I".equals(data.getActionType()))
            data.setEvtStatus(evtDefault.getEvtStatus());
        if(isNull(data.getPersonName())){
            String personName = GetService.getInstance().getMybatisPickupReturnService().getPersonNamebyCode(data.getPersonCode());
            data.setPersonName(personName);
        }
        data.setDetailType(evtDefault.getDetailType());
        data.setLanguage(MyUtils.getRealLanguage(data.getLanguage()));
        if(isNull(evtDefault)) return;
        switch(evtDefault) {
            case ZY:
                data.setEvtDesc(String.format(ZY.getEvtDesc(),data.getPersonName(),"")); //转移申请单命名规则更改
                break;
            case DB:
                data.setEvtDesc(String.format(DB.getEvtDesc(),data.getPersonName(),"")); //调拨申请单命名规则更改
                break;
            case ZR:
                data.setEvtDesc(String.format(ZR.getEvtDesc(),data.getPersonName(),"")); //转让申请单命名规则更改
                break;
            default:
                break;
        }
    }


    private HashMap<String,Object> checkItem(TransferOrAllocateMessage data) {
        String errMsg = null;
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> respMap = new HashMap<>();
        int totalnum = 0;
        int passnum = 0;
        int failnum = 0;
        final String TableId = "U5OBJBATCERESULTFLAGID";
        List<String> TDNCodes = new ArrayList<>();


        //开始校验
        if (!MyUtils.isListEmpty(data.getItem())) {
            for (TransferLine line : data.getItem()) {
                if (!StringUtils.isEmpty(line.getAssetCode()))
                    TDNCodes.add(line.getAssetCode());
                else
                    errMsg = "Asset TDN Codes can not be empty";
            }

            //  获取一个临时FlagId
            int FlagId = 0;
            map.put(MapKey.LANGUAGE, data.getLanguage());
            map.put("TableId", TableId);
            FlagId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map);
            map.put("FlagId", FlagId);
            map.put("process", data.getEvtJobType());
            map.put(MapKey.OBJ_CODE_LIST, TDNCodes);
            map.put(MapKey.CORP,data.getCorp());
            map.put("receiverCorp",data.getReceiverCorp());
            map.put(MapKey.DEPARTMENT,data.getDepartment());
            map.put(MapKey.FROM_STORE, data.getFromStore());
            map.put(MapKey.TO_STORE, data.getToStore());
            map.put(MapKey.WORKNO,data.getApplicantCode());
            map.put(MapKey.RECEIVER,data.getReceiverCode());
            map.put(MapKey.FROM_LOCATION,data.getFromLocation());
            map.put(MapKey.TO_LOCATION,data.getToLocation());
            map.put(MapKey.TRANSFERMODE,data.getTransferMode());
            map.put(MapKey.FROM_OUCODE,data.getFromOUCode());
            //	向临时表插入数据
            totalnum = GetService.getInstance().getMybatisReceiptService().insertAssetCodes(map);

            AliResultProcedure result = GetService.getInstance().getMybatisReceiptService().VerifyTDNList(map);

            if (Constants.INTERFACE_SUCCESS.equals(result.getFlag())) {
                //  返回校验通过的资产详情和失败的资产编号
                passnum = GetService.getInstance().getMybatisReceiptService().getPassedAssetsNum(map);
                failnum = GetService.getInstance().getMybatisReceiptService().getFailedAssetsNum(map);
                //List<U5MyAsset> AssetList = GetService.getInstance().getMybatisReceiptService().getVerifiedAssets(map);
                List<FailAsset> failList = GetService.getInstance().getMybatisReceiptService().getFailedAssetsList(map);
                respMap.put("totalNum", totalnum);
                respMap.put("passNum", passnum);
                respMap.put("failNum", failnum);
                //respMap.put("assetList", AssetList);
                respMap.put("failList",failList);
                if(failnum>0){
                    logger.info("Asset Verify Failed. Please check fail asset list.");
                    errMsg = "Asset Verify Failed. Please check fail asset list.";
                }

            } else {
                errMsg = "Insert Data Error: The Asset Codes cannot be inserted into Database.";
            }
            respMap.put(MapKey.ERR_MSG,errMsg);
        }
        return respMap;
    }


    /***
     * @Author Steve Zhang
     * @Description queryEvent // 查询工单
     * @Date 2019年4月29日17:21:05
     * @Param [map]
     * @Return void
     **/
    private TransferOrAllocateMessage queryEvent(HashMap<String,String> map){
        TransferOrAllocateMessage event = GetService.getInstance()
                .getMybatisTransferAllocateService().queryEvent(map);

        return event;
    }


    /**
     * @Author Steve Zhang
     * @Description updateEventLine // 更新单行数据
     * @Date 2019年9月6日11:19:00
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    //@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class,timeout=1,isolation= Isolation.DEFAULT)
    @RequestMapping(value = "/updateEventLine", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object updateEventLine(HttpServletRequest request, HttpServletResponse response){
        TransferLineList transferLineList = (TransferLineList)JsonUtils.getObjectFromJson(ResponseUtils.ReadAsChars(request), TransferLineList.class);
        String flag = "S";
        String errMsg = null;
        for(TransferLine line : transferLineList.getLines()) {
            if (line.getAssetCode() == null || line.getCorp() == null || line.getEvtCode() == null){
                flag = "E";
                errMsg = "AssetCode, corp, evtCode cant be null";
            //ResponseUtils.writeErrorResponse(response, "AssetCode, corp, evtCode cant be null");
            }else {
                try {
                    AliResultProcedure resp = GetService.getInstance()
                            .getMybatisTransferAllocateService().updateEventLine(line);
                    if(!"S".equals(resp.getFlag()))
                        flag = resp.getFlag();
                } catch (Exception e) {
                    ResponseUtils.writeErrorResponse(response, e.getMessage());
                }
            }
        }

        if (!"S".equals(flag))
            ResponseUtils.writeErrorResponse(response, "Update failed."+errMsg);
        else
            ResponseUtils.writeSuccessResponse(response, null);

        return null;
    }


    /**
     * @Author Steve Zhang
     * @Description queryEventLines // 查询未完成财务同步单行数据
     * @Date 2019年9月6日11:19:00
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/queryEventLines", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object queryEventLines(String evtCode, String evtStatus, String evtJobtype,String assetCode, String corp, String lineStatus, String language,HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        map.put(MapKey.EVT_CODE,evtCode);
        map.put(MapKey.LANGUAGE,language);
        map.put(MapKey.EVT_JOB_TYPE,evtJobtype);
        map.put(MapKey.ASSET_CODE,assetCode);
        map.put(MapKey.CORP,corp);
        map.put(MapKey.LINE_STATUS,lineStatus);
        map.put(MapKey.EVT_STATUS,evtStatus);
        try{
            List<TransferLine> lines=  GetService.getInstance()
                    .getMybatisTransferAllocateService().queryEventLines(map);
            ResponseUtils.writeSuccessResponse(response,lines);
        } catch (Exception e){
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }

        return null;
    }

}
