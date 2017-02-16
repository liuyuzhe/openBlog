package com.strongliu.blog.service;

import com.strongliu.blog.dao.CategoryDao;
import com.strongliu.blog.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/10.
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public Category findCategoryById(String id)
    {
        return categoryDao.selectById(id);
    }

    public List<Category> findAllCategory()
    {
        return categoryDao.selectAll();
    }
}
