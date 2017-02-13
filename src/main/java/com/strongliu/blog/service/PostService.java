package com.strongliu.blog.service;

import com.strongliu.blog.dao.PostDao;
import com.strongliu.blog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Post> findAllPublishPost(int pageId, int pageSize)
    {
        int startIndex = (pageId - 1) * pageSize;
        return postDao.selectAllPublish(startIndex, pageSize);
    }

    public List<Post> findAllPublishPostByCategoryId(String categoryId, int pageId, int pageSize)
    {
        int startIndex = (pageId - 1) * pageSize;
        return postDao.selectAllPublishByCategoryId(categoryId, startIndex, pageSize);
    }

    public int totalPage(int pageSize)
    {
        int totalPost = postDao.selectCount();
        int totalPage = totalPost / pageSize;
        if (totalPost % pageSize != 0) {
            totalPage += 1;
        }

        return totalPage;
    }

}
