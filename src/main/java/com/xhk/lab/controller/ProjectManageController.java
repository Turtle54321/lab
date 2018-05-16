package com.xhk.lab.controller;

import com.xhk.lab.common.aspect.RetFormat;
import com.xhk.lab.model.News;
import com.xhk.lab.rmodel.*;
import com.xhk.lab.service.NewsManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by xhk on 2018/4/15
 */
@Controller
public class ProjectManageController {
    private static Logger logger = LoggerFactory.getLogger(ProjectManageController.class);

    @Autowired
    private NewsManageService newsManageService;

    /**
     * 进入新闻管理页面，翻页
     * @param whichPage
     * @param perCount
     * @param modelMap
     * @return
     */
    @RequestMapping("project-manage")
    @RetFormat(isPage = true)
    public String newsManage(Integer whichPage, Integer perCount, ModelMap modelMap){
        whichPage = whichPage == null ? 1 : whichPage;
        perCount = perCount == null ? 10 : perCount;
        NewsGetRequest request = new NewsGetRequest();
        request.setPerCount(perCount);
        request.setWhichPage(whichPage);
        request.setType(News.PROJECT_TPYE);
        NewsGetResponse response = newsManageService.getNewsList(request);
        modelMap.addAttribute("whichPage",whichPage);
        modelMap.addAttribute("perCount",perCount);
        modelMap.addAttribute("allCount",response.getTotalNum());
        modelMap.addAttribute("list",response.getNewsList());
        return "page/project-manage";
    }

    @RequestMapping("project-change-page")
    @RetFormat(isPage = true)
    public String newsChange(Integer newsId, ModelMap modelMap){
        if (newsId != null){
            //修改新闻
            NewsGetByIdRequest request = new NewsGetByIdRequest();
            request.setNewsId(newsId);
            NewsGetByIdResponse response = newsManageService.getNewsById(request);
            modelMap.addAttribute("news",response.getNews());
        }
        //为空新增新闻
        return "page/project-change";
    }

}
