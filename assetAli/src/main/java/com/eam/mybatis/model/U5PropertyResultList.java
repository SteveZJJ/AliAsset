package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

/*
 * @author  Steve Zhang
 * @date 创建时间：2019年1月31日11:57:41
 * @version 1.0
 */
@Data
public class U5PropertyResultList {
	private List<U5PropertyResult> propertyResultList;
	private String receiptNo;
}
