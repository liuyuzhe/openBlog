package com.strongliu.blog.controller;

import com.strongliu.blog.entity.User;
import com.strongliu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "user/list";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String add(User user) {
        return "redirect:/user";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String update(User user) {
        return "user/edit";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public String remove(@PathVariable String userId) {
        return "user/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit() {
        return "";
    }

}
