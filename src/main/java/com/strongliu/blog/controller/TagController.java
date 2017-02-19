package com.strongliu.blog.controller;

import com.strongliu.blog.Manager.TagManager;
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
    public String currentTag (@PathVariable String tagName , Model model) {
        return tagWithPage(tagName, 1, model);
    }

    @RequestMapping(value = "/{tagName}/page/{pageId}", method = RequestMethod.GET)
    public String tagWithPage(@PathVariable String tagName, @PathVariable int pageId, Model model) {
        TagVo tagVo = tagManager.getTagVoByTagName(tagName, pageId);
        if (tagVo == null) {
            return "404";
        }

        model.addAttribute(tagVo);

        return "tag";
    }
}
