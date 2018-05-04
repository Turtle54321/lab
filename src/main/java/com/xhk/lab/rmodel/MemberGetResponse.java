package com.xhk.lab.rmodel;

import com.xhk.lab.model.Member;

import java.util.List;

/**
 * create by xhk on 2018/4/30
 */
public class MemberGetResponse {
    private List<Member> teacherList;
    private List<Member> doctorList;
    private List<Member> masterList;
    private List<Member> graduateDoctorList;
    private List<Member> graduateMasterList;

    public List<Member> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Member> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Member> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Member> doctorList) {
        this.doctorList = doctorList;
    }

    public List<Member> getMasterList() {
        return masterList;
    }

    public void setMasterList(List<Member> masterList) {
        this.masterList = masterList;
    }

    public List<Member> getGraduateDoctorList() {
        return graduateDoctorList;
    }

    public void setGraduateDoctorList(List<Member> graduateDoctorList) {
        this.graduateDoctorList = graduateDoctorList;
    }

    public List<Member> getGraduateMasterList() {
        return graduateMasterList;
    }

    public void setGraduateMasterList(List<Member> graduateMasterList) {
        this.graduateMasterList = graduateMasterList;
    }
}
