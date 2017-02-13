package com.strongliu.blog.controller;

import java.util.List;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    PostService postService;

	@RequestMapping(value="/{pageId}", method=RequestMethod.GET)
	public String currentPage(@PathVariable int pageId, Model model) {
		List<Post> postList = postService.findAllPublishPost(pageId, Constant.PAGE_SIZE);
		if (postList == null) {
			return "404";
		}

		int totalPage = postService.totalPage(Constant.PAGE_SIZE);

		model.addAttribute(postList);
		model.addAttribute(totalPage);

		return "page";
	}
}
