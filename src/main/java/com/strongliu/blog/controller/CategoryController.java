package com.strongliu.blog.controller;

import com.strongliu.blog.entity.Category;
import com.strongliu.blog.manager.CategoryManager;
import com.strongliu.blog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryManager categoryManager;

	@RequestMapping(value="/{categoryName}", method=RequestMethod.GET)
	public String index(@PathVariable String categoryName, Model model) {
		return indexWithPage(categoryName, 1, model);
	}
	
	@RequestMapping(value="/{categoryName}/page/{pageId}", method=RequestMethod.GET)
	public String indexWithPage(@PathVariable String categoryName, @PathVariable int pageId, Model model) {
		CategoryVo categoryVo = categoryManager.getCategoryVoByCategoryName(categoryName, pageId);
		if (categoryVo == null) {
			return "404";
		}

		model.addAttribute(categoryVo);

        return "category";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String add(Category category) {
		return "";
	}

	@RequestMapping(value="/{categoryName}", method = RequestMethod.DELETE)
	public String remove(@PathVariable String categoryName) {
		return "";
	}

}
