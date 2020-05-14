package com.eam.mybatis.model;
/*
 * @author  Steve Zhang
 * @date 创建时间：2019年1月25日19:12:32
 * @version 1.0
 */

import lombok.Data;

import java.util.List;

@Data
public class R5PropertyValuesContent {

    private List<R5PropertyValues> propertyValuesList;
    private String receiptNo;
    private String corp;
    private String language;

}
