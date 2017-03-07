package com.strongliu.blog.controller;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.manager.UserManager;
import com.strongliu.blog.validator.LoginFormValidator;
import com.strongliu.blog.validator.RegisterFormValidator;
import com.strongliu.blog.vo.LoginFormVo;
import com.strongliu.blog.vo.RegisterFormVo;
import com.strongliu.blog.vo.UserPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by liuyuzhe on 2017/2/22.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserManager userManager;
    @Autowired
    private RegisterFormValidator registerFormValidator;
    @Autowired
    private LoginFormValidator loginFormValidator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexUser(Model model) {
        return indexUserWithPage(1, model);
    }

    @RequestMapping(value = "/page/{pageId}", method = RequestMethod.GET)
    public String indexUserWithPage(@PathVariable int pageId, Model model) {
        UserPageVo userPageVo = userManager.getUserVoByPageId(pageId);
        if (userPageVo == null) {
            return "404";
        }

        model.addAttribute(userPageVo);

        return "user/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "user/register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute(value = "message") String message, HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie: cookies) {
//            if (cookie.getName().equals("username")) {
//                String username = cookie.getValue();
//                return "redirect:".concat("/");
//            }
//        }

        return "user/login";
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable String userId, Model model) {

        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object doRegister(RegisterFormVo registerFormVo, Errors errors) {
//        registerFormValidator.validate(registerFormVo, errors);
//        if (errors.hasErrors()) {
//            return "redirect:" + "/user/register";
//        }
//
//        userManager.addUserFormVo(registerFormVo);

        return new ResponseDto(0, "注册成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseDto doLogin(LoginFormVo loginFormVo, @RequestParam(value = "next", required = false) String next,
                            HttpServletRequest request, HttpServletResponse response,
                            HttpSession session, Errors errors) {
        loginFormValidator.validate(loginFormVo, errors);
        if (errors.hasErrors()) {
            return new ResponseDto(-1, "格式不匹配");
        }

        User user = userManager.getUserByLoginFormVo(loginFormVo);
        if (user == null) {
            return new ResponseDto(-1, "用户名或密码错误");
        }

        session.setAttribute(Constant.USER_SESSION_KEY, user);

        if (loginFormVo.isRemember()) {
//            TODO: 用户信息生成cookie加密
            String userCookie = loginFormVo.getUsername();
            Cookie usernameCookie = new Cookie(Constant.USER_COOKIE_KEY, userCookie);
            usernameCookie.setMaxAge(Constant.DAY_TIME * 7);
            boolean isSSL = request.getScheme().equalsIgnoreCase("https");
            usernameCookie.setSecure(isSSL);
            response.addCookie(usernameCookie);
        }

        if (!StringUtils.isEmpty(next)) {
//            return "redirect:" + next;
            return new ResponseDto(0, "重定向到next");
        }

        return new ResponseDto(0, "登陆成功");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpServletResponse response, HttpSession session) {
        session.invalidate();
        Cookie usernameCookie = new Cookie("username", "");
        usernameCookie.setMaxAge(0);
        response.addCookie(usernameCookie);

        return "redirect:" + "/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateUser(User user) {
        if (user == null) {
            return "redirect:" + "/user/";
        }

        userManager.updateUser(user);

        return "redirect:" + "user/list";
    }

    @RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String userId) {
        userManager.removeUser(userId);

        return "redirect:" + "user/list";
    }
}
