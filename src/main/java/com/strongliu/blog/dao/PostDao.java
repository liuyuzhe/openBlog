package com.strongliu.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.strongliu.blog.entity.Post;

public interface PostDao {

	Post selectPublishById(String id);

	Post selectPublishPrevById(String id);

	Post selectPublishNextById(String id);

	List<Post> selectAllPublish(@Param("offset") int offset, @Param("limit") int limit);

	List<Post> selectAllPublishByCategoryId(@Param("categoryId") int categoryId,
											@Param("offset") int offset, @Param("limit") int limit);

	List<Post> selectAllByTagId(@Param("tagId") int tagId,
								@Param("offset") int offset, @Param("limit") int limit);

	int selectCount();

//	int insert(Post post);

//	int update(Post post);

	int updateReadCount(@Param("id") String id, @Param("count") int count);

	int updateCommentCount(@Param("id") String id, @Param("count") int count);

//	void updateCategory(@Param("oldCategoryIds") List<String> oldCategoryIds, @Param("newCategoryId") List<String> newCategoryIds);

//	int deleteById(String id);
}
