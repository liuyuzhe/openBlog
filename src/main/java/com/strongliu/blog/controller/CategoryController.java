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

	@RequestMapping(value = "/{categoryName}", method = RequestMethod.GET)
	public String indexCategory(@PathVariable String categoryName, Model model) {
		return indexCategoryWithPage(categoryName, 1, model);
	}
	
	@RequestMapping(value = "/{categoryName}/page/{pageId}", method = RequestMethod.GET)
	public String indexCategoryWithPage(@PathVariable String categoryName, @PathVariable int pageId, Model model) {
		CategoryVo categoryVo = categoryManager.getCategoryVoByCategoryName(categoryName, pageId);
		if (categoryVo == null) {
			return "404";
		}

		model.addAttribute(categoryVo);

        return "category";
	}

	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public String saveCategory(Category category) {
		if (category == null) {
			return "redirect:" + "/";
		}

		categoryManager.addCategory(category);

		return "redirect:" + "/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String updateCategory(Category category) {
		if (category == null) {
			return "redirect:" + "/";
		}

		categoryManager.updateCategory(category);

		return "redirect:" + "/";
	}

	@RequestMapping(value = "/remove/{categoryId}", method = RequestMethod.DELETE)
	public String deleteCategory(@PathVariable Integer categoryId) {
		categoryManager.removeCategory(categoryId);

		return "redirect:" + "/";
	}

}
