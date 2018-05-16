package com.xhk.lab.controller;

import com.xhk.lab.common.ProjectException;
import com.xhk.lab.common.aspect.RetFormat;
import com.xhk.lab.common.constant.ErrorCodeMap;
import com.xhk.lab.model.Introduction;
import com.xhk.lab.rmodel.IntroductionChangeRequest;
import com.xhk.lab.rmodel.IntroductionGetResponse;
import com.xhk.lab.service.IntroductionManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 实验室介绍管理
 * create by xhk on 2018/4/13
 */
@Controller
public class IntroductionManageController {
    private static Logger logger = LoggerFactory.getLogger(IntroductionManageController.class);

    @Autowired
    private IntroductionManageService introductionManageService;

    @RequestMapping("introduction-manage")
    @RetFormat(isPage = true)
    public String manageIntroduction(ModelMap modelMap){
        IntroductionGetResponse response = introductionManageService.getIntroduction();
        modelMap.addAttribute("introduction",response.getIntroduction());
        return "page/introduction-manage";
    }

    @RequestMapping("introduction-change")
    @ResponseBody
    @RetFormat
    public Object changeIntroduction(Introduction introduction){
        if (introduction == null){
            throw new ProjectException(ErrorCodeMap.PARAMETER_EMPTY_ERROR);
        }
        logger.info(introduction.getContent());
        IntroductionChangeRequest request = new IntroductionChangeRequest();
        request.setIntroduction(introduction);
        introductionManageService.changeIntroduction(request);
        return "";
    }

}
