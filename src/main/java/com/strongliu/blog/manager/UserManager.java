package com.strongliu.blog.manager;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.LoginInfo;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.service.UserService;
import com.strongliu.blog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/25.
 */

@Component
public class UserManager {

    @Autowired
    private UserService userService;

    @Autowired
    private UserVo userVo;

    public UserVo getUserVoByPageId(int pageId) {
        List<User> userList = userService.findAllUser(pageId, Constant.PAGE_SIZE);
        if (userList == null) {
            return null;
        }

        int pageTotal = userService.pageTotal(Constant.PAGE_SIZE);

        userVo.setUserList(userList);
        userVo.setPageIndex(pageId);
        userVo.setPageTotal(pageTotal);

        return userVo;
    }

    public User getUserByLoginInfo(LoginInfo loginInfo) {
        return userService.findUserByLoginInfo(loginInfo);
    }

}
