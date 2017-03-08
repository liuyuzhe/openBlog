package com.strongliu.blog.service;

import com.strongliu.blog.dao.UserDao;
import com.strongliu.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/15.
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findUserById(String id) {
        return userDao.selectById(id);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return userDao.selectByUsernameAndPassword(username, password);
    }

    public List<User> findAllUser(int pageId, int pageSize) {
        pageId = pageId < 0 ? 1 : pageId;
        int startIndex = (pageId - 1) * pageSize;
        return userDao.selectAll(startIndex, pageSize);
    }

    public boolean findUserIsExit(String username) {
        return userDao.selectCountByUsername(username) > 0 ? true : false;
    }

    public int pageTotal(int pageSize)
    {
        int postToal = userDao.selectCount();
        int pageTotal = postToal / pageSize;
        if (postToal % pageSize != 0) {
            pageTotal += 1;
        }

        return pageTotal;
    }

    public int addUser(User user) {
        return userDao.insert(user);
    }

    public int updateUser(User user) {
        return userDao.update(user);
    }

    public int removeUserById(String id) {
        return userDao.deleteById(id);
    }
}
