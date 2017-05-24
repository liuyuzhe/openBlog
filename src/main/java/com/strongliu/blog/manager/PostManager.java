package com.strongliu.blog.manager;

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
import org.springframework.util.StringUtils;

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
    @Transactional
    public PostPageVo getPostPageVo(int pageId, int limit) {
        List<Post> postList = postService.findAllPost(pageId, limit);

        int pageTotal = postService.pageTotal(limit);

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
    @Transactional
    public PostPageVo getPublishPostPageVo(int pageId, int limit) {
        List<Post> postList = postService.findAllPublishPost(pageId, limit);

        int pageTotal = postService.pageTotal(limit);

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
        if (post == null) {
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
        if (post == null) {
            return null;
        }

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
        post.setExcerpt(postFormVo.getContent());
        post.setContent(postFormVo.getContent());
        Date date = new Date();
        post.setCreate_time(date);
        post.setUpdate_time(date);
        if (StringUtils.isEmpty(postFormVo.getType())) {
            post.setType(postFormVo.getType());
        }
        post.setFmt_type(postFormVo.getFmt_type());
        if (StringUtils.isEmpty(postFormVo.getStatus())) {
            post.setStatus(postFormVo.getStatus());
        }
        if (StringUtils.isEmpty(postFormVo.getComment_status())) {
            post.setComment_status(postFormVo.getComment_status());
        }
        post.setCreator_id(1); // 当前登陆用户ID
        int ret = postService.addPost(post);

        if (StringUtils.isEmpty(postFormVo.getCategories())) {
            postFormVo.setCategories("1"); // 默认分类ID
        }

        List<Integer> categoryIdList = StringUtil.StringToIntegerList(postFormVo.getCategories());
        List<Integer> tagIdList = StringUtil.StringToIntegerList(postFormVo.getTags());
        categoryIdList.addAll(tagIdList);
        relationshipService.addRelationshipList(post.getId(), categoryIdList);

        return ret;
    }

    /**
     * 更新文章
     */
    @Transactional
    public int updatePostFormVo(PostFormVo postFormVo) {
        Post post = new Post();
        post.setTitle(postFormVo.getTitle());
        post.setContent(postFormVo.getContent());
        post.setStatus(postFormVo.getStatus());
        post.setComment_status(postFormVo.getComment_status());
        int ret = postService.updatePost(post);

        List<Integer> termIdList = relationshipService.findAllTermByTargetId(post.getId());
        relationshipService.removeRelationshipList(post.getId(), termIdList);

        List<Integer> categoryIdList = StringUtil.StringToIntegerList(postFormVo.getCategories());
        List<Integer> tagIdList = StringUtil.StringToIntegerList(postFormVo.getTags());
        categoryIdList.addAll(tagIdList);
        relationshipService.addRelationshipList(post.getId(), categoryIdList);

        return ret;
    }

    /**
     * 删除文章
     */
    public int removePostForm(int postId) {
        return postService.removePostById(postId);
    }

}
