package com.strongliu.blog.controller.admin;

import com.strongliu.blog.constant.ErrorCode;
import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.manager.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("admin/category")
public class CategoryController extends BaseController {

	@Autowired
	CategoryManager categoryManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		List<Category> categoryList = categoryManager.getAllCategory();

		model.addAttribute(categoryList);

		return this.renderAdmin("category");
	}

	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto saveCategory(Category category) {
		if (category == null) {
			return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
		}

		try {
			categoryManager.addCategory(category);
			return new ResponseDto(ErrorCode.SUCCESS);
		}
		catch (Exception e) {
			return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseDto updateCategory(Category category) {
		if (category == null) {
			return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
		}

		try {
			categoryManager.updateCategory(category);
			return new ResponseDto(ErrorCode.SUCCESS);
		}
		catch (Exception e) {
			return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
		}
	}

	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseDto deleteCategory(@PathVariable Integer categoryId) {
		try {
			categoryManager.removeCategory(categoryId);
			return new ResponseDto(ErrorCode.SUCCESS);
		}
		catch (Exception e) {
			return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
		}
	}
}
