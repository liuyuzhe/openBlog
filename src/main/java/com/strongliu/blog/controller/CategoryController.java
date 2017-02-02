package com.strongliu.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@RequestMapping(value="/{categoryName}", method=RequestMethod.GET)
	public String category(@PathVariable String categoryName) {
		return categoryPage(categoryName, 1);
	}
	
	@RequestMapping(value="/{categoryName}/page/{pageId}", method=RequestMethod.GET)
	public String categoryPage(@PathVariable String categoryName, @PathVariable int pageId) {
        return "";
	}
	
}
