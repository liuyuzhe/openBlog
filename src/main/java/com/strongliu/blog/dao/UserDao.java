package com.strongliu.blog.dao;

import com.strongliu.blog.entity.User;

/**
 * Created by liuyuzhe on 2017/2/13.
 */
public interface UserDao {

    User selectById(int id);

    User selectByName(String name);

    int insert(User user);

    int update(User user);

    int deleteById(int id);
}
