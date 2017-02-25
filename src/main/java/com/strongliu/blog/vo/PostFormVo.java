package com.strongliu.blog.vo;

import com.strongliu.blog.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/25.
 */

@Component
public class PostFormVo {
    private Post post;
    private List<Integer> categoryIdList;
    private List<Integer> tagIdList;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Integer> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<Integer> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
    }
}
