package com.java.website.myblog.service.impl;

import com.java.website.myblog.dao.BlogCategoryDao;
import com.java.website.myblog.dao.BlogDao;
import com.java.website.myblog.entity.Blog;
import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.entity.BlogCategoryCount;
import com.java.website.myblog.service.BlogCategoryService;
import com.java.website.myblog.service.BlogService;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
    @Resource
    private BlogCategoryDao blogCategoryDao;

    @Resource
    private BlogDao blogDao;


    @Override
    public PageResult getBlogCategoryPage(PageUtil pageUtil) {
        List<BlogCategory> blogCategories = blogCategoryDao.findCategoryList(pageUtil);
        int totalCount = blogCategoryDao.getTotalCategories();
        PageResult pageResult = new PageResult(blogCategories, totalCount, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    

    @Override
    @Transactional
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        BlogCategory tmp = blogCategoryDao.selectByCategoryName(categoryName);
        if (tmp == null) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            return blogCategoryDao.insertSelective(blogCategory) > 0;
        }
        return false;

    }

    @Override
    @Transactional
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return blogCategoryDao.deleteBatch(ids) > 0;
    }

    @Override
    public BlogCategory selectById(Integer id) {
        return blogCategoryDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String caategoryIcon) {
        BlogCategory blogCategory = blogCategoryDao.selectByPrimaryKey(categoryId);
        if (blogCategory != null) {
            blogCategory.setCategoryIcon(caategoryIcon);
            blogCategory.setCategoryName(categoryName);
            return blogCategoryDao.updateByPrimaryKeySelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    public List<BlogCategory> getAllCategories() {
        return blogCategoryDao.findCategoryList(null);
    }

    @Override
    public int getTotalCategories() {
        return blogCategoryDao.getTotalCategories();
    }

    @Override
    public List<BlogCategoryCount> getBlogCategoryCount() {
        String categoryBlog="";
        List<BlogCategoryCount> blogCategoryCounts = blogCategoryDao.getCategoryCount();
        for (BlogCategoryCount blogCategoryCount : blogCategoryCounts) {
            Map params = new HashMap();
            if (blogCategoryCount.getCategoryId() > 0) {
                params.put("blogStatus", 1);
                params.put("blogCategoryId", blogCategoryCount.getCategoryId());
                List<Blog> blogs = blogDao.findBlogList(params);
                //将集合中blogs 对象BlogTitle值以||号方式隔开转为字符串
                categoryBlog = blogs.stream().map(Blog::getBlogTitle).collect(Collectors.joining("||"))+"||";
                blogCategoryCount.setCategoryBlog(categoryBlog);
            } else {
                blogCategoryCount.setCategoryBlog("");
                blogCategoryCount.setCategoryName("");
            }

        }
        return blogCategoryCounts;
    }
}

