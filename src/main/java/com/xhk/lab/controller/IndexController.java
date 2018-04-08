package com.xhk.lab.controller;

import com.xhk.lab.common.aspect.RetFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by xhk on 2018/3/26
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    @RetFormat(isPage = true)
    public String index(){
        return "page/index";
    }
}
