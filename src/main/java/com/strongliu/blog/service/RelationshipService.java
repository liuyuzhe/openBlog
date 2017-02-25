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

    public List<Integer> findAllTermByTargetId(String targetId)
    {
        return relationshipDao.selectAllByTargetId(targetId);
    }

    public List<String> findAllTargetByTermId(Integer termId)
    {
        return relationshipDao.selectAllByTermId(termId);
    }

    public int addRelationshipList(String targetId, List<Integer> termIdList) {
        List<Relationship> relationshipList = new ArrayList<>();
        for (Integer termId : termIdList) {
            Relationship relationship = new Relationship(targetId, termId);
            relationshipList.add(relationship);
        }

        return relationshipDao.insertList(relationshipList);
    }

    public int removeRelationshipList(String targetId, List<Integer> termIdList) {
        List<Relationship> relationshipList = new ArrayList<>();
        for (Integer termId : termIdList) {
            Relationship relationship = new Relationship(targetId, termId);
            relationshipList.add(relationship);
        }

        return relationshipDao.deleteList(relationshipList);
    }

}
