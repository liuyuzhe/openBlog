package com.strongliu.blog.controller;

import java.util.List;

import com.strongliu.blog.entity.Post;
import com.strongliu.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    PostService postService;

//	@RequestMapping(value="/{pageId}", method=RequestMethod.GET)
//	public List<Post> pages(@PathVariable int pageId) {
//		int start = (pageId - 1) * PAGE_SIZE;
//		List<Post> blogs = postService.findBlogs(start, start + PAGE_SIZE);
//
//		return blogs;
//	}
}
