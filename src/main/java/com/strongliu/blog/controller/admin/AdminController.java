package com.strongliu.blog.controller.admin;

import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.manager.CategoryManager;
import com.strongliu.blog.manager.TagManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/4/26.
 */

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private TagManager tagManager;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return this.renderAdmin("index");
    }

    @RequestMapping(value = {"/term", "/term/"}, method = RequestMethod.GET)
    public String term(Model model) {
        try {
            List<Category> categoryList = categoryManager.getAllCategory();
            if (!ObjectUtils.isEmpty(categoryList)) {
                model.addAttribute(categoryList);
            }

            List<Tag> tagList = tagManager.getAllTag();
            if (!ObjectUtils.isEmpty(tagList)) {
                model.addAttribute(tagList);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return this.render_500();
        }

        return this.renderAdmin("term");
    }
}
