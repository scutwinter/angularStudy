package com.java.website.myblog.dao;

import com.java.website.myblog.entity.BlogConfig;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogConfigDao {
    int deleteByPrimaryKey(String configName);

    int insert(BlogConfig record);

    int insertSelective(BlogConfig record);

    BlogConfig selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(BlogConfig record);

    int updateByPrimaryKey(BlogConfig record);

    List<BlogConfig> selectAll();
}