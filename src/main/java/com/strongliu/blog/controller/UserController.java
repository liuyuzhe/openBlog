package com.strongliu.blog.controller;

import com.strongliu.blog.constant.ErrorMessage;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.LoginInfo;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseDto responseDto;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register() {
        return "";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object login(@RequestBody LoginInfo loginInfo) {
        if (StringUtils.isEmpty(loginInfo.getUsername()) || StringUtils.isEmpty(loginInfo.getPassword())) {
            responseDto.setCode(ErrorMessage.FAILED.getCode());
            responseDto.setMessage(ErrorMessage.FAILED.getMessage());
            return responseDto;
        }

        try {
            User user = userService.findUserByName(loginInfo.getUsername());
            if (user == null) {
                responseDto.setCode(ErrorMessage.FAILED.getCode());
                responseDto.setMessage(ErrorMessage.FAILED.getMessage());
                return responseDto;
            }

            if (loginInfo.getPassword() != user.getPassword()) {
                responseDto.setCode(ErrorMessage.FAILED.getCode());
                responseDto.setMessage(ErrorMessage.FAILED.getMessage());
                return responseDto;
            }

            responseDto.setCode(ErrorMessage.SUCCESS.getCode());
            responseDto.setMessage(ErrorMessage.SUCCESS.getMessage());

            return responseDto;
        }
        catch (Exception e) {
            e.printStackTrace();

            responseDto.setCode(ErrorMessage.EXCEPTION.getCode());
            responseDto.setMessage(ErrorMessage.EXCEPTION.getMessage());

            return responseDto;
        }
    }
}
