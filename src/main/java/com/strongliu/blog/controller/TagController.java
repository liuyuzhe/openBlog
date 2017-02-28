package com.strongliu.blog.controller;

import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.manager.TagManager;
import com.strongliu.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liuyuzhe on 2017/2/19.
 */

@Controller
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    private TagManager tagManager;

    @RequestMapping(value = "/{tagName}", method = RequestMethod.GET)
    public String indexTag (@PathVariable String tagName , Model model) {
        return indexTagWithPage(tagName, 1, model);
    }

    @RequestMapping(value = "/{tagName}/page/{pageId}", method = RequestMethod.GET)
    public String indexTagWithPage(@PathVariable String tagName, @PathVariable int pageId, Model model) {
        TagVo tagVo = tagManager.getTagVoByTagName(tagName, pageId);
        if (tagVo == null) {
            return "404";
        }

        model.addAttribute(tagVo);

        return "tag";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveTag(Tag tag) {
        if (tag == null) {
            return "redirect:" + "/";
        }

        tagManager.addTag(tag);

        return "redirect:" + "/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateTag(Tag tag) {
        if (tag == null) {
            return "redirect:" + "/";
        }

        tagManager.updateCategory(tag);

        return "redirect:" + "/";
    }

    @RequestMapping(value = "/remove/{tagId}", method = RequestMethod.DELETE)
    public String deleteTag(@PathVariable Integer tagId) {
        tagManager.removeCategory(tagId);

        return "redirect:" + "/";
    }
}
