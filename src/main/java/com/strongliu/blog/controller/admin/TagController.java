package com.strongliu.blog.controller.admin;

import com.strongliu.blog.constant.ErrorCode;
import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.manager.TagManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */

@Controller
@RequestMapping(value = "/admin/tag")
public class TagController extends BaseController {

    @Autowired
    private TagManager tagManager;

    private final static Logger logger = LoggerFactory.getLogger(TagController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(Model model) {
        try {
            List<Tag> tagList = tagManager.getAllTag();
            if (!ObjectUtils.isEmpty(tagList)) {
                model.addAttribute(tagList);
            }

        } catch (Exception e) {
            logger.error(e.toString());
            return this.render_500();
        }

        return this.renderAdmin("tag");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto saveTag(@RequestParam("slug") String slug, @RequestParam("name") String name) {
        if (StringUtils.isEmpty(slug) || StringUtils.isEmpty(name)) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        try {
            tagManager.addTag(slug, name);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public ResponseDto updateTag(@RequestParam("id") Integer tagId, @RequestParam("slug") String slug, @RequestParam("name") String name) {
        if (tagId == null || StringUtils.isEmpty(slug) || StringUtils.isEmpty(name)) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        try {
            tagManager.updateTag(tagId, slug, name);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.DELETE, RequestMethod.POST})
    @ResponseBody
    public ResponseDto deleteTag(@RequestParam("id") Integer tagId) {
        try {
            tagManager.removeCategory(tagId);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }
}
