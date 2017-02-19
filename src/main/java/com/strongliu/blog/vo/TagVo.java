package com.strongliu.blog.vo;

import com.strongliu.blog.entity.Post;
import com.strongliu.blog.entity.Tag;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */
public class TagVo {
    private Tag tag;
    private List<Post> postList;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
