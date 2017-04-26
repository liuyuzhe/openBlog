package com.strongliu.blog.controller.admin;

import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.manager.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin/category")
public class CategoryController extends BaseController {

	@Autowired
	CategoryManager categoryManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return this.renderAdmin("category");
	}

	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto saveCategory(Category category) {
		if (category == null) {
			return "redirect:" + "/";
		}

		categoryManager.addCategory(category);

		return "redirect:" + "/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseDto updateCategory(Category category) {
		if (category == null) {
			return "redirect:" + "/";
		}

		categoryManager.updateCategory(category);

		return "redirect:" + "/";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseDto deleteCategory(@PathVariable Integer categoryId) {
		categoryManager.removeCategory(categoryId);

		return "redirect:" + "/";
	}

}
