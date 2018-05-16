package com.xhk.lab.rmodel;

import com.xhk.lab.model.Member;

import java.util.List;

/**
 * create by xhk on 2018/4/24
 */
public class TeacherGetResponse {
    private List<Member> teacherList;

    public List<Member> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Member> teacherList) {
        this.teacherList = teacherList;
    }
}
