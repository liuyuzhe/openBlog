package com.strongliu.blog.manager;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.service.PostService;
import com.strongliu.blog.service.RelationshipService;
import com.strongliu.blog.service.TagService;
import com.strongliu.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */

@Component
public class TagManager {

    @Autowired
    private TagService tagService;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private PostService postService;

    @Autowired
    private TagVo tagVo;

    @Transactional
    public TagVo getTagVoByTagName(String tagName, int pageId) {
        Tag tag = tagService.findTagByName(tagName);
        if (tag == null) {
            return null;
        }

        List<String> targetList = relationshipService.findAllTargetByTermId(tag.getId());
        List<Post> postList = postService.findAllPublishPostByIdList(targetList, pageId, Constant.PAGE_SIZE);

        int pageTotal = targetList.size() / Constant.PAGE_SIZE;
        if (targetList.size() % Constant.PAGE_SIZE != 0) {
            pageTotal += 1;
        }

        tagVo.setTag(tag);
        tagVo.setPostList(postList);
        tagVo.setPageIndex(pageId);
        tagVo.setPageTotal(pageTotal);

        return tagVo;
    }
}
