package com.java.website.myblog.service.impl;

import com.java.website.myblog.dao.BlogCategoryDao;
import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.service.BlogCategoryService;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
    @Resource
    private BlogCategoryDao blogCategoryDao;

    @Override
    public PageResult getBlogCategoryPage(PageUtil pageUtil) {
        List<BlogCategory> blogCategories=blogCategoryDao.findCategoryList(pageUtil);
        int totalCount=blogCategoryDao.getTotalCategories();
        PageResult pageResult = new PageResult(blogCategories,totalCount,pageUtil.getLimit(),pageUtil.getPage());
        return null;
    }

    @Override
    @Transactional
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        BlogCategory tmp = blogCategoryDao.selectByCategoryName(categoryName);
        if (tmp==null){
            BlogCategory blogCategory=new BlogCategory();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            return blogCategoryDao.insertSelective(blogCategory)>0;
        }
        return false;

    }

    @Override
    @Transactional
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length<1){
            return false;
        }
        return blogCategoryDao.deleteBatch(ids)>0;
    }

    @Override
    public BlogCategory selectById(Integer id) {
        return blogCategoryDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String caategoryIcon) {
        BlogCategory blogCategory = blogCategoryDao.selectByPrimaryKey(categoryId);
        if (blogCategory!=null){
            blogCategory.setCategoryIcon(caategoryIcon);
            blogCategory.setCategoryName(categoryName);
            return blogCategoryDao.updateByPrimaryKeySelective(blogCategory)>0;
        }
        return false;
    }
}
