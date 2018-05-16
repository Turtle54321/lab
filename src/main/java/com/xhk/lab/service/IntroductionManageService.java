package com.xhk.lab.service;

import com.xhk.lab.dao.IntroductionDao;
import com.xhk.lab.model.Introduction;
import com.xhk.lab.rmodel.IntroductionChangeRequest;
import com.xhk.lab.rmodel.IntroductionGetResponse;
import com.xhk.lab.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by xhk on 2018/4/13
 */
@Service
public class IntroductionManageService {
    private Logger logger = LoggerFactory.getLogger(IndexManageService.class);

    @Autowired
    private IntroductionDao introductionDao;

    public IntroductionGetResponse getIntroduction() {
        Introduction introduction = introductionDao.getEntity();
        IntroductionGetResponse response = new IntroductionGetResponse();
        response.setIntroduction(introduction);
        return response;
    }

    public void changeIntroduction(IntroductionChangeRequest request){
        request.getIntroduction().setUpdateTime(DateUtil.getCurrentTime());
        if (request.getIntroduction().getId() == null){
            //插入
            request.getIntroduction().setCreateTime(DateUtil.getCurrentTime());
            introductionDao.insert(request.getIntroduction());
        }
        else {
            introductionDao.updateEntity(request.getIntroduction());
        }
    }
}
