package com.strongliu.blog.controller;

import java.util.List;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.constant.ErrorMessage;
import com.strongliu.blog.dto.ResponseListDto;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    PostService postService;

	@Autowired
	ResponseListDto responseListDto;

	@RequestMapping(value="/{pageId}", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Object currentPage(@PathVariable int pageId) {
		try {
			List<Post> postList = postService.findAllPublishPost(pageId, Constant.PAGE_SIZE);
			int totalPage = postService.totalPage(Constant.PAGE_SIZE);
			if (postList == null) {
				responseListDto.setCode(ErrorMessage.FAILED.getCode());
				responseListDto.setMessage(ErrorMessage.FAILED.getMessage());
				return responseListDto;
			}

			responseListDto.setCode(ErrorMessage.SUCCESS.getCode());
			responseListDto.setMessage(ErrorMessage.SUCCESS.getMessage());
			responseListDto.setTotalPage(totalPage);
			responseListDto.setData(postList);
			return responseListDto;
		}
		catch (Exception e) {
			e.printStackTrace();

			responseListDto.setCode(ErrorMessage.EXCEPTION.getCode());
			responseListDto.setMessage(ErrorMessage.EXCEPTION.getMessage());
		}

		return responseListDto;
	}
}
