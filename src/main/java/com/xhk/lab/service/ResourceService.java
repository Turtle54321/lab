package com.xhk.lab.service;

import com.xhk.lab.common.constant.ProjectConstant;
import com.xhk.lab.dao.ResourceDao;
import com.xhk.lab.model.Resource;
import com.xhk.lab.rmodel.ResourceListGetRequest;
import com.xhk.lab.rmodel.ResourceListGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by xhk on 2018/4/30
 */
@Service
public class ResourceService {
    private static Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @Autowired
    private ResourceDao resourceDao;

    public ResourceListGetResponse getResource(ResourceListGetRequest request){
        Integer totalNum = resourceDao.countNum();
        Integer start = (request.getWhichPage()-1)*request.getPerCount();
        List<Resource> resourceList = resourceDao.getEntityListByPage(start, request.getPerCount());
        for (int i = 0; i < resourceList.size(); i++){
            resourceList.get(i).setUrl(ProjectConstant.FILE_DIR+"/"+resourceList.get(i).getUrl());
        }
        ResourceListGetResponse response = new ResourceListGetResponse();
        response.setResourceList(resourceList);
        response.setTotalNum(totalNum);
        return response;
    }
}
