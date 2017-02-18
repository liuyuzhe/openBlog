package com.strongliu.blog.controller;

import com.strongliu.blog.Manager.PostManager;
import com.strongliu.blog.constant.ErrorMessage;
import com.strongliu.blog.service.PostService;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.strongliu.blog.entity.Post;

@Controller
public class PostController {

	@Autowired
	private PostManager postManager;

	@RequestMapping(value="/{postId}", method=RequestMethod.GET)
	public String currentPost(@PathVariable String postId, Model model) {
		PostVo postVo = postManager.getPostVoByPostId(postId);
		if (postVo == null) {
			return  "404";
		}

		model.addAttribute(postVo);
		return "post";
	}

}
