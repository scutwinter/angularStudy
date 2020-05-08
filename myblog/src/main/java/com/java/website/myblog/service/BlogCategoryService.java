package com.java.website.myblog.service;

import com.java.website.myblog.controller.vo.BlogDetailVO;
import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.entity.BlogCategoryCount;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;

import java.util.List;

public interface BlogCategoryService {
    PageResult getBlogCategoryPage(PageUtil pageUtil);

    Boolean saveCategory(String categoryName, String categoryIcon);

    Boolean deleteBatch(Integer[] ids);

    BlogCategory selectById(Integer id);

    Boolean updateCategory(Integer categoryId,String categoryName,String caategoryIcon);

    List<BlogCategory> getAllCategories();

    List<BlogCategoryCount> getBlogCategoryCount();

    int getTotalCategories();

}
