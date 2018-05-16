package com.xhk.lab.service;

import com.xhk.lab.common.ProjectException;
import com.xhk.lab.common.constant.ErrorCodeMap;
import com.xhk.lab.common.constant.ProjectConstant;
import com.xhk.lab.dao.ResourceDao;
import com.xhk.lab.model.Resource;
import com.xhk.lab.rmodel.*;
import com.xhk.lab.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by xhk on 2018/4/26
 */
@Service
public class ResourceManageService {
    private static Logger logger = LoggerFactory.getLogger(ResourceManageService.class);

    @Autowired
    private ResourceDao resourceDao;

    public void changeResource(ResourceChangeRequest request){
        Resource resource = request.getResource();

        resource.setUpdateTime(DateUtil.getCurrentTime());
        if (resource.getId() == null){
            // 新增
            resource.setCreateTime(DateUtil.getCurrentTime());
            int num = resourceDao.insert(resource);
            if (num == 0){
                throw new ProjectException(ErrorCodeMap.FAIL_TO_ADD);
            }
        }
        else{
            int num = resourceDao.updateEntity(resource);
            if (num == 0){
                throw new ProjectException(ErrorCodeMap.FAIL_TO_UPDATE);
            }
        }
    }

    public void deleteResource(ResourceDeleteRequest request){
        resourceDao.delete(request.getResourceId());
    }

    public ResourceGetResponse getResourceList(ResourceGetRequest request){
        int totalNum = resourceDao.countNum();
        int start = (request.getWhichPage()-1) * request.getPerCount();
        List<Resource> resourceList = resourceDao.getEntityListByPage(start,request.getPerCount());
        for (int i = 0; i < resourceList.size(); i++){
            resourceList.get(i).setUrl(ProjectConstant.FILE_DIR+"/"+resourceList.get(i).getUrl());
        }
        ResourceGetResponse response = new ResourceGetResponse();
        response.setResourceList(resourceList);
        response.setTotalNum(totalNum);
        return  response;
    }

    public ResourceDetailGetResponse getResourceDetail(ResourceDetailGetRequest request){
        Resource resource = resourceDao.getEntityById(request.getResourceId());
        ResourceDetailGetResponse response = new ResourceDetailGetResponse();
        response.setResource(resource);
        return response;
    }
}
