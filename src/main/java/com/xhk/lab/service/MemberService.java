package com.xhk.lab.service;

import com.xhk.lab.common.ProjectException;
import com.xhk.lab.common.constant.ErrorCodeMap;
import com.xhk.lab.common.constant.ProjectConstant;
import com.xhk.lab.dao.MemberDao;
import com.xhk.lab.model.Member;
import com.xhk.lab.rmodel.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * create by xhk on 2018/4/30
 */
@Service
public class MemberService {
    private static Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberDao memberDao;

    public AllMemberGetResponse getMember(){
        List<Member> memberList = memberDao.getEntityList();
        List<Member> teacherList = new ArrayList<>();
        List<Member> doctorList = new ArrayList<>();
        List<Member> masterList = new ArrayList<>();
        List<Member> graduateDoctorList = new ArrayList<>();
        List<Member> graduateMasterList = new ArrayList<>();
        for (Member member : memberList){
            if (StringUtils.isBlank(member.getContent())){
                member.setContentUrl(null);
            }
            member.setHeadUrl(ProjectConstant.MEMBER_IMG_DIR+"/"+member.getHeadUrl());
            if (Member.TEACHER_STATUS == member.getStatus()){
                teacherList.add(member);
            }
            else if (Member.DOCTOR_STATUS == member.getStatus()){
                if (member.getGraduateTime() == null){
                    doctorList.add(member);
                }
                else{
                    graduateDoctorList.add(member);
                }
            }
            else if (Member.MASTER_STATUS == member.getStatus()){
                if (member.getGraduateTime() == null){
                    masterList.add(member);
                }
                else{
                    graduateMasterList.add(member);
                }
            }
            else{
                throw new ProjectException(ErrorCodeMap.SYSTEM_ERROR);
            }
        }
        AllMemberGetResponse response = new AllMemberGetResponse();
        response.setTeacherList(teacherList);
        response.setDoctorList(doctorList);
        response.setMasterList(masterList);
        response.setGraduateDoctorList(graduateDoctorList);
        response.setGraduateMasterList(graduateMasterList);
        return response;
    }

//    public MemberDetailGetResponse getMemberDetail(MemberDetailGetRequest request){
//        Member member = memberDao.getEntityById(request.getMemberId());
//        member.setHeadUrl(ProjectConstant.MEMBER_IMG_DIR+"/"+member.getHeadUrl());
//        MemberDetailGetResponse response = new MemberDetailGetResponse();
//        response.setMember(member);
//        return response;
//    }

    public PeopleDetailGetResponse getPeopleDetail(PeopleDetailGetRequest request){
        List<Member> memberList = memberDao.getEntityListByUrlName(request.getUrlName());
        if (CollectionUtils.isEmpty(memberList)){
            throw new ProjectException(ErrorCodeMap.PARAMETER_ERROR);
        }
        Member member = memberList.get(0);
        PeopleDetailGetResponse response = new PeopleDetailGetResponse();
        member.setHeadUrl(ProjectConstant.MEMBER_IMG_DIR+"/"+member.getHeadUrl());
        response.setMember(member);
        return response;
    }
}
