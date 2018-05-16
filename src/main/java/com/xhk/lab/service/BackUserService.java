package com.xhk.lab.service;

import com.xhk.lab.common.ProjectException;
import com.xhk.lab.common.constant.ErrorCodeMap;
import com.xhk.lab.dao.ManagerDao;
import com.xhk.lab.model.Manager;
import com.xhk.lab.rmodel.CheckLoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by xhk on 2018/3/28
 */
@Service
public class BackUserService {
    private static Logger logger = LoggerFactory.getLogger(BackUserService.class);

    @Autowired
    private ManagerDao managerDao;

    public String checkLogin(CheckLoginRequest request){
        Manager manager = managerDao.checkLogin(request.getUsername(),request.getPassword());
        if (manager == null){
            throw new ProjectException(ErrorCodeMap.LOGIN_ERROR);
        }
        return manager.getName();
    }
}
