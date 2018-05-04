package com.xhk.lab.dao;

import com.xhk.lab.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * create by xhk on 2018/4/14
 */
public interface NewsDao {

    /**
     * 分页取
     * @param start 取的第一个元素
     * @param num 取的数量
     * @return
     */
    @Select("select * from news where type=#{type} limit #{start},#{num};")
    public List<News> getEntityListByPage(@Param(value = "type") Integer type, @Param(value = "start") int start, @Param(value = "num") int num);

    /**
     * 取总页数
     * @return
     */
    @Select("select count(*) from news where type=#{type};")
    public int countNum(@Param(value = "type") Integer type);



    /**
     * 获取特定新闻
     * @param id
     * @return
     */
    @Select("select * from news where id = #{id};")
    public News getEntityById(@Param(value = "id") Integer id);
}
