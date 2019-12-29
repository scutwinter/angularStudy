package com.java.website.myblog.dao;

import com.java.website.myblog.entity.BlogCategory;

import java.util.List;
import java.util.Map;


public interface BlogCategoryDao {
    int deleteByPrimaryKey(Integer categoryId);

    int deleteBatch(Integer[] ids);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Integer categoryId);

    BlogCategory selectByCategoryName(String categoryName);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);

    List<BlogCategory> findCategoryList(Map param);

    int getTotalCategories();
}