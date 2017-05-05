package com.strongliu.blog.controller.admin;

import com.strongliu.blog.constant.ErrorCode;
import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.manager.PostManager;
import com.strongliu.blog.validator.PostFormValidator;
import com.strongliu.blog.vo.PostFormVo;
import com.strongliu.blog.vo.PostPageVo;
import com.strongliu.blog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/post")
public class PostController extends BaseController {

	@Autowired
	private PostManager postManager;
	@Autowired
	private PostFormValidator postFormValidator;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId,
						@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
						Model model) {
		PostPageVo postPageVo = postManager.getPostPageVo(pageId, limit);

		model.addAttribute(postPageVo);

		return this.renderAdmin("post_list");
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String newPost(Model model) {

		return this.renderAdmin("post_edit");
	}

	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public String editPost(@PathVariable Integer postId, Model model) {
		PostVo postVo = postManager.getPostVo(postId);
		if (postVo == null) {
			return this.render_404();
		}

		model.addAttribute(postVo);

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
			return new ResponseDto(ErrorCode.SUCCESS);
		} catch (Exception e) {
			return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseDto updatePost(PostFormVo postFormVo, Errors errors) {
		postFormValidator.validate(postFormVo, errors);
		if (errors.hasErrors()) {
			return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
		}

		try {
			postManager.updatePostFormVo(postFormVo);
			return new ResponseDto(ErrorCode.SUCCESS);
		} catch (Exception e) {
			return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
		}
	}

	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseDto deletePost(@RequestParam Integer postId) {
		try {
			postManager.removePostForm(postId);
			return new ResponseDto(ErrorCode.SUCCESS);
		} catch (Exception e) {
			return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
		}
	}

}
