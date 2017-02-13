package com.strongliu.blog.controller;

import com.strongliu.blog.entity.Post;
import com.strongliu.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/10.
 */

@Controller
public class IndexController {

    @Autowired
    PostService postService;

    private static final int PAGE_SIZE = 10;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        List<Post> postList = postService.findAllPublishPost(page, PAGE_SIZE);
        if (postList == null) {
            return "404";
        }

        model.addAttribute(postList);

        return "index";
    }
}
