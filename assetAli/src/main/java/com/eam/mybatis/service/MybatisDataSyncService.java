package com.eam.mybatis.service;

import com.eam.context.Constants;
import com.eam.context.EventDefault;
import com.eam.context.ExceptionDetails;
import com.eam.context.MapKey;
import com.eam.mybatis.dao.DataSyncMapper;
import com.eam.mybatis.dao.QueryCodeDescMapper;
import com.eam.mybatis.model.*;
import com.eam.utils.AliEamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

/**************************************
 *@ClassName MybatisNewAssetReceiptService
 *@Description 资产手动新增接口Service
 *@Author jason
 *@Date 2018/8/20 下午7:08
 *@Version 1.0
 **************************************/
@Service
public class MybatisDataSyncService {
	
	@Autowired
	DataSyncMapper dataSyncService = null;
	
	


//	public List<U5MyAsset> getAssetsListByCodeList(Map<String, Object> map) {
//
//		return dataSyncService.getAssetsListByCodeList(map);
//
//	}


	public String createPersonInfo(R5Personnel person) {
		return dataSyncService.createPersonInfo(person);
	}

	public int newPersonInfo(Map<String,Object> map) {
		return dataSyncService.newPersonInfo(map);
	}

	public String createDepartmentInfo(R5Mrcs department) {
			return dataSyncService.createDepartmentInfo(department);
	}

	public int newDepartmentInfo(Map<String, Object> map) {
		return dataSyncService.newDepartmentInfo(map);
	}
}
