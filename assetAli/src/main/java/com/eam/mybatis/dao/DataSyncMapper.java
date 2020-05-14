package com.eam.mybatis.dao;

import com.eam.mybatis.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataSyncMapper {

	List<U5MyAsset> getAssetsListByCodeList(Map<String, Object> map);

	String createPersonInfo(R5Personnel person);

	int newPersonInfo(Map<String,Object> map);

	String createDepartmentInfo(R5Mrcs department);

	int newDepartmentInfo(Map<String, Object> map);
}
