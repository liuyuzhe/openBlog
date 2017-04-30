package com.strongliu.blog.dao;

import com.strongliu.blog.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {

	Category selectById(Integer id);

	Category selectBySlug(String slug);

	List<Category> selectAll();

	List<Category> selectAllByIdList(List<Integer> idList);

	int insert(Category category);

	int update(Category category);

	int updatePostCount(@Param("id") Integer id, @Param("number") int number);

	int deleteById(Integer id);
}
