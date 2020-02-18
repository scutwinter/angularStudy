package com.java.website.myblog.service;

import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;

public interface TagService {
    PageResult getBlogTagPage(PageUtil pageUtil);

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);



}
