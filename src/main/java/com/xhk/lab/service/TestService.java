package com.xhk.lab.service;

import com.alibaba.fastjson.JSONObject;
import com.xhk.lab.dao.TestDao;
import com.xhk.lab.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by xhk on 18/3/4
 */
@Service("testService")
public class TestService {

    @Autowired
    private TestDao testDao;

    public void test(){
        Test test = testDao.getEnteryById(1);
        System.out.println(JSONObject.toJSONString(test));
    }
}
