package com.strongliu.blog.dao;

import com.strongliu.blog.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostDao {

	Post selectById(int id);

	Post selectPublishBySlug(String slug);

	Post selectPublishPrevById(int id);

	Post selectPublishNextById(int id);

	List<Post> selectAll(@Param("offset") int offset, @Param("limit") int limit);

	List<Post> selectAllPublish(@Param("offset") int offset, @Param("limit") int limit);

	List<Post> selectAllPublishByIdList(@Param("idList") List<Integer> idList, @Param("offset") int offset, @Param("limit") int limit);

	int selectCount();

	int insert(Post post);

	int update(Post post);

	int deleteById(int id);

	int updateCommentCount(@Param("id") int id, @Param("count") int count);

	int updateReadCount(@Param("id") int id, @Param("count") int count);

	int updateSpotCount(@Param("id") int id, @Param("count") int count);
}
