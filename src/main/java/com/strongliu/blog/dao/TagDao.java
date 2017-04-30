package com.strongliu.blog.dao;


import com.strongliu.blog.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */
public interface TagDao {

    Tag selectById(Integer id);

    Tag selectBySlug(String slug);

    List<Tag> selectAll();

    List<Tag> selectAllByIdList(List<Integer> idList);

    int insert(Tag tag);

    int update(Tag tag);

    int updatePostCount(@Param("id") Integer id, @Param("number") int number);

    int deleteById(Integer id);
}
