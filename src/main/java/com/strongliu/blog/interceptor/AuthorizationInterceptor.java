package com.strongliu.blog.interceptor;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.service.UserService;
import com.strongliu.blog.utility.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuyuzhe on 2017/2/25.
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String[] IGNORE_URI = {"/admin/user/register", "/admin/user/login", "/admin/user/logout"};

    @Autowired
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();

        for (String uri : IGNORE_URI) {
            if (requestURI.contains(uri)) {
                return true;
            }
        }

        User user = (User) request.getSession().getAttribute(Constant.USER_SESSION_KEY);
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            if (!ObjectUtils.isEmpty(cookies)) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(Constant.USER_COOKIE_KEY)) {
                        String cookieInfo = cookie.getValue();
                        if (!StringUtils.isEmpty(cookieInfo)) {
                            try {
                                String userInfo = SecurityUtil.decryptAES(cookieInfo, Constant.PASSWORD_SALT);
                                Integer userId = Integer.valueOf(userInfo);
                                user = userService.findUserById(userId);
                                request.getSession().setAttribute(Constant.USER_SESSION_KEY, user);
                            } catch (Exception e) {
                                logger.error(e.toString());
                            }
                        }
                    }
                }
            }
        }

        if (requestURI.startsWith("/admin") && user == null) {
            String nextUrl = "/admin/user/login?" + "next=" + request.getRequestURI();
            response.sendRedirect(nextUrl);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
