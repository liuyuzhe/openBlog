package com.strongliu.blog.manager;

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

    /**
     * 获取该分类下的文章列表
     */
    @Transactional
    public CategoryVo getCategoryVo(String keyword, int pageId, int limit) {
        Category category = categoryService.findCategoryBySlug(keyword);
        if (category == null) {
            return null;
        }

        List<String> targetList = relationshipService.findAllTargetByTermId(category.getId());
        List<Post> postList = postService.findAllPublishPostByIdList(targetList, pageId, limit);

        int pageTotal = targetList.size() / limit;
        if (targetList.size() % limit != 0) {
            pageTotal += 1;
        }

        categoryVo.setCategory(category);
        categoryVo.setPostList(postList);
        categoryVo.setPageIndex(pageId);
        categoryVo.setPageTotal(pageTotal);

        return categoryVo;
    }

    /**
     * 获取所有分类
     */
    public List<Category> getAllCategory() {
        return categoryService.findAllCategory();
    }

    public int addCategory(Category category) {
        return categoryService.addCategory(category);
    }

    public int updateCategory(Category category) {
        return categoryService.updateCategory(category);
    }

    public int removeCategory(Integer categoryId) {
        return categoryService.removeCategoryById(categoryId);
    }

}
