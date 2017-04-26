package com.strongliu.blog.controller.user;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.manager.CategoryManager;
import com.strongliu.blog.manager.PostManager;
import com.strongliu.blog.manager.TagManager;
import com.strongliu.blog.vo.CategoryVo;
import com.strongliu.blog.vo.PostPageVo;
import com.strongliu.blog.vo.PostVo;
import com.strongliu.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/10.
 */

@Controller
public class IndexController extends BaseController {

    @Autowired
    private PostManager postManager;
    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private TagManager tagManager;

    /**
     * 首页
     */
    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId, Model model) {
        PostPageVo postPageVo = postManager.getPostPageVoByPageId(pageId);
        List<Category> categoryList = categoryManager.getAllCategory();
        List<Tag> tagList = tagManager.getAllTag();

        model.addAttribute(postPageVo);
        model.addAttribute(categoryList);
        model.addAttribute(tagList);

        return this.renderUser("index");
    }

    /**
     * 文章页
     */
    @RequestMapping(value = "posts/{postId}", method = RequestMethod.GET)
    public String posts(@PathVariable String postId, Model model) {
        PostVo postVo = postManager.getPostVoByPostId(postId);
        if (postVo == null) {
            return this.render_404();
        }

        model.addAttribute(postVo);

        return this.renderUser("post");
    }

    /**
     * 分类页
     */
    @RequestMapping(value = "categories/{keyword}", method = RequestMethod.GET)
    public String categories(@PathVariable String keyword, @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId,
                             @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit, Model model) {
        CategoryVo categoryVo = categoryManager.getCategoryVoByCategoryName(keyword, pageId, limit);
        if (categoryVo == null) {
            return this.render_404();
        }

        model.addAttribute(categoryVo);

        return this.renderUser("category");
    }

    /**
     * 标签页
     */
    @RequestMapping(value = "tags/{keyword}", method = RequestMethod.GET)
    public String tags(@PathVariable String keyword, @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit, Model model) {
        TagVo tagVo = tagManager.getTagVoByTagName(keyword, pageId, limit);
        if (tagVo == null) {
            return this.render_404();
        }

        model.addAttribute(tagVo);

        return this.renderUser("tag");
    }

}
