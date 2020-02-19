package com.java.website.myblog.service.impl;

import com.java.website.myblog.dao.BlogCategoryDao;
import com.java.website.myblog.dao.BlogTagDao;
import com.java.website.myblog.dao.BlogTagRelationDao;
import com.java.website.myblog.dao.BlogDao;
import com.java.website.myblog.entity.Blog;
import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.entity.BlogTag;
import com.java.website.myblog.entity.BlogTagRelation;
import com.java.website.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private BlogCategoryDao blogCategoryDao;
    @Autowired
    private BlogTagDao blogTagDao;
    @Autowired
    private BlogTagRelationDao blogTagRelationDao;


    @Override
    public List<BlogCategory> getAllBlogCategory() {
        return blogCategoryDao.findCategoryList(null);
    }

    @Override
    @Transactional
    public String saveBlog(Blog blog) {
        BlogCategory blogCategory = blogCategoryDao.selectByPrimaryKey(blog.getBlogCategoryId());
        if (blogCategory==null){
            blog.setBlogCategoryId(0);
            blog.setBlogCategoryName("默认分类");
        }else {
            //设置博客分类名称
            blog.setBlogCategoryName(blogCategory.getCategoryName());
            //博客分类排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank()+1);
        }

        //将标签数据存入数组
        String[] tags=blog.getBlogTags().split(",");
        if(tags.length>6){
            return "标签数量限制为6";
        }

        //保存博客文章
        if(blogDao.insertSelective(blog)>0){
            //新增标签数据
            List<BlogTag> tagListForInsert = new ArrayList<>();
            //本次文章的标签数据
            List<BlogTag> allTagsList = new ArrayList<>();
            for (int i= 0;i<tags.length;i++){
                BlogTag blogTag = blogTagDao.selectByTagName(tags[i]);
                if (blogTag == null){ //如果标签数据不存在，则增加标签
                    BlogTag tmpTag = new BlogTag();
                    tmpTag.setTagName(tags[i]);
                    tagListForInsert.add(tmpTag);
                }else{//存在则加入本次文章的标签
                    allTagsList.add(blogTag);
                }
            }
            //将此次新增的标签数据存入数据库
            if(!CollectionUtils.isEmpty(tagListForInsert)){
                blogTagDao.batchInsertBlogTag(tagListForInsert);
            }
            //更新本次分类的排序信息
            blogCategoryDao.updateByPrimaryKeySelective(blogCategory);
            //标签与文章关系数组
            List<BlogTagRelation> blogTagRelations=new ArrayList<>();
            //将本次新增入数据库的标签数据加入本次文章标签
            allTagsList.addAll(tagListForInsert);
            //循环标签信息，添加它与文章的关系数据，并保存
            for(BlogTag tag:allTagsList){
                BlogTagRelation blogTagRelation=new BlogTagRelation();
                blogTagRelation.setBlogId(blog.getBlogId());
                blogTagRelation.setTagId(tag.getTagId());
                blogTagRelations.add(blogTagRelation);
            }
            if (blogTagRelationDao.batchInsert(blogTagRelations)>0){
                return "success";
            }
        }

        return "保存失败";
    }
}
