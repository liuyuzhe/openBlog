package com.strongliu.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.strongliu.blog.entity.Post;

public interface PostDao {
	
	Post selectById(String id);
	
	Post selectPrevById(String id);
	
	Post selectNextById(String id);

	List<Post> selectAllPublish(@Param("offset") int offset, @Param("limit") int limit);

//	List<Post> selectAllByCategoryId(String categoryId);

	List<Post> selectAllPublishByCategoryId(@Param("categoryId") String categoryId, @Param("offset") int offset, @Param("limit") int limit);

//	List<Post> selectAllByTagId(String tagId);

	int selectCount();

	int updateReadCount(@Param("id") String id, @Param("count") int count);

	int updateCommentCount(@Param("id") String id, @Param("count") int count);

//	void updateCategory(@Param("oldCategoryIds") List<String> oldCategoryIds, @Param("newCategoryId") List<String> newCategoryIds);
}
