package com.strongliu.blog.vo;

import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/18.
 */

@Component
public class CategoryVo {
    private Category category;
    private List<Post> postList;

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
}
