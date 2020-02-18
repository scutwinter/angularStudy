package com.java.website.myblog.dao;

import com.java.website.myblog.entity.Blog;
import org.springframework.stereotype.Component;

@Component
public interface BlogDao {
    int deleteByPrimaryKey(Long blogId);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long blogId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);
}