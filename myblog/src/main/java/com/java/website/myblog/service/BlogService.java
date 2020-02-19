package com.java.website.myblog.service;

import com.java.website.myblog.entity.Blog;
import com.java.website.myblog.entity.BlogCategory;

import java.util.List;

public interface BlogService {
    String saveBlog(Blog blog);
    List<BlogCategory> getAllBlogCategory();
}
