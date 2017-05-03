package com.strongliu.blog.entity;

/**
 * Created by liuyuzhe on 2017/2/20.
 */
public class Relationship {
    private Integer target_id;
    private Integer term_id;

    public Relationship(Integer targetId, Integer termId) {
        this.target_id = targetId;
        this.term_id = termId;
    }

    public Integer getTarget_id() {
        return target_id;
    }

    public void setTarget_id(Integer target_id) {
        this.target_id = target_id;
    }

    public Integer getTerm_id() {
        return term_id;
    }

    public void setTerm_id(Integer term_id) {
        this.term_id = term_id;
    }
}
