package com.strongliu.blog.controller.admin;

import com.strongliu.blog.constant.ErrorCode;
import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.manager.CategoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController extends BaseController {

	@Autowired
	CategoryManager categoryManager;

	private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String index(Model model) {
		try {
			List<Category> categoryList = categoryManager.getAllCategory();
			if (ObjectUtils.isEmpty(categoryList)) {
				return this.render_404();
			}

			model.addAttribute(categoryList);
		} catch (Exception e) {
			logger.error(e.toString());
			return this.render_500();
		}

		return this.renderAdmin("category");
	}

	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto saveCategory(Category category) {
		if (ObjectUtils.isEmpty(category)) {
			return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
		}

		try {
			categoryManager.addCategory(category);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
		}

		return new ResponseDto(ErrorCode.SUCCESS);
	}

	@RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.POST})
	@ResponseBody
	public ResponseDto updateCategory(Category category) {
		if (ObjectUtils.isEmpty(category)) {
			return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
		}

		try {
			categoryManager.updateCategory(category);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
		}

		return new ResponseDto(ErrorCode.SUCCESS);
	}

	@RequestMapping(value = "/remove", method = {RequestMethod.DELETE, RequestMethod.POST})
	@ResponseBody
	public ResponseDto deleteCategory(@PathVariable Integer categoryId) {
		try {
			categoryManager.removeCategory(categoryId);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
		}

		return new ResponseDto(ErrorCode.SUCCESS);
	}
}
