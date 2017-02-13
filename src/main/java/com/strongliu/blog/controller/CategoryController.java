package com.strongliu.blog.controller;

import com.strongliu.blog.entity.Post;
import com.strongliu.blog.service.CategoryService;
import com.strongliu.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	PostService postService;

	@RequestMapping(value="/{categoryName}", method=RequestMethod.GET)
	public String category(@PathVariable String categoryName, Model model) {
		return categoryPage(categoryName, 1, model);
	}
	
	@RequestMapping(value="/{categoryName}/page/{pageId}", method=RequestMethod.GET)
	public String categoryPage(@PathVariable String categoryName, @PathVariable int pageId, Model model) {
//		List<Post> postList = postService.findAllPostByCategoryPage(categoryName, pageId);
//		if (postList == null) {
//			return "404";
//		}
//
//		model.addAttribute(postList);

        return "category";
	}
	
}
