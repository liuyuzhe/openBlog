package com.strongliu.blog.service;

import com.strongliu.blog.dao.RelationshipDao;
import com.strongliu.blog.entity.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/20.
 */

@Service
public class RelationshipService {

    @Autowired
    RelationshipDao relationshipDao;

    /**
     * 根据文章Id,查询分类Id列表
     */
    public List<Integer> findAllTermByTargetId(int targetId) {
        return relationshipDao.selectAllByTargetId(targetId);
    }

    /**
     * 根据分类Id,查询文章Id列表
     */
    public List<Integer> findAllTargetByTermId(int termId) {
        return relationshipDao.selectAllByTermId(termId);
    }

    /**
     * 添加文章Id与分类Id列表的对应关系
     */
    public int addRelationshipList(int targetId, List<Integer> termIdList) {
        List<Relationship> relationshipList = new ArrayList<>();
        for (Integer termId : termIdList) {
            Relationship relationship = new Relationship(targetId, termId);
            relationshipList.add(relationship);
        }

        return relationshipDao.insertList(relationshipList);
    }

    /**
     * 移除文章Id与分类Id列表的对应关系
     */
    public int removeRelationshipList(int targetId, List<Integer> termIdList) {
        List<Relationship> relationshipList = new ArrayList<>();
        for (Integer termId : termIdList) {
            Relationship relationship = new Relationship(targetId, termId);
            relationshipList.add(relationship);
        }

        return relationshipDao.deleteList(relationshipList);
    }

}
