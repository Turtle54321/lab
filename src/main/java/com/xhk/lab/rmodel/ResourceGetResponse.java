package com.xhk.lab.rmodel;

import com.xhk.lab.model.Resource;

import java.util.List;

/**
 * create by xhk on 2018/4/26
 */
public class ResourceGetResponse {
    private List<Resource> resourceList;
    private Integer totalNum;

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
