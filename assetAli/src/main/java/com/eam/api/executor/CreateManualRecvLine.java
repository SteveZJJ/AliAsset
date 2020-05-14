package com.eam.api.executor;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.model.Manual_CreateHeader;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * @author Steve Zhang
 * @Time    2018-8-22 21:12:11
 * @version 1.0
 */
public class CreateManualRecvLine implements Callable {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    HashMap <String, String> map = new HashMap <>();
    HttpServletResponse response;

    public CreateManualRecvLine(String receiptNo, String language, HttpServletResponse response) {
        map.put(MapKey.RECEIPT_NO,receiptNo);
        map.put(MapKey.LANGUAGE,language);
        this.response = response;
    }

    @Override
    public Object call() throws Exception {
        AliResultProcedure resp =  GetService.getInstance().getMybatisReceiptService().createManualRecvLine(map);
        ResponseUtils.writeResponse(response,
                JsonUtils.getJsonFromObject(new AliResponseData(resp.getFlag(), resp.getMessage(), map)));
        return null;
    }
}
