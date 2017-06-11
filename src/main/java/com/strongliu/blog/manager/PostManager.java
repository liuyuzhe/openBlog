package com.strongliu.blog.manager;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.entity.Tag;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.service.*;
import com.strongliu.blog.utility.StringUtil;
import com.strongliu.blog.vo.PostFormVo;
import com.strongliu.blog.vo.PostPageVo;
import com.strongliu.blog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/18.
 */

@Component
public class PostManager {

    @Autowired
    private PostService postService;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;

    /**
     * 获取文章列表
     */
    public PostPageVo getPostPageVo(int pageId, int limit) {
        List<Post> postList = postService.findAllPost(pageId, limit);
        if (ObjectUtils.isEmpty(postList)) {
            return null;
        }

        int postTotal = postList.size();
        int pageTotal = postTotal / limit;
        if (postTotal % limit != 0) {
            pageTotal += 1;
        }

        PostPageVo postPageVo = new PostPageVo();
        postPageVo.setPostList(postList);
        if (pageId > 0 && pageTotal > 0) {
            postPageVo.setPageIndex(pageId);
            postPageVo.setPageTotal(pageTotal);
        }

        return postPageVo;
    }

    /**
     * 获取已发布文章列表
     */
    public PostPageVo getPublishPostPageVo(int pageId, int limit) {
        List<Post> postList = postService.findAllPublishPost(pageId, limit);
        if (ObjectUtils.isEmpty(postList)) {
            return null;
        }

        for (Post post : postList) {
            if (post.getFmt_type().equalsIgnoreCase("markdown")) {
                post.setContent(StringUtil.markdownToHtml(post.getContent()));
            }
            post.setExcerpt(StringUtil.generatePostExcerpt(post.getContent(), Constant.POST_EXCERPT_LENGTH));
        }

        int postTotal = postList.size();
        int pageTotal = postTotal / limit;
        if (postTotal % limit != 0) {
            pageTotal += 1;
        }

        PostPageVo postPageVo = new PostPageVo();
        postPageVo.setPostList(postList);
        if (pageId > 0 && pageTotal > 0) {
            postPageVo.setPageIndex(pageId);
            postPageVo.setPageTotal(pageTotal);
        }

        return postPageVo;
    }

    /**
     * 获取文章
     */
    @Transactional
    public PostVo getPostVo(int postId) {
        Post post = postService.findPostById(postId);
        if (ObjectUtils.isEmpty(post)) {
            return null;
        }

        List<Integer> termList = relationshipService.findAllTermByTargetId(post.getId());
        List<Category> categoryList = categoryService.findAllCategoryByIdList(termList);
        List<Tag> tagList = tagService.findAllTagByIdList(termList);

        PostVo postVo = new PostVo();
        postVo.setPost(post);
        postVo.setCategoryList(categoryList);
        postVo.setTagList(tagList);

        return postVo;
    }

    /**
     * 获取已发布文章
     */
    @Transactional
    public PostVo getPublishPostVo(String slug) {
        Post post = postService.findPublishPostBySlug(slug);
        if (ObjectUtils.isEmpty(post)) {
            return null;
        }

        if (post.getFmt_type().equalsIgnoreCase("markdown")) {
            post.setContent(StringUtil.markdownToHtml(post.getContent()));
        }
        post.setExcerpt(StringUtil.generatePostExcerpt(post.getContent(), Constant.POST_EXCERPT_LENGTH));

        List<Integer> termList = relationshipService.findAllTermByTargetId(post.getId());
        List<Category> categoryList = categoryService.findAllCategoryByIdList(termList);
        List<Tag> tagList = tagService.findAllTagByIdList(termList);

        User user = userService.findUserById(post.getCreator_id());
        Post postPrev = postService.findPublishPrevPostById(post.getId());
        Post postNext = postService.findPublishNextPostById(post.getId());

        PostVo postVo = new PostVo();
        postVo.setPost(post);
        postVo.setCategoryList(categoryList);
        postVo.setTagList(tagList);
        postVo.setUser(user);
        postVo.setPostPrev(postPrev);
        postVo.setPostNext(postNext);

        return postVo;
    }

