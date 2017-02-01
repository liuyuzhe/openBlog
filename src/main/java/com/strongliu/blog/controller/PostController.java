package com.strongliu.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.strongliu.blog.service.PostService;
import com.strongliu.blog.vo.PostVo;

@Controller
@RequestMapping(value="/{postId}", method=RequestMethod.GET)
public class PostController {
	@Autowired
	PostService postService;
	
	public String post(@PathVariable String postId, Model model) {
		PostVo post = postService.findPostById(postId);
		if (post == null) {
			return "404";
		}
		
		model.addAttribute("post", post);
		
		return "post";
	}

}
