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

    public String render_500() {
        return "common/500";
    }

    public String redirect(String viewName) {
        return "redirect:" + viewName;
    }

    public int getUserId() {
        return 0;
    }
}
