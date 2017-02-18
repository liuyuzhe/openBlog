package com.strongliu.blog.service;

import com.strongliu.blog.dao.PostDao;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.vo.PostVo;
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

    public Post findPublishPostById(String id)
    {
        return postDao.selectPublishById(id);
    }

    public Post findPublishPrevPostById(String id)
    {
        return postDao.selectPublishPrevById(id);
    }

    public Post findPublishNextPostById(String id)
    {
        return postDao.selectPublishNextById(id);
    }

    public List<Post> findAllPublishPost(int pageId, int pageSize)
    {
        int startIndex = (pageId - 1) * pageSize;
        return postDao.selectAllPublish(startIndex, pageSize);
    }

    public List<Post> findAllPublishPostByCategoryId(int categoryId, int pageId, int pageSize)
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
