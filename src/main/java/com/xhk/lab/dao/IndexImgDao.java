package com.xhk.lab.dao;

import com.xhk.lab.model.IndexImg;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * create by xhk on 2018/4/8
 */
public interface IndexImgDao {

    /**
     * 按seq升序获取轮播图片
     * @return
     */
    @Select("select * from index_img order by seq asc limit 3;")
    public List<IndexImg> getEntityList();

}
