package com.strongliu.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.strongliu.blog.entity.Post;

public interface PostDao {
	
	public Post selectById(String id);
	
	public Post selectPrevById(String id);
	
	public Post selectNextById(String id);

	public List<Post> selectAllByPage(int startIndex, int pageSize);

//	public List<Post> selectAllByCategoryId(String categoryId);

	public List<Post> selectAllByCategoryPage(String categoryId, int startIndex, int pageSize);
	
//	public List<Post> selectAllByTagId(String tagId);
	
	public int updateReadCount(@Param("id") String id, @Param("count") int count);
	
	public int updateCommentCount(@Param("id") String id, @Param("count") int count);
	
//	public void updateCategory(@Param("oldCategoryIds") List<String> oldCategoryIds, @Param("newCategoryId") List<String> newCategoryIds);

	public int count();
}
