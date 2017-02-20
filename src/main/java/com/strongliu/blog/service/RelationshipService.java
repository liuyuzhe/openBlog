package com.strongliu.blog.service;

import com.strongliu.blog.dao.RelationshipDao;
import com.strongliu.blog.entity.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/20.
 */

@Service
public class RelationshipService {

    @Autowired
    RelationshipDao relationshipDao;

    public List<Relationship> findAllReleationshipByTargetId(String targetId)
    {
        return relationshipDao.selectAllByTargetId(targetId);
    }

    public List<Relationship> findAllReleationshipByTermId(int termId)
    {
        return relationshipDao.selectAllByTermId(termId);
    }

}
