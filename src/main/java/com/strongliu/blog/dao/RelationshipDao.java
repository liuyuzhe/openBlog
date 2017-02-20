package com.strongliu.blog.dao;

import com.strongliu.blog.entity.Relationship;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/20.
 */
public interface RelationshipDao {

    List<Relationship> selectAllByTargetId(String id);

    List<Relationship> selectAllByTermId(int id);
}
