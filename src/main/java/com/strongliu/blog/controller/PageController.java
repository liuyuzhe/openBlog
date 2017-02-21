package com.strongliu.blog.controller;

import com.strongliu.blog.manager.PageManager;
import com.strongliu.blog.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
