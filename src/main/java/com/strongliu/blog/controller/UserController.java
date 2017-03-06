package com.strongliu.blog.controller;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.constant.ErrorMessage;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private ResponseDto responseDto;

    @RequestMapping(method = RequestMethod.GET)
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
    public String inputRegister() {
        return "user/register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String inputLogin(@ModelAttribute(value = "message") String message, HttpServletRequest request) {
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
    public Object saveRegister(RegisterFormVo registerFormVo, Errors errors) {
//        registerFormValidator.validate(registerFormVo, errors);
//        if (errors.hasErrors()) {
//            return "redirect:" + "/user/register";
//        }
//
//        userManager.addUserFormVo(registerFormVo);

        responseDto.setCode(ErrorMessage.FAILED.getCode());
        responseDto.setMessage(ErrorMessage.FAILED.getMessage());
        return responseDto;

//        return "redirect:" + "/user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String saveLogin(LoginFormVo loginFormVo, @RequestParam(value = "next", required = false) String next,
                            HttpServletRequest request, HttpServletResponse response,
                            HttpSession session, final RedirectAttributes redirectAttributes, Errors errors) {
        loginFormValidator.validate(loginFormVo, errors);
        if (errors.hasErrors()) {
            return "redirect:" + "/login";
        }

        User user = userManager.getUserByLoginFormVo(loginFormVo);
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:" + "/user/login";
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
            return "redirect:" + next;
        }

        redirectAttributes.addFlashAttribute("message", "登陆成功");
        return "redirect:" + "/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response, HttpSession session) {
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
