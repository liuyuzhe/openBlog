package com.strongliu.blog.manager;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.Category;
import com.strongliu.blog.entity.Post;
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

        List<String> targetList = relationshipService.findAllTargetByTermId(category.getId());
        List<Post> postList = postService.findAllPublishPostByIdList(targetList, pageId, Constant.PAGE_SIZE);

        int pageTotal = targetList.size() / Constant.PAGE_SIZE;
        if (targetList.size() % Constant.PAGE_SIZE != 0) {
            pageTotal += 1;
        }

        categoryVo.setCategory(category);
        categoryVo.setPostList(postList);
        categoryVo.setPageIndex(pageId);
        categoryVo.setPageTotal(pageTotal);

        return categoryVo;
    }
}
