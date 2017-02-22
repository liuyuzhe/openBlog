package com.strongliu.blog.dao;

import com.strongliu.blog.entity.LoginInfo;
import com.strongliu.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/13.
 */
public interface UserDao {

    User selectByLoginInfo(LoginInfo info);

    User selectById(String id);

    List<User> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    int selectCount();

    int insert(User user);

    int update(User user);

    int deleteById(String id);
}
