package com.strongliu.blog.Manager;

import com.strongliu.blog.constant.Constant;
import com.strongliu.blog.entity.Post;
import com.strongliu.blog.service.PostService;
import com.strongliu.blog.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */

@Component
public class PageManager {

    @Autowired
    private PostService postService;

    @Autowired
    private PageVo pageVo;

    public PageVo getPageVoByPageId(int pageId) {
        List<Post> postList = postService.findAllPublishPost(pageId, Constant.PAGE_SIZE);
        if (postList == null) {
            return null;
        }

        int pageTotal = postService.pageTotal(Constant.PAGE_SIZE);

        pageVo.setPostList(postList);
        pageVo.setPageIndex(pageId);
        pageVo.setPageTotal(pageTotal);

        return pageVo;
    }
}



