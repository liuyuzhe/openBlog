package com.strongliu.blog.Manager;

import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.service.PostService;
import com.strongliu.blog.service.TagService;
import com.strongliu.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuyuzhe on 2017/2/19.
 */

@Component
public class TagManager {

    @Autowired
    private TagService tagService;
    @Autowired
    private PostService postService;

    @Autowired
    private TagVo tagVo;

    // TODO:tag
    public TagVo getTagVoByTagName(String tagName, int pageId) {
        Tag tag = tagService.findTagByName(tagName);
        if (tag == null) {
            return null;
        }

        tagVo.setTag(tag);

        return tagVo;
    }
}
