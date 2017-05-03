package com.strongliu.blog.service;

import com.strongliu.blog.dao.TagDao;
import com.strongliu.blog.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/19.
 */

@Service
public class TagService {

    @Autowired
    private TagDao tagDao;

    public Tag findTagById(int id) {
        return tagDao.selectById(id);
    }

    /**
     * 查找标签
     */
    public Tag findTagBySlug(String slug) {
        return tagDao.selectBySlug(slug);
    }

    public List<Tag> findAllTagByIdList(List<Integer> idList) {
        return tagDao.selectAllByIdList(idList);
    }

    public List<Tag> findAllTag() {
        return tagDao.selectAll();
    }

    public int addTag(Tag tag) {
        return tagDao.insert(tag);
    }

    public int updateTag(Tag tag) {
        return tagDao.update(tag);
    }

    public int updatePostCount(int id, int number) {
        return tagDao.updatePostCount(id, number);
    }

    public int removeTagById(int id) {
        return tagDao.deleteById(id);
    }
}
