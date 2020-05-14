package com.eam.api.executor;

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.model.ReceiptDetailsManual;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class NewReceiptDetailsManual implements Callable<Object> {

    private ReceiptDetailsManual detail;

    HttpServletResponse response;

    public NewReceiptDetailsManual(ReceiptDetailsManual detail, HttpServletResponse response) {
        this.detail = detail;
        this.response = response;
    }

    @Override
    public Object call() {

        System.out.println(detail.toString());
        AliResultProcedure resp = GetService.getInstance().getMybatisReceiptService().newReceiptDetailsManual(detail);

        System.out.println(resp.toString());
        HashMap<String, String> map = new HashMap<>();
        if (Constants.INTERFACE_SUCCESS.equals(resp.getFlag())) {
            map.put(MapKey.RECEIPT_NO, resp.getParam1());
            map.put(MapKey.CORP, resp.getParam2());
        }
        ResponseUtils.writeResponse(response,
                JsonUtils.getJsonFromObject(new AliResponseData(resp.getFlag(), resp.getMessage(), map)));

        return null;
    }
}