package com.java.website.myblog.service;

import com.java.website.myblog.entity.BlogTagCount;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;

import java.util.List;

public interface TagService {
    PageResult getBlogTagPage(PageUtil pageUtil);

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();

}
