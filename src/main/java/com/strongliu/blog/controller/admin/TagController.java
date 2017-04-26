package com.strongliu.blog.controller.admin;

import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.manager.TagManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuyuzhe on 2017/2/19.
 */

@Controller
@RequestMapping(value = "admin/tag")
public class TagController extends BaseController {

    @Autowired
    private TagManager tagManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return this.renderAdmin("tag");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto saveTag(Tag tag) {
        if (tag == null) {
            return "redirect:" + "/";
        }

        tagManager.addTag(tag);

        return "redirect:" + "/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDto updateTag(Tag tag) {
        if (tag == null) {
            return "redirect:" + "/";
        }

        tagManager.updateCategory(tag);

        return "redirect:" + "/";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDto deleteTag(@PathVariable Integer tagId) {
        tagManager.removeCategory(tagId);

        return "redirect:" + "/";
    }
}
