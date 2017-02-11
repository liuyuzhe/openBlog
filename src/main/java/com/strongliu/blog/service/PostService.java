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

    private static int PAGE_SIZE = 10;

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

    public List<Post> findAllPostByPageId(int pageId)
    {
        int startIndex = (pageId - 1) * PAGE_SIZE;
        return postDao.selectAllByPage(startIndex, PAGE_SIZE);
    }

    public List<Post> findAllPostByCategoryPage(String categoryName, int pageId)
    {
        int startIndex = (pageId - 1) * PAGE_SIZE;
        return postDao.selectAllByCategoryPage(categoryName, startIndex, PAGE_SIZE);
    }

    public int totalPage()
    {
        int totalPage = postDao.count() / PAGE_SIZE;
        if (totalPage % PAGE_SIZE != 0) {
            totalPage += 1;
        }

        return totalPage;
    }

}
