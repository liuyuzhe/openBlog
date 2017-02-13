package com.strongliu.blog.controller;

import com.strongliu.blog.constant.ErrorMessage;
import com.strongliu.blog.service.PostService;
import com.strongliu.blog.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.strongliu.blog.entity.Post;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

	@Autowired
	PostService postService;
	ResponseDto responseDto;

	@RequestMapping(value="/{postId}", method=RequestMethod.GET)
	@ResponseBody
	public Object getPostById(@PathVariable String postId) {
		try {
			Post post = postService.findPostById(postId);
			if (post == null) {
				responseDto.setCode(ErrorMessage.FAILED.getCode());
				responseDto.setMessage(ErrorMessage.FAILED.getMessage());
			}

			responseDto.setCode(ErrorMessage.SUCCESS.getCode());
			responseDto.setMessage(ErrorMessage.SUCCESS.getMessage());
			responseDto.setData(post);

			return responseDto;
		}
		catch (Exception e) {
			e.printStackTrace();

			responseDto.setCode(ErrorMessage.EXCEPTION.getCode());
			responseDto.setMessage(ErrorMessage.EXCEPTION.getMessage());

			return responseDto;
		}
	}

}
