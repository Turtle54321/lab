package com.xhk.lab.service;

import com.xhk.lab.common.constant.ProjectConstant;
import com.xhk.lab.dao.IndexImgDao;
import com.xhk.lab.dao.IntroductionDao;
import com.xhk.lab.dao.MemberDao;
import com.xhk.lab.dao.NewsDao;
import com.xhk.lab.model.IndexImg;
import com.xhk.lab.model.Introduction;
import com.xhk.lab.model.Member;
import com.xhk.lab.model.News;
import com.xhk.lab.rmodel.IndexDisplayResponse;
import com.xhk.lab.rmodel.IndexImgGetResponse;
import com.xhk.lab.rmodel.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * create by xhk on 2018/4/29
 */
@Service
public class IndexService {
    private static Logger logger = LoggerFactory.getLogger(IndexService.class);

    @Autowired
    private IndexImgDao indexImgDao;

    @Autowired
    private IntroductionDao introductionDao;

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private MemberDao memberDao;

    public IndexDisplayResponse indexDisplay(){
        Introduction introduction = introductionDao.getEntity();
        List<News> newsList = newsDao.getEntityListByPage(News.NEWS_TYPE,0,5);
        List<News> projectList = newsDao.getEntityListByPage(News.PROJECT_TPYE,0,5);
        List<Project> projects = new ArrayList<>(projectList.size());
        for (News news : projectList){
            Project project = new Project();
            project.setId(news.getId());
            project.setCreateTime(news.getCreateTime());
            project.setEtitle(news.getEtitle());
            project.setTitle(news.getTitle());
            projects.add(project);
        }
        List<Member> memberList = memberDao.getEntityByRandon(6);
        for (int i = 0; i< memberList.size();i++){
            memberList.get(i).setHeadUrl(ProjectConstant.MEMBER_IMG_DIR+"/"+memberList.get(i).getHeadUrl());
        }
        IndexDisplayResponse response = new IndexDisplayResponse();
        response.setIntroduction(introduction);
        response.setMemberList(memberList);
        response.setNewsList(newsList);
        response.setProjectList(projects);
        return response;

    }

    public IndexImgGetResponse getIndexImg(){
        List<IndexImg> indexImgList = indexImgDao.getEntityList();
        List<String> imgUrlList = new ArrayList<>(indexImgList.size());
        for (int i = 0; i < indexImgList.size(); i++){
            imgUrlList.add(ProjectConstant.INDEX_IMG_DIR+"/"+indexImgList.get(i).getUrl());
        }
        IndexImgGetResponse response = new IndexImgGetResponse();
        response.setImgUrlList(imgUrlList);
        return response;
    }
}
