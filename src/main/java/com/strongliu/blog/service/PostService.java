package com.strongliu.blog.service;

import com.strongliu.blog.dao.PostDao;
import com.strongliu.blog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuyuzhe on 2017/2/2.
 */

@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    public Post findPostById(String id)
    {
        return postDao.selectById(id);
    }

    public Post findPrevPostById(String id)
    {
        return postDao.selectPrevById(id);
    }

    public Post findNextPostById(String id)
    {
        return postDao.selectNextById(id);
    }

}
