package com.strongliu.blog.manager;

import com.strongliu.blog.entity.User;
import com.strongliu.blog.service.UserService;
import com.strongliu.blog.vo.LoginFormVo;
import com.strongliu.blog.vo.RegisterFormVo;
import com.strongliu.blog.vo.UserPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/25.
 */

@Component
public class UserManager {

    @Autowired
    private UserService userService;

    public UserPageVo getUserPageVo(int pageId, int limit) {
        List<User> userList = userService.findAllUser(pageId, limit);

        int pageTotal = userService.pageTotal(limit);

        UserPageVo userPageVo = new UserPageVo();
        userPageVo.setUserList(userList);
        if (pageId > 0 && pageTotal > 0) {
            userPageVo.setPageIndex(pageId);
            userPageVo.setPageTotal(pageTotal);
        }

        return userPageVo;
    }

    public User getUserVo(int userId) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return null;
        }

        return user;
    }

    public User getUserByLoginFormVo(LoginFormVo loginFormVo) {
        return userService.findUserByUsernameAndPassword(loginFormVo.getUsername(), loginFormVo.getPassword());
    }

    public boolean getUserIsExit(String username) {
        return userService.findUserIsExit(username);
    }

    public int addUserFormVo(RegisterFormVo registerFormVo) {
        User user = new User();
        user.setName(registerFormVo.getUsername());
        user.setPassword(registerFormVo.getPassword());
        user.setNickname(registerFormVo.getUsername());
        user.setEmail(registerFormVo.getEmail());
        Date date = new Date();
        user.setRegister_time(date);
        user.setActivate_time(date);

        return userService.addUser(user);
    }

    public int updateUser(User user) {
        return userService.updateUser(user);
    }

    public int removeUser(int userId) {
        return userService.removeUserById(userId);
    }


}
