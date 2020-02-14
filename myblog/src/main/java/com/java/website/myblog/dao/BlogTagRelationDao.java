package com.java.website.myblog.dao;

import com.java.website.myblog.entity.BlogTagRelation;

import java.util.List;

public interface BlogTagRelationDao {
    int deleteByPrimaryKey(Long relationId);

    int insert(BlogTagRelation record);

    int insertSelective(BlogTagRelation record);

    BlogTagRelation selectByPrimaryKey(Long relationId);

    int updateByPrimaryKeySelective(BlogTagRelation record);

    int updateByPrimaryKey(BlogTagRelation record);

    List<Long> selectDistinctTagIds(Integer[] ids);
}