package com.strongliu.blog.controller;

import com.strongliu.blog.entity.LoginInfo;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liuyuzhe on 2017/2/22.
 */

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public String login(@RequestBody LoginInfo loginInfo) {
        if (StringUtils.isEmpty(loginInfo.getUsername()) || StringUtils.isEmpty(loginInfo.getPassword())) {
        }

        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "redirect:/login";
    }

}
