package com.eam.mybatis.model;

public class AliResultProcedure {
    private String flag;
    private String message;
    private String code;
    private String param1;
    private String param2;
    private String param3;
    private String param4;
    private String param5;
    private String param6;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam5() {
        return param5;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }

    public String getParam6() {
        return param6;
    }

    public void setParam6(String param6) {
        this.param6 = param6;
    }
    
    @Override
    public String toString() {
        return "AliResultProcedure{" +
                       "flag='" + flag + '\'' +
                       ", message='" + message + '\'' +
                       ", code='" + code + '\'' +
                       ", param1='" + param1 + '\'' +
                       ", param2='" + param2 + '\'' +
                       ", param3='" + param3 + '\'' +
                       ", param4='" + param4 + '\'' +
                       ", param5='" + param5 + '\'' +
                       ", param6='" + param6 + '\'' +
                       '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
