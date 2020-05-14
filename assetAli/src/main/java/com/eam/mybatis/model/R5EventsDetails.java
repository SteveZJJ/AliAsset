package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

/**************************************
 *@ClassName R5EventsDetails
 *@Description   //单据详情
 *@Author jason
 *@Date 2018/9/12 - 16:29
 *@Version 1.0
 **************************************/
@Data
public class R5EventsDetails {
	private String partCode;//物料ID
	private String partDesc;//物料名称
	private String planQty;//申请数量
	private String actQty;//发放数量
	private List <ReceiptItem> assets;
}
