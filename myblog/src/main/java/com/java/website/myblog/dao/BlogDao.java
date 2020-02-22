package com.java.website.myblog.dao;

import com.java.website.myblog.entity.Blog;
import com.java.website.myblog.entity.BlogCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface BlogDao {
    int deleteByPrimaryKey(Long blogId);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long blogId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> findBlogList(Map param);

    int getTotalBlogs(Map param);

    int deleteBatch(Integer[] ids);

    List<Blog> findBlogListByType(@Param("type") int type, @Param("limit") int limit);
}