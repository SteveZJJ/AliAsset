package com.eam.context;

/***
 * @Author JasonZhao
 * @Description  //定义客户化值列表字段
 * @Date 2018/8/20 下午8:20
 * @Param
 * @Return
 **/

public enum UserDefinedField {
	/**
	 * IntialType --获取新增类型
	 * OwnerShip ---获取所有权
	 * Usage ---用途
	 * AssetSource  ---资产来源
	 **/
	IntialType("udfchar04","OBJ"),OTHERS("NULL","NULL"),OwnerShip("udfchar08","OBJ"),Usage("udfchar07","OBJ"),Usage_EVT("udfchar07","EVNT"),AssetSource("udfchar22","EVNT");
	private String entity;
	private String field;

	UserDefinedField(String field,String entity){
		this.entity = entity;
		this.field = field;
	}

	public String getEntity(){
		return entity;
	}

	public String getField(){
		return field;
	}
}
