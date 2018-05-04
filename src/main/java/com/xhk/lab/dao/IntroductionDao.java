package com.xhk.lab.dao;

import com.xhk.lab.model.Introduction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

/**
 * create by xhk on 2018/4/13
 */
public interface IntroductionDao {

    /**
     * 获取实验室简介
     * @return
     */
    @Select("select * from introduction order by id asc limit 1")
    public Introduction getEntity();

}
