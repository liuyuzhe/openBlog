package com.strongliu.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strongliu.blog.dao.PostDao;
import com.strongliu.blog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	PostDao postMapper;
	
	public PostVo findPostById(String id) {
		return postMapper.selectById(id);
	}
}
