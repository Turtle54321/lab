package com.xhk.lab.service;

import com.xhk.lab.dao.NewsDao;
import com.xhk.lab.model.News;
import com.xhk.lab.rmodel.*;
import com.xhk.lab.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by xhk on 2018/4/15
 */
@Service
public class NewsManageService {
    private static Logger logger = LoggerFactory.getLogger(NewsManageService.class);

    @Autowired
    private NewsDao newsDao;

    public NewsGetResponse getNewsList(NewsGetRequest request){
        int totalNum = newsDao.countNum(request.getType());
        int start = (request.getWhichPage()-1) * request.getPerCount();
        List<News> newsList = newsDao.getEntityListByPage(request.getType(),start,request.getPerCount());
        NewsGetResponse response = new NewsGetResponse();
        response.setNewsList(newsList);
        response.setTotalNum(totalNum);
        return response;
    }

    public NewsGetByIdResponse getNewsById(NewsGetByIdRequest request){
        News news = newsDao.getEntityById(request.getNewsId());
        NewsGetByIdResponse response = new NewsGetByIdResponse();
        response.setNews(news);
        return response;
    }

    public void addNews(NewsAddRequest request){
        News news = new News();
        news.setTitle(request.getTitle());
        news.setEtitle(request.getEtitle());
        news.setType(request.getType());
        news.setCreateTime(DateUtil.getCurrentTime());
        news.setUpdateTime(DateUtil.getCurrentTime());
        if (StringUtils.isBlank(request.getUrl())){
            news.setContent(request.getContent());
            news.setEcontent(request.getEcontent());
        }
        else{
            //直接引用新闻
            news.setUrl(request.getUrl());
        }
        newsDao.insert(news);
    }

    public void deleteNews(NewsDeleteRequest request){
        newsDao.delete(request.getNewsId());
    }

    public void updateNews(NewsUpdateRequest request){
        News news = new News();
        news.setId(request.getNewsId());
        news.setTitle(request.getTitle());
        news.setEtitle(request.getEtitle());
        news.setType(request.getType());
        news.setUpdateTime(DateUtil.getCurrentTime());
        if (StringUtils.isBlank(request.getUrl())){
            news.setContent(request.getContent());
            news.setEcontent(request.getEcontent());
        }
        else{
            //直接引用新闻
            news.setUrl(request.getUrl());
        }
        newsDao.updateEntity(news);
    }
}
