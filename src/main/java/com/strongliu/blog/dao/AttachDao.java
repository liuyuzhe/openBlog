package com.strongliu.blog.dao;

import com.strongliu.blog.entity.Attach;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/5/4.
 */
public interface AttachDao {

    Attach selectById(int id);

    Attach selectBySlug(String slug);

    List<Attach> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    int selectCount();

    int insert(Attach attach);

    int deleteById(int id);
}
