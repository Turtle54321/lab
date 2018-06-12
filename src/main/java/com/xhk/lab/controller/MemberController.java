package com.xhk.lab.controller;

import com.xhk.lab.common.ProjectException;
import com.xhk.lab.common.aspect.RetFormat;
import com.xhk.lab.common.constant.ErrorCodeMap;
import com.xhk.lab.rmodel.*;
import com.xhk.lab.service.IndexService;
import com.xhk.lab.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by xhk on 2018/4/30
 */
@Controller
public class MemberController {
    private static Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private IndexService indexService;

    /**
     * 获取所有老师，在读硕士、博士，已毕业硕士、博士
     * @param language
     * @param modelMap
     * @return
     */
    @RequestMapping("member")
    @RetFormat(isPage = true)
    public String member(Integer language, ModelMap modelMap){
        language = language == null ? 1 : language;
        AllMemberGetResponse response = memberService.getMember();
        IndexImgGetResponse responseImg = indexService.getIndexImg();
        modelMap.addAttribute("indexImgList",responseImg.getImgUrlList());
        modelMap.addAttribute("teacherList",response.getTeacherList());
        modelMap.addAttribute("doctorList",response.getDoctorList());
        modelMap.addAttribute("masterList",response.getMasterList());
        modelMap.addAttribute("graduateDoctorList",response.getGraduateDoctorList());
        modelMap.addAttribute("graduateMasterList",response.getGraduateMasterList());

        modelMap.addAttribute("language",language);
        return "page/member";
    }

//    @RequestMapping("member-detail")
//    @RetFormat(isPage = true)
//    public String memberDetail(Integer memberId, Integer language, ModelMap modelMap){
//        if(memberId == null){
//            throw new ProjectException(ErrorCodeMap.PARAMETER_ERROR);
//        }
//        language = language == null ? 1 : language;
//        MemberDetailGetRequest request = new MemberDetailGetRequest();
//        request.setMemberId(memberId);
//        MemberDetailGetResponse response = memberService.getMemberDetail(request);
//        modelMap.addAttribute("member",response.getMember());
//        modelMap.addAttribute("language",language);
//        return "page/member-detail";
//    }

    @RequestMapping("{urlName}/biography")
    @RetFormat(isPage = true)
    public String teacherDetail(Integer language, @PathVariable String urlName, ModelMap modelMap){

        if (StringUtils.isBlank(urlName)){
            throw new ProjectException(ErrorCodeMap.URL_ERROR);
        }
        language = language == null ? 1 : language;
        PeopleDetailGetRequest request = new PeopleDetailGetRequest();
        request.setUrlName(urlName);
        PeopleDetailGetResponse response = memberService.getPeopleDetail(request);
        modelMap.addAttribute("member",response.getMember());
        modelMap.addAttribute("language",language);
        return "page/biography";
    }
}
