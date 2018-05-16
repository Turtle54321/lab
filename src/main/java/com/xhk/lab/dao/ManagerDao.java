package com.xhk.lab.dao;

import com.xhk.lab.model.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * create by xhk on 2018/3/28
 */
public interface ManagerDao {

    /**
     * 登录时查询管理员用户
     * @param username
     * @param password
     * @return
     */
    @Select("select * from manager where name=#{username} and password=#{password};")
    Manager checkLogin(@Param("username") String username, @Param("password") String password);
}
