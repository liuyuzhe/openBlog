package com.strongliu.blog.interceptor;

import com.strongliu.blog.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuyuzhe on 2017/2/25.
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String[] IGNORE_URL = {""};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean flag = false;

        String servletPath = request.getServletPath();
        for (String path : IGNORE_URL) {
            if (servletPath.contains(path)) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                request.setAttribute("message", "请登陆");
                request.setAttribute("next", request.getRequestURI());
                request.getRequestDispatcher("/account/loginForm").forward(request, response);
            }
            else {
                flag = true;
            }
        }

        return flag;

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
