package com.strongliu.blog.interceptor;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuyuzhe on 2017/2/25.
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String[] IGNORE_URI = {"/user/register", "/user/login", "user/logout"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();

        for (String uri : IGNORE_URI) {
            if (requestURI.contains(uri)) {
                return true;
            }
        }

        if (requestURI.startsWith("/admin")) {
            User user = (User) request.getSession().getAttribute(Constant.USER_SESSION_KEY);
            if (user == null) {
                request.setAttribute("message", "请登陆");
                request.setAttribute("next", request.getRequestURI());
                request.getRequestDispatcher("/account/loginForm").forward(request, response);
                return false;
            }
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
