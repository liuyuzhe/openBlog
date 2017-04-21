package com.strongliu.blog.controller;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.manager.CategoryManager;
import com.strongliu.blog.manager.PostManager;
import com.strongliu.blog.manager.TagManager;
import com.strongliu.blog.validator.PostFormValidator;
import com.strongliu.blog.vo.PostFormVo;
import com.strongliu.blog.vo.PostPageVo;
import com.strongliu.blog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostManager postManager;
	@Autowired
	private CategoryManager categoryManager;
	@Autowired
	private TagManager tagManager;
	@Autowired
	private PostFormValidator postFormValidator;

	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public String indexPost(@PathVariable String postId, Model model) {
		PostVo postVo = postManager.getPostVoByPostId(postId);
		if (postVo == null) {
			return  "404";
		}

		model.addAttribute(postVo);

		return "post/post";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPostWithPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId, Model model) {
		PostPageVo postPageVo = postManager.getPostPageVoByPageId(Constant.PAGE_INDEX_DEFAULT);
		List<Category> categoryList = categoryManager.getAllCategory();
		List<Tag> tagList = tagManager.getAllTag();

		model.addAttribute(postPageVo);
		model.addAttribute(categoryList);
		model.addAttribute(tagList);

		return "page";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String inputPost(Model model) {
		model.addAttribute(new Post());

		return "post/createPage";
	}

	@RequestMapping(value = "/edit/{postId}", method = RequestMethod.GET)
	public String editPost(@PathVariable String postId, Model model) {
		PostVo postVo = postManager.getPostVoByPostId(postId);

		model.addAttribute(postVo);

		return "post/createPage";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String savePost(PostFormVo postFormVo, Errors errors) {
		postFormValidator.validate(postFormVo, errors);
		if (errors.hasErrors()) {
			return "redirect:" + "/post/createPage";
		}

		String postId = postManager.addPostFormVo(postFormVo);

		return "redirect:" + "/post/" + postId;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String updatePost(PostFormVo postFormVo, Errors errors) {
		postFormValidator.validate(postFormVo, errors);
		if (errors.hasErrors()) {
			return "redirect:" + "/post/createPage";
		}

		String postId = postManager.updatePostFormVo(postFormVo);

		return "redirect:" + "/post/" + postId;
	}

	@RequestMapping(value = "/remove/{postId}", method = RequestMethod.DELETE)
	public String deletePost(@PathVariable String postId) {
		postManager.removePostForm(postId);

		return "redirect:" + "/";
	}

}
