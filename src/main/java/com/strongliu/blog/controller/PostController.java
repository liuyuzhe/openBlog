package com.strongliu.blog.controller;

import com.strongliu.blog.entity.Post;
import com.strongliu.blog.manager.PostManager;
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

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostManager postManager;
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

	@RequestMapping(value = "/page/{pageId}", method = RequestMethod.GET)
	public String indexPostWithPage(@PathVariable int pageId, Model model) {
		PostPageVo postPageVo = postManager.getPostPageVoByPageId(pageId);
		if (postPageVo == null) {
			return "404";
		}

		model.addAttribute(postPageVo);

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

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String savePost(PostFormVo postFormVo, Errors errors) {
		postFormValidator.validate(postFormVo, errors);
		if (errors.hasErrors()) {
			return "redirect:" + "/post/createPage";
		}

		String postId = postManager.addPostFormVo(postFormVo);

		return "redirect:" + "/post/" + postId;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
