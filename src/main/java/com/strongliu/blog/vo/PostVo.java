package com.strongliu.blog.vo;

import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/18.
 */

@Component
public class PostVo {
    private Post post;
    private List<Category> categoryList;
    private List<Tag> tagList;
    private User user;
    private Post postPrev;
    private Post postNext;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
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
