package com.strongliu.blog.vo;

import com.strongliu.blog.entity.Attach;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/5/4.
 */
public class AttachPageVo {
    private List<Attach> attachList;
    private int pageIndex;
    private int pageTotal;

    public List<Attach> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<Attach> attachList) {
        this.attachList = attachList;
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
