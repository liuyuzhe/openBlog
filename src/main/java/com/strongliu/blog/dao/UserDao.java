package com.strongliu.blog.dao;

import com.strongliu.blog.entity.User;

/**
 * Created by liuyuzhe on 2017/2/13.
 */
public interface UserDao {

    int insert(User user);

    User selectById(String id);

    User selectByName(String name);

    int update(User user);

    int deleteById(String id);
}
