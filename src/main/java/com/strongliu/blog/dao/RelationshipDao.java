package com.strongliu.blog.dao;

import com.strongliu.blog.entity.Relationship;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/20.
 */
public interface RelationshipDao {

    List<Integer> selectAllByTargetId(int id);

    List<Integer> selectAllByTermId(int id);

    int insertList(List<Relationship> relationshipList);

    int deleteList(List<Relationship> relationshipList);
}
