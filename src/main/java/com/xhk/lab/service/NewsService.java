package com.xhk.lab.service;

import com.xhk.lab.dao.NewsDao;
import com.xhk.lab.model.News;
import com.xhk.lab.rmodel.NewsDetailGetRequest;
import com.xhk.lab.rmodel.NewsDetailGetResponse;
import com.xhk.lab.rmodel.NewsGetRequest;
import com.xhk.lab.rmodel.NewsGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by xhk on 2018/4/30
 */
@Service
public class NewsService {
    private static Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    private NewsDao newsDao;
    public NewsGetResponse getNews(NewsGetRequest request){
        Integer start = (request.getWhichPage()-1)*request.getPerCount();
        Integer totalNum = newsDao.countNum(request.getType());
        List<News> newsList = newsDao.getEntityListByPage(request.getType(),start,request.getPerCount());
        NewsGetResponse response = new NewsGetResponse();
        response.setNewsList(newsList);
        response.setTotalNum(totalNum);
        return response;
    }

    public NewsDetailGetResponse getNewsDetail(NewsDetailGetRequest request){
        News news = newsDao.getEntityById(request.getNewsId());
        NewsDetailGetResponse response = new NewsDetailGetResponse();
        response.setNews(news);
        return response;
    }
}
