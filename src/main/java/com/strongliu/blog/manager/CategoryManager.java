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
import org.springframework.util.ObjectUtils;

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

    /**
     * 获取该分类下的文章列表
     */
    @Transactional
    public CategoryVo getCategoryVo(String slug, int pageId, int limit) {
        Category category = categoryService.findCategoryBySlug(slug);
        if (ObjectUtils.isEmpty(category)) {
            return null;
        }

        List<Integer> targetList = relationshipService.findAllTargetByTermId(category.getId());
        List<Post> postList = postService.findAllPublishPostByIdList(targetList, pageId, limit);

        int pageTotal = targetList.size() / limit;
        if (targetList.size() % limit != 0) {
            pageTotal += 1;
        }

        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setCategory(category);
        categoryVo.setPostList(postList);
        if (pageId > 0 && pageTotal > 0) {
            categoryVo.setPageIndex(pageId);
            categoryVo.setPageTotal(pageTotal);
        }

        return categoryVo;
    }

    /**
     * 获取所有分类
     */
    public List<Category> getAllCategory() {
        return categoryService.findAllCategory();
    }

    public int addCategory(Category category) {
        categoryService.addCategory(category);
        return category.getId();
    }

    public int updateCategory(Category category) {
        return categoryService.updateCategory(category);
    }

    public int removeCategory(int categoryId) {
        return categoryService.removeCategoryById(categoryId);
    }

}
