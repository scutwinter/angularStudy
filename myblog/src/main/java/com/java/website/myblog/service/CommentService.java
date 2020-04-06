package com.java.website.myblog.service;

import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;

public interface CommentService {
    public PageResult getCommentsPage(PageUtil pageUtil);
    public Boolean checkDone(Integer[] ids);
    public Boolean reply(Long commentId,String replyBody);
    public Boolean deleteBatch(Integer[] ides);
}
