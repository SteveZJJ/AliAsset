
/*
 * MybatisService.java
 *
 */
package com.eam.mybatis.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.mybatis.dao.DemoMapper;
import com.eam.mybatis.model.U5CodeValue;

/**
 * For db accessor. To store the EHR data to EAM database.
 *
 * @author
 */
@Service
public class MybatisDemoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DemoMapper demoService = null;

    public List<U5CodeValue> getDemoList() {
        logger.info("Here is My queryDemoSql");
        return demoService.getDemoList();
    }

    public List<U5CodeValue> getDemoUsers(HashMap<String, String> map) {
        return demoService.getDemoUsers(map);
    }

    public int getDemoUsersCount(HashMap<String, String> map) {
        return demoService.getDemoUsersCount(map);
    }


}
