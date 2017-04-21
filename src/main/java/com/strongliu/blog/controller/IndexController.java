package com.strongliu.blog.controller;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.manager.CategoryManager;
import com.strongliu.blog.manager.PostManager;
import com.strongliu.blog.manager.TagManager;
import com.strongliu.blog.vo.PostPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/10.
 */

@Controller
public class IndexController {

    @Autowired
    private PostManager postManager;
    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private TagManager tagManager;

    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        PostPageVo postPageVo = postManager.getPostPageVoByPageId(Constant.PAGE_INDEX_DEFAULT);
        List<Category> categoryList = categoryManager.getAllCategory();
        List<Tag> tagList = tagManager.getAllTag();

        model.addAttribute(postPageVo);
        model.addAttribute(categoryList);
        model.addAttribute(tagList);

        return "user/index";
    }

}
