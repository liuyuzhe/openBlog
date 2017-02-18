package com.strongliu.blog.controller;

import com.strongliu.blog.entity.Post;
import com.strongliu.blog.service.CategoryService;
import com.strongliu.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;

	@RequestMapping(value="/{categoryName}", method=RequestMethod.GET)
	public String category(@PathVariable String categoryName) {
		return categoryPage(categoryName, 1);
	}
	
	@RequestMapping(value="/{categoryName}/page/{pageId}", method=RequestMethod.GET)
	public String categoryPage(@PathVariable String categoryName, @PathVariable int pageId) {
		try {
		}
		catch (Exception e)
		{

		}

//		List<Post> postList = postService.findAllPostByCategoryPage(categoryName, pageId);

        return "category";
	}
	
}
