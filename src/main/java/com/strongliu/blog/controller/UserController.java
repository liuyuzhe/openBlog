package com.strongliu.blog.controller;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.LoginInfo;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.manager.UserManager;
import com.strongliu.blog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "user/registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register() {
//        return "user/registerPage";
        return "redirect" + "/user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@ModelAttribute(value = "message") String message, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("username")) {
                String username = cookie.getValue();
                return "redirect:".concat("/");
            }
        }

        return "user/loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(LoginInfo loginInfo, @RequestParam(value = "next", required = false) String next,
                        HttpServletRequest request, HttpServletResponse response,
                        HttpSession session, final RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmpty(loginInfo.getUsername()) || StringUtils.isEmpty(loginInfo.getPassword())) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码为空");
            return "redirect:" + "/user/login";
        }

        User user = userManager.getUserByLoginInfo(loginInfo);
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:" + "/user/login";
        }

        if (loginInfo.isRemember()) {
            Cookie usernameCookie = new Cookie("username", loginInfo.getUsername());
            usernameCookie.setMaxAge(Constant.DAY_TIME * 7);
            response.addCookie(usernameCookie);
        }

        if (!StringUtils.isEmpty(next)) {
            return "redirect:" + next;
        }

        session.setAttribute("user", user);
        redirectAttributes.addFlashAttribute("message", "登陆成功");
        return "redirect:" + "/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:" + "/";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String indexUser(Model model) {
        return indexUserWithPage(1, model);
    }

    @RequestMapping(value = "/page/{pageId}", method = RequestMethod.GET)
    public String indexUserWithPage(@PathVariable int pageId, Model model) {
        UserVo userVo = userManager.getUserVoByPageId(pageId);
        if (userVo == null) {
            return "404";
        }

        model.addAttribute(userVo);

        return "user/list";
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public String editUserPage(@PathVariable String userId, Model model) {

        return "user/registerPage";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(User user) {
        return "redirect:" + "user/list";
    }

    @RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
    public String removeUser(@PathVariable String userId) {
        return "redirect:" + "user/list";
    }
}
