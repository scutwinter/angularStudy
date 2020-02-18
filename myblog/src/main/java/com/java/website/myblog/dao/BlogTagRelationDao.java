package com.java.website.myblog.dao;

import com.java.website.myblog.entity.BlogTagRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BlogTagRelationDao {
    int deleteByPrimaryKey(Long relationId);

    int insert(BlogTagRelation record);

    int insertSelective(BlogTagRelation record);

    BlogTagRelation selectByPrimaryKey(Long relationId);

    int updateByPrimaryKeySelective(BlogTagRelation record);

    int updateByPrimaryKey(BlogTagRelation record);

    List<Long> selectDistinctTagIds(Integer[] ids);
    //因为XML中KEY值没有使用list 因此此处要加上@Param注解
    int batchInsert(@Param("relationList") List<BlogTagRelation> blogTagRelations);
}