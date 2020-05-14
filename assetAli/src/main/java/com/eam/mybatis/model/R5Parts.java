package com.eam.mybatis.model;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月18日 下午7:44:15
 * @version 1.0
 */

public class R5Parts {
    private String partCode;
    private String partDesc;

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public R5Parts(String partCode, String partDesc) {
        this.partCode = partCode;
        this.partDesc = partDesc;
    }
}
