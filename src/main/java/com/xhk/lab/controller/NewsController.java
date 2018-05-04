package com.xhk.lab.controller;

import com.xhk.lab.common.ProjectException;
import com.xhk.lab.common.aspect.RetFormat;
import com.xhk.lab.common.constant.ErrorCodeMap;
import com.xhk.lab.model.News;
import com.xhk.lab.rmodel.*;
import com.xhk.lab.service.IndexService;
import com.xhk.lab.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by xhk on 2018/4/30
 */
@Controller
public class NewsController {
    private static Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @Autowired
    private IndexService indexService;


    @RequestMapping("news")
    @RetFormat(isPage = true)
    public String news(Integer whichPage, Integer perCount, Integer language, Integer type, ModelMap modelMap){
        whichPage = whichPage == null ? 1 : whichPage;
        perCount = perCount == null ? 10 : perCount;
        language = language == null ? 1 : language;
        if (!News.typeConfirm(type)){
            logger.error("type is wrong, type : "+type);
            throw new ProjectException(ErrorCodeMap.PARAMETER_ERROR);
        }
        NewsGetRequest request = new NewsGetRequest();
        request.setPerCount(perCount);
        request.setType(type);
        request.setWhichPage(whichPage);
        NewsGetResponse response = newsService.getNews(request);
        IndexImgGetResponse responseImg = indexService.getIndexImg();
        modelMap.addAttribute("indexImgList",responseImg.getImgUrlList());
        modelMap.addAttribute("language",language);
        modelMap.addAttribute("type",type);
        modelMap.addAttribute("whichPage",whichPage);
        modelMap.addAttribute("perCount",perCount);
        modelMap.addAttribute("list",response.getNewsList());
        modelMap.addAttribute("allCount", response.getTotalNum());
        return "page/news";

    }

    @RequestMapping("news-detail")
    @RetFormat(isPage = true)
    public String newsDetail(Integer newsId, Integer language, ModelMap modelMap){
        if(newsId == null){
            throw new ProjectException(ErrorCodeMap.PARAMETER_ERROR);
        }
        language = language == null ? 1 : language;
        NewsDetailGetRequest request = new NewsDetailGetRequest();
        request.setNewsId(newsId);
        NewsDetailGetResponse response = newsService.getNewsDetail(request);
        modelMap.addAttribute("news",response.getNews());
        modelMap.addAttribute("language",language);
        return "page/news-detail";
    }
}
