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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.eam.context.EventDefault.*;
import static java.util.Objects.isNull;


@Controller
public class ScrapAndDisposeController {

    private final Logger logger = LoggerFactory.getLogger(getClass());



    /**
     * @Author Steve Zhang
     * @Description scrapApply // 报废申请
     * @Date 2019年4月16日16:17:33
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/scrapApply", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object scrapApply(HttpServletRequest request, HttpServletResponse response){
        ScrapOrDisposeMessage data = getDataFromRequest(request);
        moreDataDefault(data,BF);
        logger.info("scrapApply::" + data.toString());
        doScrap(response,data);
        return null;
    }

    /**
     * @Author Steve Zhang
     * @Description disposeApply // 处置申请
     * @Date 2019年4月16日16:17:33
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/disposeApply", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object disposeApply(HttpServletRequest request, HttpServletResponse response){
        ScrapOrDisposeMessage data = getDataFromRequest(request);
        moreDataDefault(data,CZ);
        logger.info("disposeApply::" + data.toString());
        doDispose(response,data);
        return null;
    }

    /**
     * @Author Steve Zhang
     * @Description secureApply // 安全处理申请
     * @Date 2019年4月16日16:17:33
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/secureApply", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object secureApply(HttpServletRequest request, HttpServletResponse response){
        ScrapOrDisposeMessage data = getDataFromRequest(request);
        moreDataDefault(data,AQCL);
        logger.info("secureApply::" + data.toString());
        doSecure(response,data);
        return null;
    }



    /***
     * @Author Steve Zhang
     * @Description doScrap 报废
     * @Date 2019年4月17日11:09:22
     * @Param [response, data]
     * @Return void
     **/
    private void doScrap(HttpServletResponse response, ScrapOrDisposeMessage data){
        try {
            HashMap<String, String> map = new HashMap <>();
            String errMsg = null;
            HashMap<String, Object> map1 = checkItem(data);
            if(!StringUtils.isEmpty(map1.get("errMsg"))){
                ResponseUtils.writeResponse(response,JsonUtils.getJsonFromObject(new AliResponseErrData(Constants.INTERFACE_ERROR, (String)map1.get(MapKey.ERR_MSG), "V",null,map1)));
            }else {
                switch (data.getActionType()) {
                    case "I":
                        AliResultProcedure res1 = GetService.getInstance()
                                .getMybatisScrapDisposeService()
                                .createScrapEvent(data);
                        if (Constants.INTERFACE_SUCCESS.equals(res1.getFlag())) {
                            map.put("eventCode", res1.getParam1());
                            GetService.getInstance()
                                    .getMybatisScrapDisposeService()
                                    .updateApplyItem(res1.getParam1(), data.getLanguage(), data.getItem(), data.getDetailType());
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
                                .getMybatisScrapDisposeService()
                                .createScrapEvent(data);
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
                                .getMybatisScrapDisposeService()
                                .updateApplyItem(
                                        data.getEvtCode()
                                        , data.getLanguage()
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
     * @Description doDispose 处置
     * @Date 2019年4月19日11:23:59
     * @Param [response, data]
     * @Return void
     **/
    private void doDispose(HttpServletResponse response, ScrapOrDisposeMessage data){
        try {
            HashMap<String, String> map = new HashMap <>();
            String errMsg = null;
            HashMap<String, Object> map1 = checkItem(data);

            if(!StringUtils.isEmpty(map1.get("errMsg"))){
                logger.info(map1.toString());
                AliResponseErrData ARE = new AliResponseErrData(Constants.INTERFACE_ERROR,(String)map1.get(MapKey.ERR_MSG),"V",null,map1);
                logger.info(ARE.toString());
                ResponseUtils.writeResponse(response,JsonUtils.getJsonFromObject(ARE));
            }else {
                switch (data.getActionType()) {
                    case "I":
                        AliResultProcedure res1 = GetService.getInstance()
                                .getMybatisScrapDisposeService()
                                .createScrapEvent(data);
                        if (Constants.INTERFACE_SUCCESS.equals(res1.getFlag())) {
                            map.put("eventCode", res1.getParam1());
                            GetService.getInstance()
                                    .getMybatisScrapDisposeService()
                                    .updateApplyItem(res1.getParam1(), data.getLanguage(), data.getItem(), data.getDetailType());
                            if (!isNull(data.getAttachments())){
                                map1.put("TableId","U5EVENTFILE");
                                data.getAttachments().forEach(
                                    s -> {
                                        s.setEventCode(res1.getParam1());
                                        String lineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map1)+"";
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
                                .getMybatisScrapDisposeService()
                                .createScrapEvent(data);
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
                            if (!isNull(data.getQuotations())) {
                                updateQuotationsLines(data.getQuotations(), data.getEvtCode());
                            }
                        }else
                            errMsg = res2.getMessage();

                        break;
                    case "UL":
                        GetService.getInstance()
                                .getMybatisScrapDisposeService()
                                .updateApplyItem(
                                        data.getEvtCode()
                                        , data.getLanguage()
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
     * @Description doSecure 安全处理
     * @Date 2019年5月20日17:38:18
     *
     * @Param [response, data]
     * @Return void
     **/
    private void doSecure(HttpServletResponse response, ScrapOrDisposeMessage data){
        try {
            HashMap<String, String> map = new HashMap <>();
            String errMsg = null;
            HashMap<String, Object> map1 = checkItem(data);
            if(!StringUtils.isEmpty(map1.get("errMsg"))){
                ResponseUtils.writeResponse(response,JsonUtils.getJsonFromObject(new AliResponseErrData(Constants.INTERFACE_ERROR,(String)map1.get(MapKey.ERR_MSG),null,map1)));
            }else{
                switch(data.getActionType()) {
                    case "I":
                        //校验
                        AliResultProcedure res1 = GetService.getInstance()
                                .getMybatisScrapDisposeService()
                                .createScrapEvent(data);
                        if ( Constants.INTERFACE_SUCCESS.equals(res1.getFlag()) ) {
                            map.put("eventCode",res1.getParam1());
                            GetService.getInstance()
                                    .getMybatisScrapDisposeService()
                                    .updateApplyItem(res1.getParam1(),data.getLanguage(),data.getItem(),data.getDetailType());
                            if ( ! isNull(data.getAttachments()) ) {
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
                        if("".equals(data.getEvtCode())||isNull(data.getEvtCode()))
                            errMsg = "Require event code when update event header.";
                        AliResultProcedure res2 = GetService.getInstance()
                                .getMybatisScrapDisposeService()
                                .createScrapEvent(data);
                        if ( Constants.INTERFACE_SUCCESS.equals(res2.getFlag()) ) {

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
                                .getMybatisScrapDisposeService()
                                .updateApplyItem(
                                        data.getEvtCode()
                                        ,data.getLanguage()
                                        ,data.getItem()
                                        ,data.getDetailType());
                        break;
                    default:
                        errMsg = " actionType not in :I/UH/UL";
                        break;
                }
                if ( StringUtils.isEmpty(errMsg) ) {
                    ResponseUtils.writeSuccessResponse(response,map);
                } else {
                    ResponseUtils.writeErrorResponse(response, errMsg);
                }
            }
        }
        catch (Exception e) {
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }
    }



    /**
     * @Author Steve Zhang
     * @Description queryScrapEvent // 查询报废单据
     * @Date 2019年4月16日16:17:33
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/queryScrapEvent", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object queryScrapEvent(String evtCode, String language, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        map.put(MapKey.EVT_CODE,evtCode);
        map.put(MapKey.LANGUAGE,language);
        map.put(MapKey.EVT_JOB_TYPE,BF.getEvtJobType());
        try{
            ScrapOrDisposeMessage event = queryEvent(map);
            ResponseUtils.writeSuccessResponse(response,event);
        } catch (Exception e){
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }

        return null;
    }

    /**
     * @Author Steve Zhang
     * @Description queryDisposeEvent // 查询处置单据
     * @Date 2019年4月23日15:06:37
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/queryDisposeEvent", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object queryDisposeEvent(String evtCode, String language, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        map.put(MapKey.EVT_CODE,evtCode);
        map.put(MapKey.LANGUAGE,language);
        map.put(MapKey.EVT_JOB_TYPE,CZ.getEvtJobType());
        try{
            ScrapOrDisposeMessage event = queryEvent(map);
            ResponseUtils.writeSuccessResponse(response,event);
        } catch (Exception e){
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }

        return null;
    }

    /**
     * @Author Steve Zhang
     * @Description querySecureEvent // 查询处置单据
     * @Date 2019年5月24日16:35:37
     * @Param [request, response]
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/querySecureEvent", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object querySecureEvent(String evtCode, String language, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        map.put(MapKey.EVT_CODE,evtCode);
        map.put(MapKey.LANGUAGE,language);
        map.put(MapKey.EVT_JOB_TYPE,AQCL.getEvtJobType());
        try{
            ScrapOrDisposeMessage event = queryEvent(map);
            ResponseUtils.writeSuccessResponse(response,event);
        } catch (Exception e){
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }

        return null;
    }





    /**
     * @Author Steve Zhang
     * @Description getDataFromRequest // 获取body信息
     * @Date 2019年4月16日16:17:20
     * @Param [request]
     * @Return com.eam.mybatis.model.ScrapOrDisposeMessage
     **/
    private ScrapOrDisposeMessage getDataFromRequest(HttpServletRequest request){
        String body = ResponseUtils.ReadAsChars(request);
        logger.info(body);
        return (ScrapOrDisposeMessage)
                JsonUtils.getObjectFromJson(body, ScrapOrDisposeMessage.class);
    }


    /***
     * @Author Steve Zhang
     * @Description moreDataDefault // 更多配置
     * @Date 2019年4月16日16:17:14
     * @Param [data, evtDefault]
     * @Return void
     **/
    private void moreDataDefault(ScrapOrDisposeMessage data, EventDefault evtDefault){
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
            case BF:
                data.setEvtDesc(String.format(BF.getEvtDesc(),data.getPersonName(),"")); //报废申请单命名规则更改
                break;
            case CZ:
                data.setEvtDesc(String.format(CZ.getEvtDesc(),data.getPersonName(),""));
                break;
            case AQCL:
                data.setEvtDesc(String.format(AQCL.getEvtDesc(),data.getPersonName(),""));
            default:
                break;
        }
    }

    /***
     * @Author Steve Zhang
     * @Description queryEvent // 查询单据信息
     * @Date 2019年4月16日16:17:14
     * @Param [data, evtDefault]
     * @Return void
     **/
    private ScrapOrDisposeMessage queryEvent(HashMap<String,String> map){
        ScrapOrDisposeMessage event = GetService.getInstance()
                .getMybatisScrapDisposeService().queryEvent(map);

        return event;
    }


    private HashMap<String,Object> checkItem(ScrapOrDisposeMessage data) {
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
            for (ScrapLine line : data.getItem()) {
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
            map.put(MapKey.DEPARTMENT,data.getDepartment());
            map.put(MapKey.OBJ_CODE_LIST, TDNCodes);
            map.put(MapKey.CORP,data.getCorp());

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
                    errMsg = "Asset Verify Failed. Please check fail asset list.";
                }

            } else {
                errMsg = "Insert Data Error: The Asset Codes cannot be inserted into Database.";
            }
            respMap.put(MapKey.ERR_MSG,errMsg);
        }
        return respMap;
    }

    private void updateQuotationsLines(List<Quotation> quotations, String evtCode){
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String,Object> map3 = new HashMap<>();
        String TableId = "U5SUPPLIERSBIDLIST";
        String TableId1 = "U5EVENTFILE";
        map2.put("TableId",TableId);
        map3.put("TableId",TableId1);
        //先清理已有报价行
        GetService.getInstance().getMybatisScrapDisposeService().cleanQuotationsLines(evtCode);

        quotations.forEach(

                s ->{
                    s.setEvtCode(evtCode);

                    String FlagId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map2)+"";
                    s.setCode(FlagId);
                    GetService.getInstance().getMybatisScrapDisposeService()
                            .insertQuotationsLines(s);
                    s.getAttachments().forEach(
                            k ->{
                                String LineId = GetService.getInstance().getMybatisReceiptService().getNextFlagId(map3)+"";
                                k.setLine(LineId);
                                k.setBidLine(FlagId);
                                k.setEventCode(evtCode);
                                GetService.getInstance().getMybatisScrapDisposeService()
                                        .insertEventFiles(k);
                            }
                    );
                }
        );
    }

    @RequestMapping(value = "/updateFAScrapResult", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody
    Object updateFAScrapResult(String faResult, String assetCode, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        map.put("faResult",faResult);
        map.put(MapKey.ASSET_CODE,assetCode);
        try{
            int u = GetService.getInstance().getMybatisScrapDisposeService().updateFAScrapResult(map);
            if(u>0){
                ResponseUtils.writeSuccessResponse(response,assetCode);
            }else{
                ResponseUtils.writeErrorResponse(response,"Update FA result fail");
            }
        } catch(Exception e){
            ResponseUtils.writeErrorResponse(response,e.getMessage());
        }

        return null;
    }




}
