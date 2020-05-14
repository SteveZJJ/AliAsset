package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName PickupLine
 *@Description 入职领用单行日期
 *@Author jason
 *@Date 2018/8/23 上午10:52
 *@Version 1.0
 **************************************/
@Data
public class PickupLine {
	private String reqLine;//借用、归还  行号
	private String specification;//借用 配置信息
	private String normalModel;//借用   日常型号
	private String assetCategory;//借用 资产类目
	private String qty;//借用数量  MLP_QTY
	private String duration;//借用时长
	private String assetCode;//归还、续借 填写大阿里编号
}
