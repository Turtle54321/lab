package com.xhk.lab.controller;

import com.xhk.lab.common.aspect.RetFormat;
import com.xhk.lab.rmodel.IndexDisplayResponse;
import com.xhk.lab.rmodel.IndexImgGetResponse;
import com.xhk.lab.service.IndexService;
import com.xhk.lab.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by xhk on 2018/3/26
 */
@Controller
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexService indexService;
    @RequestMapping("/index")
    @RetFormat(isPage = true)
    public String index(Integer language, ModelMap modelMap){
        language = language == null ? 1 : language;
        // 首页展示轮播图片、简介、项目、人员头像、最近新闻
        IndexDisplayResponse response = indexService.indexDisplay();
        IndexImgGetResponse responseImg = indexService.getIndexImg();
        modelMap.addAttribute("indexImgList",responseImg.getImgUrlList());
        modelMap.addAttribute("introduction",response.getIntroduction());
        modelMap.addAttribute("newsList",response.getNewsList());
        modelMap.addAttribute("memberList",response.getMemberList());
        modelMap.addAttribute("projectList",response.getProjectList());
        modelMap.addAttribute("language",language);
        return "page/index";
    }
}
