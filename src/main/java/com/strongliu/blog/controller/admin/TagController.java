package com.strongliu.blog.controller.admin;

import com.strongliu.blog.constant.ErrorCode;
import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.manager.TagManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<Tag> tagList = tagManager.getAllTag();

        model.addAttribute(tagList);

        return this.renderAdmin("tag");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto saveTag(Tag tag) {
        if (tag == null) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        try {
            tagManager.addTag(tag);
            return new ResponseDto(ErrorCode.SUCCESS);
        }
        catch (Exception e) {
            return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDto updateTag(Tag tag) {
        if (tag == null) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_INVALID);
        }

        try {
            tagManager.updateCategory(tag);
            return new ResponseDto(ErrorCode.SUCCESS);
        }
        catch (Exception e) {
            return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDto deleteTag(@PathVariable Integer tagId) {
        try {
            tagManager.removeCategory(tagId);
            return new ResponseDto(ErrorCode.SUCCESS);
        }
        catch (Exception e) {
            return new ResponseDto(ErrorCode.ERROR_DB_FAILED);
        }
    }
}
