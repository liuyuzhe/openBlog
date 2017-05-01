package com.strongliu.blog.controller.admin;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.constant.ErrorCode;
import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.manager.UserManager;
import com.strongliu.blog.utility.SecurityUtil;
import com.strongliu.blog.validator.LoginFormValidator;
import com.strongliu.blog.validator.RegisterFormValidator;
import com.strongliu.blog.vo.LoginFormVo;
import com.strongliu.blog.vo.RegisterFormVo;
import com.strongliu.blog.vo.UserPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId,
                        @RequestParam(value = "limit", required = false, defaultValue = "15") Integer limit, Model model) {

        UserPageVo userPageVo = userManager.getUserPageVo(pageId, limit);
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
        registerFormValidator.validate(registerFormVo, errors);
        if (errors.hasErrors()) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        boolean isExit = userManager.getUserIsExit(registerFormVo.getUsername());
        if (isExit) {
            return new ResponseDto(ErrorCode.ERROR_EXISTED_USER);
        }

        try {
            userManager.addUserFormVo(registerFormVo);
            return new ResponseDto(ErrorCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute(value = "message") String message, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constant.USER_SESSION_KEY);
        if (user != null) {
            return this.redirect("/");
        }

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
            try {
                String userId = user.getId();
                String userCookie = SecurityUtil.encryptAES(userId, Constant.PASSWORD_SALT);
                Cookie cookieInfo = new Cookie(Constant.USER_COOKIE_KEY, userCookie);
                cookieInfo.setMaxAge(Constant.DAY_TIME * 7);
                boolean isSSL = request.getScheme().equalsIgnoreCase("https");
                cookieInfo.setSecure(isSSL);
                response.addCookie(cookieInfo);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseDto(ErrorCode.ERROR_ENCRYPT_FAILED);
            }
        }

//        处理登陆后自动跳转
//        if (!StringUtils.isEmpty(next)) {
//            return this.redirect(next);
//        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response, HttpSession session) {
        session.invalidate();
        Cookie usernameCookie = new Cookie(Constant.USER_COOKIE_KEY, "");
        usernameCookie.setMaxAge(0);
        response.addCookie(usernameCookie);

        return this.redirect("/");
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public String editUser(@PathVariable String userId, Model model) {
        User user = userManager.getUserVo(userId);

        model.addAttribute(user);

        return this.renderAdmin("user_edit");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDto updateUser(User user) {
        // 验证用户信息合法性

        try {
            userManager.updateUser(user);
            return new ResponseDto(ErrorCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
        }
    }

    @RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDto deleteUser(@PathVariable String userId) {
        try {
            userManager.removeUser(userId);
            return new ResponseDto(ErrorCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
        }
    }
}
