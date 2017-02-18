package com.strongliu.blog.controller;

import java.util.List;

import com.strongliu.blog.Manager.PageManager;
import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.constant.ErrorMessage;
import com.strongliu.blog.dto.ResponseListDto;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.service.PostService;
import com.strongliu.blog.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
	private PageManager pageManager;

	@RequestMapping(value="/{pageId}", method=RequestMethod.GET)
	public Object currentPage(@PathVariable int pageId, Model model) {
		PageVo pageVo = pageManager.getPageVoByPageId(pageId);
		if (pageVo == null) {
			return "404";
		}

		model.addAttribute(pageVo);

		return "page";
	}
}
