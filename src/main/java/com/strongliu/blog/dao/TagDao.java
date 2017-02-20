package com.strongliu.blog.dao;


import com.strongliu.blog.entity.Tag;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */
public interface TagDao {

    Tag selectById(int id);

    Tag selectByName(String name);

    List<Tag> selectAllByIdList(List<Integer> idList);

    List<Tag> selectAll();
}
