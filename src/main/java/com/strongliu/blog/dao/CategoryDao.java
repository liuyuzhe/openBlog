package com.strongliu.blog.dao;

import java.util.List;

import com.strongliu.blog.entity.Category;

public interface CategoryDao {
	
	public Category selectById(String id);

	public List<Category> selectAll();
}
