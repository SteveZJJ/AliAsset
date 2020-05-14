package com.eam.mybatis.model;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月18日 下午5:48:16
 * @version 1.0
 */

public class AliResponseData extends AliResponse {
    private Object data; // 数据信息


    public AliResponseData(String flag, String message, Object data) {
        super(flag, message);
        this.data = data;
    }

    public AliResponseData(String flag, String message, String code, Object data) {
        super(flag, message, code);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "AliResponseData [data=" + data + "]";
    }

}
