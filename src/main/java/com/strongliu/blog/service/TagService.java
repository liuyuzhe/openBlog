package com.strongliu.blog.service;

import com.strongliu.blog.dao.TagDao;
import com.strongliu.blog.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */

@Service
public class TagService {

    @Autowired
    private TagDao tagDao;

    public Tag findTagById(int id) {
        return tagDao.selectById(id);
    }

    public Tag findTagByName(String name) {
        return tagDao.selectByName(name);
    }

    public List<Tag> findAllTagByPostId(String postId) {
        return tagDao.selectAllTagByPostId(postId);
    }
}