    /**
     * 添加文章
     */
    @Transactional
    public int addPostFormVo(PostFormVo postFormVo) {
        Post post = new Post();
        post.setSlug(postFormVo.getSlug());
        post.setThumb_url(postFormVo.getThumb_url());
        post.setTitle(postFormVo.getTitle());
        post.setContent(postFormVo.getContent());
        Date date = new Date();
        post.setCreate_time(date);
        post.setUpdate_time(date);

        if (StringUtils.isEmpty(postFormVo.getType())) {
            post.setType(Constant.POST_TYPE_DEFAULT);
        } else {
            post.setType(postFormVo.getType());
        }

        post.setFmt_type(postFormVo.getFmt_type());

        if (StringUtils.isEmpty(postFormVo.getStatus())) {
            post.setStatus(Constant.POST_STATUS_DEFAULT);
        } else {
            post.setStatus(postFormVo.getStatus());
        }

        if (StringUtils.isEmpty(postFormVo.getComment_status())) {
            post.setComment_status(Constant.POST_COMMENT_STATUS_DEFAULT);
        } else {
            post.setComment_status(postFormVo.getComment_status());
        }

        post.setComment_count(Constant.POST_COMMENT_COUNT);
        post.setRead_count(Constant.POST_READ_COUNT);
        post.setSpot_count(Constant.POST_SPOT_COUNT);
        post.setCreator_id(1); // 当前登陆用户ID
        postService.addPost(post);

        if (StringUtils.isEmpty(postFormVo.getCategories())) {
            postFormVo.setCategories("1"); // 默认分类ID
        }

        // 新集合
        List<Integer> categoryIdList = StringUtil.StringToIntegerList(postFormVo.getCategories());
        List<Integer> tagIdList = StringUtil.StringToIntegerList(postFormVo.getTags());
        if (!ObjectUtils.isEmpty(categoryIdList) && !ObjectUtils.isEmpty(tagIdList)) {
            categoryIdList.addAll(tagIdList);
        }

        if (!ObjectUtils.isEmpty(categoryIdList)) {
            relationshipService.addRelationshipList(post.getId(), categoryIdList);
            categoryService.updatePostCountByIdList(categoryIdList, 1);
        }

        return post.getId();
    }

    /**
     * 更新文章
     */
    @Transactional
    public int updatePostFormVo(PostFormVo postFormVo) {
        Post post = new Post();
        post.setSlug(postFormVo.getSlug());
        post.setThumb_url(postFormVo.getThumb_url());
        post.setTitle(postFormVo.getTitle());
        post.setContent(postFormVo.getContent());
        Date date = new Date();
        post.setUpdate_time(date);

        if (StringUtils.isEmpty(postFormVo.getType())) {
            post.setType(Constant.POST_TYPE_DEFAULT);
        } else {
            post.setType(postFormVo.getType());
        }

        post.setFmt_type(postFormVo.getFmt_type());

        if (StringUtils.isEmpty(postFormVo.getStatus())) {
            post.setStatus(Constant.POST_STATUS_DEFAULT);
        } else {
            post.setStatus(postFormVo.getStatus());
        }

        if (StringUtils.isEmpty(postFormVo.getComment_status())) {
            post.setComment_status(Constant.POST_COMMENT_STATUS_DEFAULT);
        } else {
            post.setComment_status(postFormVo.getComment_status());
        }

        int ret = postService.updatePost(post);

        // 原集合
        List<Integer> termIdList = relationshipService.findAllTermByTargetId(post.getId());

        // 新集合
        List<Integer> categoryIdList = StringUtil.StringToIntegerList(postFormVo.getCategories());
        List<Integer> tagIdList = StringUtil.StringToIntegerList(postFormVo.getTags());
        if (!ObjectUtils.isEmpty(categoryIdList) && !ObjectUtils.isEmpty(tagIdList)) {
            categoryIdList.addAll(tagIdList);
        }

        List<Integer> oldTermIdList = new ArrayList<>(termIdList);

        // 原集合-新集合 差集
        if (!ObjectUtils.isEmpty(categoryIdList) && !ObjectUtils.isEmpty(termIdList)) {
            termIdList.removeAll(categoryIdList);
            if (!ObjectUtils.isEmpty(termIdList)) {
                relationshipService.removeRelationshipList(post.getId(), termIdList);
                categoryService.updatePostCountByIdList(termIdList, -1);
            }
        }

        // 新集合-原集合 差集
        if (!ObjectUtils.isEmpty(categoryIdList) && !ObjectUtils.isEmpty(oldTermIdList)) {
            categoryIdList.removeAll(oldTermIdList);
            if (!ObjectUtils.isEmpty(categoryIdList)) {
                relationshipService.addRelationshipList(post.getId(), categoryIdList);
                categoryService.updatePostCountByIdList(categoryIdList, 1);
            }
        }

        return ret;
    }

    /**
     * 删除文章
     */
    @Transactional
    public int removePostForm(int postId) {
        int index = postService.removePostById(postId);

        // 原集合
        List<Integer> termIdList = relationshipService.findAllTermByTargetId(postId);

        if (!ObjectUtils.isEmpty(termIdList)) {
            categoryService.updatePostCountByIdList(termIdList, -1);
        }

        return index;
    }

}
