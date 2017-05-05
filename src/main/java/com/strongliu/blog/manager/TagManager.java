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

    /**
     * 获取该标签下的文章列表
     */
    @Transactional
    public TagVo getTagVo(String slug, int pageId, int limit) {
        Tag tag = tagService.findTagBySlug(slug);
        if (tag == null) {
            return null;
        }

        List<Integer> targetList = relationshipService.findAllTargetByTermId(tag.getId());
        List<Post> postList = postService.findAllPublishPostByIdList(targetList, pageId, limit);

        int pageTotal = targetList.size() / limit;
        if (targetList.size() % limit != 0) {
            pageTotal += 1;
        }

        TagVo tagVo = new TagVo();
        tagVo.setTag(tag);
        tagVo.setPostList(postList);
        if (pageId > 0 && pageTotal > 0) {
            tagVo.setPageIndex(pageId);
            tagVo.setPageTotal(pageTotal);
        }

        return tagVo;
    }

    /**
     * 获取所有标签
     */
    public List<Tag> getAllTag() {
        return tagService.findAllTag();
    }

    public int addTag(Tag tag) {
        return tagService.addTag(tag);
    }

    public int updateCategory(Tag tag) {
        return tagService.updateTag(tag);
    }

    public int removeCategory(int tagId) {
        return tagService.removeTagById(tagId);
    }

}
