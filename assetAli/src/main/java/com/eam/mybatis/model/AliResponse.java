package com.eam.mybatis.model;

public class AliResponse {
    private String flag;// 成功 S  失败  E
    private String code;//状态标识码
    private String message; // 消息

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public AliResponse(String flag, String message, String code) {
        this.flag = flag;
        this.message = message;
        this.code = code;
    }
    public AliResponse(String flag, String message) {
        this.flag = flag;
        this.message = message;
        this.code = null;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AliResult [flag=" + flag + ", message=" + message + "]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
