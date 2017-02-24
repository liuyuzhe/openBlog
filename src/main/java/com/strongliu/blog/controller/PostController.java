package com.strongliu.blog.controller;

import com.strongliu.blog.entity.Post;
import com.strongliu.blog.manager.PostManager;
import com.strongliu.blog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostManager postManager;

	@RequestMapping(value="/{postId}", method=RequestMethod.GET)
	public String index(@PathVariable String postId, Model model) {
		PostVo postVo = postManager.getPostVoByPostId(postId);
		if (postVo == null) {
			return  "404";
		}

		model.addAttribute(postVo);
		return "post";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String add(Post post) {
		return "post/add";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String update(Post post) {
		return "";
	}

	@RequestMapping(value = "/{postId}", method = RequestMethod.DELETE)
	public String remove(@PathVariable String postId) {
		return "";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit() {
		return "";
	}

}
