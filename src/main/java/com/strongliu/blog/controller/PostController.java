package com.strongliu.blog.controller;

import com.strongliu.blog.entity.Post;
import com.strongliu.blog.manager.PostManager;
import com.strongliu.blog.utility.StringUtil;
import com.strongliu.blog.vo.PostFormVo;
import com.strongliu.blog.vo.PostPageVo;
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
	public String postList(@PathVariable int pageId, Model model) {
		PostPageVo postPageVo = postManager.getPostPageVoByPageId(pageId);
		if (postPageVo == null) {
			return "404";
		}

		model.addAttribute(postPageVo);

		return "page";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createPostPage(Model model) {
		model.addAttribute(new Post());

		return "post/createPage";
	}

	@RequestMapping(value = "/edit/{postId}", method = RequestMethod.GET)
	public String editPostPage(@PathVariable String postId, Model model) {
		PostVo postVo = postManager.getPostVoByPostId(postId);

		model.addAttribute(postVo);

		return "post/createPage";
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String createPost(Post post, String categorys, String tags) {
		if (post == null) {
			return "redirect:" + "/create";
		}

		PostFormVo postFormVo = new PostFormVo();
		postFormVo.setPost(post);
		postFormVo.setCategoryIdList(StringUtil.StringToIntegerList(categorys));
		postFormVo.setTagIdList(StringUtil.StringToIntegerList(tags));

		postManager.addPostFormVo(postFormVo);

		return "redirect:" + "/post/" + post.getId();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePost(Post post, String categories, String tags) {
		if (post == null) {
			return "redirect" + "/create";
		}

		PostFormVo postFormVo = new PostFormVo();
		postFormVo.setPost(post);
		postFormVo.setCategoryIdList(StringUtil.StringToIntegerList(categories));
		postFormVo.setTagIdList(StringUtil.StringToIntegerList(tags));

		postManager.updatePostFormVo(postFormVo);

		return "redirect:" + "/post/" + post.getId();
	}

	@RequestMapping(value = "/remove/{postId}", method = RequestMethod.DELETE)
	public String removePost(@PathVariable String postId) {
		postManager.removePostForm(postId);

		return "redirect:" + "/";
	}

}
