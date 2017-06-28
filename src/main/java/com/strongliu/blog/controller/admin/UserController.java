package com.strongliu.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId,
                        @RequestParam(value = "limit", required = false, defaultValue = "15") Integer limit, Model model) {

        try {
            UserPageVo userPageVo = userManager.getUserPageVo(pageId, limit);
            if (!ObjectUtils.isEmpty(userPageVo)) {
                model.addAttribute(userPageVo);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return this.render_500();
        }

        return this.renderAdmin("user_list");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return this.renderAdmin("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto register(RegisterFormVo registerFormVo, Errors errors) {
        registerFormValidator.validate(registerFormVo, errors);
        if (errors.hasErrors()) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        try {
            boolean isExit = userManager.getUserIsExit(registerFormVo.getUsername());
            if (isExit) {
                return new ResponseDto(ErrorCode.ERROR_EXISTED_USER);
            }

            userManager.addUserFormVo(registerFormVo);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "next", required = false) String next, Model model) {

        if (!StringUtils.isEmpty(next)) {
            // 直接写 model.addAttribute(next) 会导致model里key值变为"string"
            model.addAttribute("next", next);
        }

        return this.renderAdmin("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto login(LoginFormVo loginFormVo, @RequestParam(value = "next", required = false) String next,
                            HttpServletRequest request, HttpServletResponse response,
                            HttpSession session, Errors errors) {
        loginFormValidator.validate(loginFormVo, errors);
        if (errors.hasErrors()) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        try {
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
                    Integer userId = user.getId();
                    String userInfo = userId.toString();
                    String userCookie = SecurityUtil.encryptAES(userInfo, Constant.PASSWORD_SALT);
                    Cookie cookieInfo = new Cookie(Constant.USER_COOKIE_KEY, userCookie);
                    cookieInfo.setMaxAge(Constant.DAY_TIME * 7);
                    boolean isSSL = request.getScheme().equalsIgnoreCase("https");
                    cookieInfo.setSecure(isSSL);
                    response.addCookie(cookieInfo);
                } catch (Exception e) {
                    return new ResponseDto(ErrorCode.ERROR_ENCRYPT_FAILED);
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        // 处理登陆后自动跳转
        if (!StringUtils.isEmpty(next)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("next", next);
            String data = JSON.toJSONString(jsonObject);
            return new ResponseDto<>(ErrorCode.SUCCESS, data);
        }

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
    public String editUser(@PathVariable Integer userId, Model model) {
        try {
            User user = userManager.getUserVo(userId);
            if (!ObjectUtils.isEmpty(user)) {
                model.addAttribute(user);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return this.render_500();
        }

        return this.renderAdmin("user_edit");
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public ResponseDto updateUser(User user) {
        // 验证User合法性

        try {
            userManager.updateUser(user);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.DELETE, RequestMethod.POST})
    @ResponseBody
    public ResponseDto deleteUser(@RequestParam("userId") Integer userId) {
        try {
            userManager.removeUser(userId);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }
}
