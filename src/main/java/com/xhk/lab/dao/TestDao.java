package com.xhk.lab.dao;

import com.xhk.lab.model.Test;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * create by xhk on 18/3/2
 */
public interface TestDao {

    @Select("select * from test where id = #{id}")
    public Test getEnteryById(@Param("id") Integer id);
}
