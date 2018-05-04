package com.xhk.lab.dao;

import com.xhk.lab.model.Resource;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * create by xhk on 2018/4/26
 */
public interface ResourceDao {

    /**
     * 分页获取时间逆序
     * @param start
     * @param num
     * @return
     */
    @Select("select * from resource order by create_time desc limit #{start},#{num};")
    public List<Resource> getEntityListByPage(@Param(value = "start") Integer start, @Param(value = "num") Integer num);

    /**
     * 获取总数
     * @return
     */
    @Select("select count(*) from resource;")
    public int countNum();

    /**
     * 获取特定id的资源
     * @param id
     * @return
     */
    @Select("select * from resource where id = #{id};")
    public Resource getEntityById(@Param(value = "id") Integer id);

}
