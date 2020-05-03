package com.java.website.myblog.service.impl;

import com.java.website.myblog.dao.BlogCommentDao;
import com.java.website.myblog.entity.BlogComment;
import com.java.website.myblog.service.CommentService;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private BlogCommentDao blogCommentDao;

    @Override
    public PageResult getCommentsPage(PageUtil pageUtil) {
        List<BlogComment> blogComments=blogCommentDao.findBlogCommentList(pageUtil);
        int total = blogCommentDao.getTotalComments(pageUtil);
        PageResult pageResult = new PageResult(blogComments,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean checkDone(Integer[] ids) {
        return blogCommentDao.checkDone(ids)>0;
    }

    @Override
    public Boolean reply(Long commentId, String replyBody) {
        BlogComment blogComment = blogCommentDao.selectByPrimaryKey(commentId);
        if(blogComment!=null && blogComment.getCommentStatus().intValue()==1){
            blogComment.setReplyBody(replyBody);
            blogComment.setReplyCreateTime(new Date());
            return blogCommentDao.updateByPrimaryKeySelective(blogComment)>0;
        }
        return false;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogCommentDao.deleteBatch(ids)>0;
    }

    @Override
    public Boolean addComment(BlogComment blogComment) {
        return blogCommentDao.insertSelective(blogComment)>0;
    }

    @Override
    public PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page) {
        if(page<1){
            return null;
        }
        Map params = new HashMap();
        params.put("page",page);
        params.put("limit",8);
        params.put("blogId",blogId);
        params.put("commentStatus",1);
        PageUtil pageUtil = new PageUtil(params);
        List<BlogComment> blogComments = blogCommentDao.findBlogCommentList(pageUtil);
        if(!CollectionUtils.isEmpty(blogComments)){
            int total = blogCommentDao.getTotalComments(pageUtil);
            PageResult pageResult=new PageResult(blogComments,total,pageUtil.getLimit(),pageUtil.getPage());
            return pageResult;
        }
        return null;
    }

    @Override
    public int getTotalComments() {
        return blogCommentDao.getTotalComments(null);
    }
}
