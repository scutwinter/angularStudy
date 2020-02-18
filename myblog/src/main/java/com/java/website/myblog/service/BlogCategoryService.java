package com.java.website.myblog.service;

import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;

public interface BlogCategoryService {
    PageResult getBlogCategoryPage(PageUtil pageUtil);

    Boolean saveCategory(String categoryName, String categoryIcon);

    Boolean deleteBatch(Integer[] ids);

    BlogCategory selectById(Integer id);

    Boolean updateCategory(Integer categoryId,String categoryName,String caategoryIcon);
}