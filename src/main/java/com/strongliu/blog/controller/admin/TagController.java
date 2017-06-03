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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */

@Controller
@RequestMapping(value = "admin/tag")
public class TagController extends BaseController {

    @Autowired
    private TagManager tagManager;

    private final static Logger logger = LoggerFactory.getLogger(TagController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(Model model) {
        try {
            List<Tag> tagList = tagManager.getAllTag();
            if (ObjectUtils.isEmpty(tagList)) {
                return this.render_404();
            }

            model.addAttribute(tagList);
        } catch (Exception e) {
            logger.error(e.toString());
            return this.render_500();
        }

        return this.renderAdmin("tag");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto saveTag(Tag tag) {
        if (ObjectUtils.isEmpty(tag)) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        try {
            tagManager.addTag(tag);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDto updateTag(Tag tag) {
        if (ObjectUtils.isEmpty(tag)) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        try {
            tagManager.updateCategory(tag);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDto deleteTag(@PathVariable Integer tagId) {
        try {
            tagManager.removeCategory(tagId);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }
}
