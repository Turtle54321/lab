package com.xhk.lab.service;

import com.xhk.lab.common.ProjectException;
import com.xhk.lab.common.constant.ErrorCodeMap;
import com.xhk.lab.common.constant.ProjectConstant;
import com.xhk.lab.dao.MemberDao;
import com.xhk.lab.model.Member;
import com.xhk.lab.rmodel.*;
import com.xhk.lab.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * create by xhk on 2018/4/15
 */
@Service
public class MemberManageService {
    private static Logger logger = LoggerFactory.getLogger(MemberManageService.class);

    @Autowired
    private MemberDao memberDao;

    public MemberGetResponse getMemberList(MemberGetRequest request){
        int totalNum = memberDao.countNum(request.getStatus());
        int start = (request.getWhichPage()-1) * request.getPerCount();
        List<Member> memberList = memberDao.getEntityListByPageStatus(request.getStatus(),start,request.getPerCount());
        for (int i = 0; i < memberList.size(); i++){
            memberList.get(i).setHeadUrl(ProjectConstant.MEMBER_IMG_DIR + "/" + memberList.get(i).getHeadUrl());
            if (StringUtils.isBlank(memberList.get(i).getContent())){
                memberList.get(i).setContentUrl(null);
            }
        }
        MemberGetResponse response = new MemberGetResponse();
        response.setMemberList(memberList);
        response.setTotalNum(totalNum);
        return  response;
    }


    public void deteleMember(MemberDeleteRequest request){
        memberDao.delete(request.getMemberId());
    }

    public void changeMember(MemberChangeRequest request){
        // 确认urlName无重复
        List<Member> memberList = memberDao.getEntityListByUrlName(request.getMember().getUrlName());
        if (!CollectionUtils.isEmpty(memberList)){
            if (memberList.size()==1&&memberList.get(0).getId()==request.getMember().getId()){
                //修改自己的信息
            }
            else{
                // urlName重复
                throw new ProjectException(ErrorCodeMap.URL_NAME_REPEAT);
            }
        }
        Member member  = request.getMember();
        member.setUpdateTime(DateUtil.getCurrentTime());

        if (member.getId() == null){
            // 新增
            member.setCreateTime(DateUtil.getCurrentTime());
            int num = memberDao.addEntity(member);
            if (num == 0){
                throw new ProjectException(ErrorCodeMap.FAIL_TO_ADD);
            }
        }
        else{
            // 更新
            int num = memberDao.updateEntity(member);
            if (num == 0){
                throw new ProjectException(ErrorCodeMap.FAIL_TO_UPDATE);
            }
        }

    }

    public MemberDetailGetResponse getMemberDetail(MemberDetailGetRequest request){
        Member member = memberDao.getEntityById(request.getMemberId());
        member.setHeadUrl(ProjectConstant.MEMBER_IMG_DIR+"/"+member.getHeadUrl());
        MemberDetailGetResponse response = new MemberDetailGetResponse();
        response.setMember(member);
        return response;
    }

}
