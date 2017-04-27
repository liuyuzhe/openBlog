package com.strongliu.blog.controller.admin;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.constant.ErrorCode;
import com.strongliu.blog.controller.BaseController;
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
@RequestMapping("/admin/user")
public class UserController extends BaseController {

    @Autowired
    private UserManager userManager;
    @Autowired
    private RegisterFormValidator registerFormValidator;
    @Autowired
    private LoginFormValidator loginFormValidator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                        @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit, Model model) {

        UserPageVo userPageVo = userManager.getUserVoByPageId(pageId);
        if (userPageVo == null) {
            return this.render_404();
        }

        return this.renderAdmin("user_list");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return this.renderAdmin("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResponseDto register(RegisterFormVo registerFormVo, Errors errors) {
//        registerFormValidator.validate(registerFormVo, errors);
//        if (errors.hasErrors()) {
//            return "redirect:" + "/user/register";
//        }
//
//        userManager.addUserFormVo(registerFormVo);

        boolean isExit = userManager.getUserIsExit(registerFormVo.getUsername());
        if (isExit) {
            return new ResponseDto(ErrorCode.ERROR_EXISTED_USER);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
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

        return this.renderAdmin("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseDto login(LoginFormVo loginFormVo, @RequestParam(value = "next", required = false) String next,
                            HttpServletRequest request, HttpServletResponse response,
                            HttpSession session, Errors errors) {
        loginFormValidator.validate(loginFormVo, errors);
        if (errors.hasErrors()) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        boolean isExit = userManager.getUserIsExit(loginFormVo.getUsername());
        if (!isExit) {
            return new ResponseDto(ErrorCode.ERROR_NO_EXISTED_USER);
        }

        User user = userManager.getUserByLoginFormVo(loginFormVo);
        if (user == null) {
            return new ResponseDto(ErrorCode.ERROR_PASSWORD_NOT_MATCH);
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
            // 重定向到next
            return new ResponseDto(ErrorCode.SUCCESS);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response, HttpSession session) {
        session.invalidate();
        Cookie usernameCookie = new Cookie("username", "");
        usernameCookie.setMaxAge(0);
        response.addCookie(usernameCookie);

        return "redirect:" + "/";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public String editUser(@PathVariable String userId, Model model) {

        return this.renderAdmin("user_edit");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDto updateUser(User user) {
        if (user == null) {
            return "redirect:" + "/user/";
        }

        userManager.updateUser(user);

        return this.renderAdmin("user_edit");
    }

    @RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDto deleteUser(@PathVariable String userId) {
        userManager.removeUser(userId);

        return this.renderAdmin("user_edit");
    }
}