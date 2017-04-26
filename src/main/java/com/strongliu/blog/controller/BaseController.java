package com.strongliu.blog.controller;

/**
 * Created by liuyuzhe on 2017/4/26.
 */
public abstract class BaseController {
    public String renderAdmin(String viewName) {
        return "admin/" + viewName;
    }

    public String renderUser(String viewName) {
        return "user/" + viewName;
    }

    public String render_404() {
        return "common/404";
    }
}
