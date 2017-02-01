package com.strongliu.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.strongliu.blog.dto.Blog;

@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping(value="/{pageId}", method=RequestMethod.GET)
	public List<Blog> pages(@PathVariable int pageId) {
		int start = (page - 1) * PAGE_SIZE;
		List<Blog> blogs = blogService.findBlogs(start, start + PAGE_SIZE);
		
		return blogs;
	}
}
