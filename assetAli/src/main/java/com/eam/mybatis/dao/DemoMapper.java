package com.eam.mybatis.dao;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月4日 下午5:47:51
 * @version 1.0
 */

import java.util.HashMap;
import java.util.List;

import com.eam.mybatis.model.U5CodeValue;

public interface DemoMapper {

    List<U5CodeValue> getDemoList();

    List<U5CodeValue> getDemoUsers(HashMap<String, String> map);

    int getDemoUsersCount(HashMap<String, String> map);

}
