package com.strongliu.blog.vo;

/**
 * Created by liuyuzhe on 2017/2/27.
 */

public class LoginFormVo {
    private String username;
    private String password;
    private boolean remember;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
