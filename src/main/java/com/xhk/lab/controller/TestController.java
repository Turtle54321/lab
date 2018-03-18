package com.xhk.lab.controller;

import com.xhk.lab.common.aspect.JsonParameterVerify;
import com.xhk.lab.common.aspect.RetFormat;
import com.xhk.lab.rmodel.TestRequest;
import com.xhk.lab.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by xhk on 18/3/4
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("test.do")
    @RetFormat(isPage = true)
    public String test(TestRequest request, ModelMap modelMap){
        System.out.println(request.getId());
//        testService.test();
        modelMap.put("message","成功");
        return "success";
    }

    @RequestMapping("jsonTest.do")
    @ResponseBody
    @JsonParameterVerify
    public String jsonTest(TestRequest request){
        System.out.println(request.getId());
        return "lalala";
    }
}
