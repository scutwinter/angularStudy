package com.java.website.myblog.dao;

import com.java.website.myblog.entity.BlogTag;

import java.util.List;
import java.util.Map;

public interface BlogTagDao {
    int deleteByPrimaryKey(Integer tagId);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);

    List<BlogTag> findTagList(Map param);

    int getTotalTags();

    BlogTag selectByTagName(String tagName);

    int deleteBatch(Integer[] ids);
}