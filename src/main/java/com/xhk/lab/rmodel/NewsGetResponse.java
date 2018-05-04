package com.xhk.lab.rmodel;

import com.xhk.lab.model.News;

import java.util.List;

/**
 * create by xhk on 2018/4/30
 */
public class NewsGetResponse {
    private List<News> newsList;
    private Integer totalNum;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
