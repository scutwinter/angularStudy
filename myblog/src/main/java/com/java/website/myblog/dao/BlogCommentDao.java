package com.java.website.myblog.dao;

import com.java.website.myblog.entity.BlogComment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface BlogCommentDao {
    int deleteByPrimaryKey(Long commentId);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKey(BlogComment record);

    List<BlogComment> findBlogCommentList(Map param);

    int getTotalComments(Map param);

    int checkDone(Integer[] ids);

    int deleteBatch(Integer[] ids);
}