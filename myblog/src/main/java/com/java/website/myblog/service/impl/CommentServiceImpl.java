package com.java.website.myblog.service.impl;

import com.java.website.myblog.dao.BlogCommentDao;
import com.java.website.myblog.entity.BlogComment;
import com.java.website.myblog.service.CommentService;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private BlogCommentDao blogCommentDao;

    @Override
    public PageResult getCommentsPage(PageUtil pageUtil) {
        List<BlogComment> blogComments=blogCommentDao.findBlogCommentList(pageUtil);
        int total = blogCommentDao.getTotalComments();
        PageResult pageResult = new PageResult(blogComments,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }
}
