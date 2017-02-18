package com.strongliu.blog.dao;

import java.util.List;

import com.strongliu.blog.entity.Category;

public interface CategoryDao {

	public Category selectById(int id);

	public Category selectByName(String name);

	public List<Category> selectAll();
}
