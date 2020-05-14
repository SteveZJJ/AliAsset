package com.eam.mybatis.model;

import lombok.Data;

/**
 * @author Steve Zhang
 * @Date 2019年6月21日17:26:13
 * @Description 转移、调拨及转让的资产行汇总信息
 */

@Data
public class LineSummary {

    private String quantity;
    private String featureDesc;
    private String partCode;
    private String partDesc;
}
