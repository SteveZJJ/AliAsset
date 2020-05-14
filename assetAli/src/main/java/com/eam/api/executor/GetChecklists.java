package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月19日 下午12:36:36
 * @version 1.0
 */

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.R5ActChecklists;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;

@SuppressWarnings(value = {"rawtypes"})
public class GetChecklists implements Callable {

    HttpServletResponse response;
    HashMap<String, String> map = new HashMap <>();

    public GetChecklists(String orgCode, String objCode, String evtCode, String language,
                         HttpServletResponse response2) {
        this.response = response2;
        map.put(MapKey.ORG_CODE,orgCode);
        map.put(MapKey.OBJ_CODE, objCode);
        map.put(MapKey.EVT_CODE, evtCode);
        map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
    }

    @Override
    public Object call(){
        List<R5ActChecklists> checklists = GetService.getInstance().getMybatisReceiptService().getChecklists(map);
//		List<R5ActChecklists> checklists = new ArrayList<R5ActChecklists>();
//		checklists.add(new R5ActChecklists("serialNo1", "description1", "属性列1", "+", "01", ""));
//		checklists.add(new R5ActChecklists("serialNo2", "description2", "属性列2", "+", "05", ""));
//		checklists.add(new R5ActChecklists("serialNo3", "description3", "属性列3", "+", "06", "GENERAL"));
        ResponseUtils.writeResponse(response,
                JsonUtils.getJsonFromObject(new AliResponseData(Constants.INTERFACE_SUCCESS, null, checklists)));
        return null;
    }

}
