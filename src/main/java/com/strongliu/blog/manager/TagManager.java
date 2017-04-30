package com.strongliu.blog.manager;

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

    /**
     * 获取该标签下的文章列表
     */
    @Transactional
    public TagVo getTagVo(String keyword, int pageId, int limit) {
        Tag tag = tagService.findTagBySlug(keyword);
        if (tag == null) {
            return null;
        }

        List<String> targetList = relationshipService.findAllTargetByTermId(tag.getId());
        List<Post> postList = postService.findAllPublishPostByIdList(targetList, pageId, limit);

        int pageTotal = targetList.size() / limit;
        if (targetList.size() % limit != 0) {
            pageTotal += 1;
        }

        tagVo.setTag(tag);
        tagVo.setPostList(postList);
        tagVo.setPageIndex(pageId);
        tagVo.setPageTotal(pageTotal);

        return tagVo;
    }

    /**
     * 获取所有标签
     */
    @Transactional
    public List<Tag> getAllTag() {
        return tagService.findAllTag();
    }

    @Transactional
    public int addTag(Tag tag) {
        return tagService.addTag(tag);
    }

    @Transactional
    public int updateCategory(Tag tag) {
        return tagService.updateTag(tag);
    }

    @Transactional
    public int removeCategory(Integer tagId) {
        return tagService.removeTagById(tagId);
    }

}
