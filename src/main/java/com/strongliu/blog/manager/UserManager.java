package com.strongliu.blog.manager;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.service.UserService;
import com.strongliu.blog.vo.LoginFormVo;
import com.strongliu.blog.vo.RegisterFormVo;
import com.strongliu.blog.vo.UserPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/25.
 */

@Component
public class UserManager {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPageVo userPageVo;

    @Transactional
    public UserPageVo getUserVoByPageId(int pageId) {
        List<User> userList = userService.findAllUser(pageId, Constant.PAGE_SIZE);
        if (userList == null) {
            return null;
        }

        int pageTotal = userService.pageTotal(Constant.PAGE_SIZE);

        userPageVo.setUserList(userList);
        userPageVo.setPageIndex(pageId);
        userPageVo.setPageTotal(pageTotal);

        return userPageVo;
    }

    @Transactional
    public User getUserByLoginInfo(LoginFormVo loginFormVo) {
        return userService.findUserByLoginInfo(loginFormVo);
    }

    @Transactional
    public int addUserFormVo(RegisterFormVo registerFormVo) {
        User user = new User();
        user.setName(registerFormVo.getUsername());
        user.setPassword(registerFormVo.getPassword());
        user.setNickname(registerFormVo.getNickname());
        user.setEmail(registerFormVo.getEmail());

        return userService.addUser(user);
    }

    @Transactional
    public int updateUser(User user) {
        return userService.updateUser(user);
    }

    @Transactional
    public int removeUser(String userId) {
        return userService.removeUserById(userId);
    }


}
