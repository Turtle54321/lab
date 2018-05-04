package com.xhk.lab.dao;

import com.xhk.lab.model.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * create by xhk on 2018/4/23
 */
public interface MemberDao {

    /**
     * 随机取num个成员
     * @param num
     * @return
     */
    @Select("select * from member order by rand() limit ${num};")
    public List<Member> getEntityByRandon(@Param(value = "num")Integer num);

    /**
     * 获取特定成员信息
     * @param id
     * @return
     */
    @Select("select * from member where id = #{id};")
    public Member getEntityById(@Param(value = "id") Integer id);

    /**
     * 获取所有成员，按入学时间递增
     * @return
     */
    @Select("select * from member order by enter_time asc;")
    public List<Member> getEntityList();

    /**
     * 获取总数
     * @param status
     * @return
     */
    @Select("select count(*) from member where status = #{status};")
    public int countNum(@Param(value = "status") Integer status);
}
