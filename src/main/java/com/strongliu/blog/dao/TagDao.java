package com.strongliu.blog.dao;


import com.strongliu.blog.entity.Tag;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */
public interface TagDao {

    Tag selectById(Integer id);

    Tag selectByName(String name);

    List<Tag> selectAllByIdList(List<Integer> idList);

    List<Tag> selectAll();

    int insert(Tag tag);

    int update(Tag tag);

    int deleteById(Integer id);
}
