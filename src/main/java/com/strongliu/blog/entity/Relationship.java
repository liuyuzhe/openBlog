package com.strongliu.blog.entity;

/**
 * Created by liuyuzhe on 2017/2/20.
 */
public class Relationship {
    private String target_id;
    private Integer term_id;

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public Integer getTerm_id() {
        return term_id;
    }

    public void setTerm_id(Integer term_id) {
        this.term_id = term_id;
    }
}
