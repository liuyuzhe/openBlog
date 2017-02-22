package com.strongliu.blog.service;

import com.strongliu.blog.dao.RelationshipDao;
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

    public List<Integer> findAllTermByTargetId(String targetId)
    {
        return relationshipDao.selectAllByTargetId(targetId);
    }

    public List<String> findAllTargetByTermId(Integer termId)
    {
        return relationshipDao.selectAllByTermId(termId);
    }

}
