package com.strongliu.blog.dao;

import java.util.List;

import com.strongliu.blog.entity.Category;

public interface CategoryDao {

	Category selectById(Integer id);

	Category selectByName(String name);

	List<Category> selectAllByIdList(List<Integer> idList);

	List<Category> selectAll();

	int insert(Category category);

	int update(Category category);

	int deleteById(Integer id);
}
