package com.eam.api.executor;

import com.eam.context.Constants;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.model.Manual_CreateHeader;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * @author Steve Zhang
 * @Time    2018-8-22 21:12:11
 * @version 1.0
 */
public class CreateAssetReceiptManual_New implements Callable {

    Manual_CreateHeader header;
    HttpServletResponse response;

    public CreateAssetReceiptManual_New(Manual_CreateHeader header, HttpServletResponse response) {
        this.header = header;
        this.response = response;
    }


    @Override
    public Object call() throws Exception {
        AliResultProcedure resp =  GetService.getInstance().getMybatisReceiptService().createAssetReceiptManual_New(header);
        HashMap<String, String> map = new HashMap<String, String>();
        if (Constants.INTERFACE_SUCCESS.equals(resp.getFlag())) {
            map.put("receiptNo", resp.getParam1());
            map.put("corp", resp.getParam2());
        }
        ResponseUtils.writeResponse(response,
                JsonUtils.getJsonFromObject(new AliResponseData(resp.getFlag(), resp.getMessage(), map)));
        return null;
    }
}
