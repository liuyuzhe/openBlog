package com.strongliu.blog.controller.admin;

import com.strongliu.blog.constant.ErrorCode;
import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.manager.CategoryManager;
import com.strongliu.blog.manager.PostManager;
import com.strongliu.blog.manager.TagManager;
import com.strongliu.blog.validator.PostFormValidator;
import com.strongliu.blog.vo.PostFormVo;
import com.strongliu.blog.vo.PostPageVo;
import com.strongliu.blog.vo.PostVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/post")
public class PostController extends BaseController {

	@Autowired
	private PostManager postManager;
	@Autowired
	private CategoryManager categoryManager;
	@Autowired
	private TagManager tagManager;
	@Autowired
	private PostFormValidator postFormValidator;

	private final static Logger logger = LoggerFactory.getLogger(PostController.class);

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId,
						@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
						Model model) {
		try {
			PostPageVo postPageVo = postManager.getPostPageVo(pageId, limit);
			if (!ObjectUtils.isEmpty(postPageVo)) {
				model.addAttribute(postPageVo);
			}
		} catch (Exception e) {
			logger.error(e.toString());
			return this.render_500();
		}

		return this.renderAdmin("post_list");
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String newPost(Model model) {
		try {
			List<Category> categoryList = categoryManager.getAllCategory();
			if (!ObjectUtils.isEmpty(categoryList)) {
				model.addAttribute(categoryList);
			}

			List<Tag> tagList = tagManager.getAllTag();
			if (!ObjectUtils.isEmpty(tagList)) {
				model.addAttribute(tagList);
			}
		} catch (Exception e) {
			logger.error(e.toString());
			return this.render_500();
		}

		return this.renderAdmin("post_edit");
	}

	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public String editPost(@PathVariable Integer postId, Model model) {
		try {
			PostVo postVo = postManager.getPostVo(postId);
			if (ObjectUtils.isEmpty(postVo)) {
				return this.render_404();
			}

			model.addAttribute(postVo);

			List<Category> categoryList = categoryManager.getAllCategory();
			if (!ObjectUtils.isEmpty(categoryList)) {
				model.addAttribute(categoryList);
			}

			List<Tag> tagList = tagManager.getAllTag();
			if (!ObjectUtils.isEmpty(tagList)) {
				model.addAttribute(tagList);
			}
		} catch (Exception e) {
			logger.error(e.toString());
			return this.render_500();
		}

		return this.renderAdmin("post_edit");
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto savePost(PostFormVo postFormVo, Errors errors) {
		postFormValidator.validate(postFormVo, errors);
		if (errors.hasErrors()) {
			return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
		}

		try {
			postManager.addPostFormVo(postFormVo);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
		}

		return new ResponseDto(ErrorCode.SUCCESS);
	}

	@RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.POST})
	@ResponseBody
	public ResponseDto updatePost(PostFormVo postFormVo, Errors errors) {
		postFormValidator.validate(postFormVo, errors);
		if (errors.hasErrors()) {
			return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
		}

		try {
			postManager.updatePostFormVo(postFormVo);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
		}

		return new ResponseDto(ErrorCode.SUCCESS);
	}

	@RequestMapping(value = "/remove", method = {RequestMethod.DELETE, RequestMethod.POST})
	@ResponseBody
	public ResponseDto deletePost(@RequestParam Integer postId) {
		try {
			postManager.removePostForm(postId);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
		}

		return new ResponseDto(ErrorCode.SUCCESS);
	}

}
