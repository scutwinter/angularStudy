package com.java.website.myblog.service;

import com.java.website.myblog.controller.vo.SimpleBlogListVo;
import com.java.website.myblog.entity.Blog;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;

import java.util.List;

public interface BlogService {
    String saveBlog(Blog blog);
    Blog getBlogById(long blogId);
    String updateBlog(Blog blog);
    PageResult getBlogPage(PageUtil pageUtil);
    Boolean deleteBatch(Integer[] ids);
    List<SimpleBlogListVo> getBlogListForIndexPage(int type);
    PageResult getBlogsForIndexPage(int page);
}
