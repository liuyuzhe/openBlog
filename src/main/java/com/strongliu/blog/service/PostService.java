package com.strongliu.blog.service;

import com.strongliu.blog.dao.PostDao;
import com.strongliu.blog.entity.Post;
import javafx.geometry.Pos;
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

    /**
     * 查找文章
     */
    public Post findPostById(int id)
    {
        return postDao.selectById(id);
    }

    /**
     * 查找已发布文章
     */
    public Post findPublishPostBySlug(String slug)
    {
        return postDao.selectPublishBySlug(slug);
    }

    /**
     * 查找上一篇已发布文章
     */
    public Post findPublishPrevPostById(int id)
    {
        return postDao.selectPublishPrevById(id);
    }

    /**
     * 查找下一篇已发布文章
     */
    public Post findPublishNextPostById(int id)
    {
        return postDao.selectPublishNextById(id);
    }

    /**
     * 查找文章列表
     */
    public List<Post> findAllPost(int pageId, int pageSize)
    {
        pageId = pageId < 0 ? 1 : pageId;
        int startIndex = (pageId - 1) * pageSize;
        return postDao.selectAll(startIndex, pageSize);
    }

    /**
     * 查找已发布文章列表
     */
    public List<Post> findAllPublishPost(int pageId, int pageSize)
    {
        pageId = pageId < 0 ? 1 : pageId;
        int startIndex = (pageId - 1) * pageSize;
        return postDao.selectAllPublish(startIndex, pageSize);
    }

    /**
     * 根据Id列表,查找已发布文章列表
     */
    public List<Post> findAllPublishPostByIdList(List<Integer> idList, int pageId, int pageSize)
    {
        pageId = pageId < 0 ? 1 : pageId;
        int startIndex = (pageId - 1) * pageSize;
        return postDao.selectAllPublishByIdList(idList, startIndex, pageSize);
    }

    public int pageTotal(int pageSize)
    {
        int postTotal = postDao.selectCount();
        int pageTotal = postTotal / pageSize;
        if (postTotal % pageSize != 0) {
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

    public int removePostById(int id)
    {
        return postDao.deleteById(id);
    }
}
