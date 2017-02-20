package com.strongliu.blog.Manager;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.entity.Relationship;
import com.strongliu.blog.service.CategoryService;
import com.strongliu.blog.service.PostService;
import com.strongliu.blog.service.RelationshipService;
import com.strongliu.blog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/18.
 */

@Component
public class CategoryManager {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private PostService postService;

    @Autowired
    private CategoryVo categoryVo;

    @Transactional
    public CategoryVo getCategoryVoByCategoryName(String categoryName, int pageId) {
        Category category = categoryService.findCategoryByName(categoryName);
        if (category == null) {
            return null;
        }

        List<Relationship> relationshipList = relationshipService.findAllReleationshipByTermId(category.getId());

        List<Post> postList = postService.findAllPublishPostByCategoryId(category.getId(), pageId, Constant.PAGE_SIZE);

        categoryVo.setCategory(category);
        categoryVo.setPostList(postList);

        return categoryVo;
    }
}
