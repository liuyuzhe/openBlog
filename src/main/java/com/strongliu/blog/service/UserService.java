package com.strongliu.blog.service;

import com.strongliu.blog.dao.UserDao;
import com.strongliu.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuyuzhe on 2017/2/15.
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public int addUser(User user) {
        return userDao.insert(user);
    }

    public User findUserById(int id) {
        return userDao.selectById(id);
    }

    public User findUserByName(String name) {
        return userDao.selectByName(name);
    }

    public int updateUser(User user) {
        return userDao.update(user);
    }

    public int removeUser(int id) {
        return userDao.deleteById(id);
    }
}
