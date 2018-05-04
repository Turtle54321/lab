package com.xhk.lab.rmodel;

import com.xhk.lab.model.IndexImg;
import com.xhk.lab.model.Introduction;
import com.xhk.lab.model.Member;
import com.xhk.lab.model.News;

import java.util.List;

/**
 * create by xhk on 2018/4/29
 */
public class IndexDisplayResponse {
    private Introduction introduction;
    private List<News> newsList;
    private List<Project> projectList;
    private List<Member> memberList;

    public Introduction getIntroduction() {
        return introduction;
    }

    public void setIntroduction(Introduction introduction) {
        this.introduction = introduction;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }
}
