package com.java.website.myblog.dao;

import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.entity.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface LinkDao {
    int deleteByPrimaryKey(Integer linkId);

    int deleteBatch(Integer[] ids);

    int insert(Link record);

    int insertSelective(Link record);

    Link selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);

    List<Link> findLinkList(Map param);

    int getTotalLinks();
}