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
        pageId = pageId < 0 ? 1 : pageId;
        int startIndex = (pageId - 1) * pageSize;
        return postDao.selectAllPublish(startIndex, pageSize);
    }

    public List<Post> findAllPublishPostByIdList(List<String> idList, int pageId, int pageSize)
    {
        pageId = pageId < 0 ? 1 : pageId;
        int startIndex = (pageId - 1) * pageSize;
        return postDao.selectAllPublishByIdList(idList, startIndex, pageSize);
    }

    public int pageTotal(int pageSize)
    {
        int postToal = postDao.selectCount();
        int pageTotal = postToal / pageSize;
        if (postToal % pageSize != 0) {
            pageTotal += 1;
        }

        return pageTotal;
    }

    public int addPost(Post post)
    {
        return postDao.insert(post);
    }

    public int updatePost(Post post)
    {
        return postDao.update(post);
    }

    public int removePostById(String id)
    {
        return postDao.deleteById(id);
    }
}
