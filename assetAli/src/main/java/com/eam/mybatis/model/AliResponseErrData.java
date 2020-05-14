package com.eam.mybatis.model;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月18日 下午5:48:16
 * @version 1.0
 */

public class AliResponseErrData extends AliResponseData {

    private Object errData; //报错数据返回


    public AliResponseErrData(String flag, String message, Object data, Object errData) {
        super(flag,message,data);
        this.errData = errData;
    }

    public AliResponseErrData(String flag, String message, String code, Object data, Object errData) {
        super(flag, message, code,data);
        this.errData = errData;
    }


    public Object getErrData() {
        return errData;
    }

    public void setErrData(Object errData) {
        this.errData = errData;
    }

    @Override
    public String toString() {
        return "AliResponseErrData [Flag = "+ getFlag() +" Error data=" + errData + "]";
    }

}
