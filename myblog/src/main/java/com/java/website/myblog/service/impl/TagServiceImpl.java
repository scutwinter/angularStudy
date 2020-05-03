package com.java.website.myblog.service.impl;

import com.java.website.myblog.dao.BlogTagDao;
import com.java.website.myblog.dao.BlogTagRelationDao;
import com.java.website.myblog.entity.BlogTag;
import com.java.website.myblog.entity.BlogTagCount;
import com.java.website.myblog.service.TagService;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private BlogTagDao blogTagDao;
    @Resource
    private BlogTagRelationDao blogTagRelationDao;
    @Override
    public PageResult getBlogTagPage(PageUtil pageUtil) {
        List<BlogTag> tags=blogTagDao.findTagList(pageUtil);
        int total = blogTagDao.getTotalTags();
        PageResult pageResult = new PageResult(tags,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean saveTag(String tagName) {
        BlogTag temp = blogTagDao.selectByTagName(tagName);
        if(temp == null){
            BlogTag blogTag = new BlogTag();
            blogTag.setTagName(tagName);
            return blogTagDao.insertSelective(blogTag)>0;
        }
        return false;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        List<Long> relations=blogTagRelationDao.selectDistinctTagIds(ids);
        if(!CollectionUtils.isEmpty(relations)){
            return false;
        }
        return blogTagDao.deleteBatch(ids)>0;
    }

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        return blogTagDao.getTagCount();
    }

    @Override
    public int getTotalBlogTag() {
        return blogTagDao.getTotalTags();
    }
}
