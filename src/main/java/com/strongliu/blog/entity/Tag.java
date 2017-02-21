package com.strongliu.blog.entity;

import java.util.Date;

/**
 * Created by liuyuzhe on 2017/2/19.
 */
public class Tag {
    private Integer id;
    private String name;
    private String slug;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
