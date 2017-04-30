package com.strongliu.blog.vo;

import com.strongliu.blog.entity.User;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/25.
 */

public class UserPageVo {
    private List<User> userList;
    private int pageIndex;
    private int pageTotal;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
}
