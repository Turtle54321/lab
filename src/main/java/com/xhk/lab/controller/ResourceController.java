package com.xhk.lab.controller;

import com.xhk.lab.common.aspect.RetFormat;
import com.xhk.lab.rmodel.IndexImgGetResponse;
import com.xhk.lab.rmodel.ResourceGetRequest;
import com.xhk.lab.rmodel.ResourceGetResponse;
import com.xhk.lab.service.IndexService;
import com.xhk.lab.service.ResourceService;
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
public class ResourceController {
    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private IndexService indexService;

    @RequestMapping("resource")
    @RetFormat(isPage = true)
    public String resource(Integer whichPage, Integer perCount, Integer language, ModelMap modelMap){
        whichPage = whichPage == null ? 1 : whichPage;
        perCount = perCount == null ? 10 : perCount;
        language = language == null ? 1 : language;
        ResourceGetRequest request = new ResourceGetRequest();
        request.setPerCount(perCount);
        request.setWhichPage(whichPage);
        ResourceGetResponse response = resourceService.getResource(request);
        IndexImgGetResponse responseImg = indexService.getIndexImg();
        modelMap.addAttribute("indexImgList",responseImg.getImgUrlList());
        modelMap.addAttribute("language",language);
        modelMap.addAttribute("whichPage",whichPage);
        modelMap.addAttribute("perCount",perCount);
        modelMap.addAttribute("list",response.getResourceList());
        modelMap.addAttribute("allCount", response.getTotalNum());
        return "page/resource";
    }
}
