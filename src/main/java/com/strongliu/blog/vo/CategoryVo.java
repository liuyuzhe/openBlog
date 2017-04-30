package com.strongliu.blog.vo;

import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Post;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/18.
 */

public class CategoryVo {
    private Category category;
    private List<Post> postList;
    private int pageIndex;
    private int pageTotal;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
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
