package com.strongliu.blog.vo;

import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.entity.User;

/**
 * Created by liuyuzhe on 2017/2/18.
 */
public class PostVo {
    private Post post;
    private Category category;
    private User user;
    private Post postPrev;
    private Post postNext;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPostPrev() {
        return postPrev;
    }

    public void setPostPrev(Post postPrev) {
        this.postPrev = postPrev;
    }

    public Post getPostNext() {
        return postNext;
    }

    public void setPostNext(Post postNext) {
        this.postNext = postNext;
    }
}
