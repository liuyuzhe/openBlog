package com.strongliu.blog.entity;

import java.util.Date;

/**
 * Created by liuyuzhe on 2017/2/15.
 */
public class User {
    private String id;
    private String name;
    private String password;
    private String nickname;
    private String email;
    private String avatar_url;
    private Date register_time;
    private Date activate_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public Date getActivate_time() {
        return activate_time;
    }

    public void setActivate_time(Date activate_time) {
        this.activate_time = activate_time;
    }
}
