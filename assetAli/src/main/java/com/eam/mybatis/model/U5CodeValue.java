package com.eam.mybatis.model;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2017年2月4日 下午2:39:37
 * @version 1.0
 */

public class U5CodeValue {

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "U5CodeValue [code=" + code + ", value=" + value + "]";
    }


    public boolean equalsCode(String vcode){

        return vcode.equals(this.code);
    }
}
