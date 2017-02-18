package com.strongliu.blog.Manager;

import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.entity.User;
import com.strongliu.blog.service.CategoryService;
import com.strongliu.blog.service.PostService;
import com.strongliu.blog.service.UserService;
import com.strongliu.blog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuyuzhe on 2017/2/18.
 */

@Component
public class PostManager {

    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @Autowired
    private PostVo postVo;

    public PostVo getPostVoById(String postId) {
        Post post = postService.findPublishPostById(postId);
        if (post == null) {
            return null;
        }

        Category category = categoryService.findCategoryById(post.getCategory_Id());
        User user = userService.findUserById(post.getAuthor_Id());
        Post postPrev = postService.findPublishPrevPostById(postId);
        Post postNext = postService.findPublishNextPostById(postId);


        postVo.setPost(post);
        postVo.setCategory(category);
        postVo.setUser(user);
        postVo.setPostPrev(postPrev);
        postVo.setPostNext(postNext);

        return postVo;
    }
}
