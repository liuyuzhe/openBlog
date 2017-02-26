package com.strongliu.blog.dao;

import com.strongliu.blog.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostDao {

	Post selectPublishById(String id);

	Post selectPublishPrevById(String id);

	Post selectPublishNextById(String id);

	List<Post> selectAllPublish(@Param("offset") int offset, @Param("limit") int limit);

	List<Post> selectAllPublishByIdList(@Param("idList") List<String> idList, @Param("offset") int offset, @Param("limit") int limit);

	int selectCount();

	int insert(Post post);

	int update(Post post);

	int deleteById(String id);

//	int updateReadCount(@Param("id") String id, @Param("count") int count);

//	int updateCommentCount(@Param("id") String id, @Param("count") int count);
}
