package com.strongliu.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.strongliu.blog.dto.Blog;

@Controller
@RequestMapping("/blog")
public class BlogController {

	private static final int PAGE_SIZE = 10;
	
	@RequestMapping(value="/create", method=RequestMethod.PUT)
	public String createBlog(Blog blog) {
		return "create";
		
		return "redirect/blog";
	}
	
	@RequestMapping(value="/{blogId}", method=RequestMethod.GET)
	public String getBlog(@PathVariable int blogId, Model model) {
		Blog blog = blogService.findBlog(blogId);
		if (blog == null) {
			return "error";
		}
		
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String updateBlog() {
		
	}
	
	@RequestMapping(value="", method=RequestMethod.DELETE)
	public String deleteBlog() {
		
	}
}
