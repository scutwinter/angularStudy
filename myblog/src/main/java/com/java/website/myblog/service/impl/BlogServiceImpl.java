package com.java.website.myblog.service.impl;

import com.java.website.myblog.controller.vo.BlogDetailVO;
import com.java.website.myblog.controller.vo.BlogListVo;
import com.java.website.myblog.controller.vo.SimpleBlogListVo;
import com.java.website.myblog.dao.BlogCategoryDao;
import com.java.website.myblog.dao.BlogTagDao;
import com.java.website.myblog.dao.BlogTagRelationDao;
import com.java.website.myblog.dao.BlogDao;
import com.java.website.myblog.entity.Blog;
import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.entity.BlogTag;
import com.java.website.myblog.entity.BlogTagRelation;
import com.java.website.myblog.service.BlogService;
import com.java.website.myblog.util.MarkDownUtil;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;
import com.java.website.myblog.util.PatternUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.PatternUtils;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public String updateBlog(Blog blog) {
        Blog blogForUpdate= blogDao.selectByPrimaryKey(blog.getBlogId());
        if (blogForUpdate==null){
            return "数据不存在";
        }
        blogForUpdate.setBlogTitle(blog.getBlogTitle());
        blogForUpdate.setBlogTitle(blog.getBlogTitle());
        blogForUpdate.setBlogSubUrl(blog.getBlogSubUrl());
        blogForUpdate.setBlogContent(blog.getBlogContent());
        blogForUpdate.setBlogCoverImage(blog.getBlogCoverImage());
        blogForUpdate.setBlogStatus(blog.getBlogStatus());
        blogForUpdate.setEnableComment(blog.getEnableComment());
        BlogCategory blogCategory = blogCategoryDao.selectByPrimaryKey(blog.getBlogCategoryId());
        if (blogCategory == null) {
            blogForUpdate.setBlogCategoryId(0);
            blogForUpdate.setBlogCategoryName("默认分类");
        } else {
            //设置博客分类名称
            blogForUpdate.setBlogCategoryName(blogCategory.getCategoryName());
            blogForUpdate.setBlogCategoryId(blogCategory.getCategoryId());
            //分类的排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }

        //将标签数据存入数组
        String[] tags=blog.getBlogTags().split(",");
        if(tags.length>6){
            return "标签数量限制为6";
        }
        blogForUpdate.setBlogTags(blog.getBlogTags());

        //新增TAG对象


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
        //更新本次分类的排序信息
        blogCategoryDao.updateByPrimaryKeySelective(blogCategory);
        //删除原来关系数据
        blogTagRelationDao.deleteByBlogId(blog.getBlogId());
        blogTagRelationDao.batchInsert(blogTagRelations);
        if (blogDao.updateByPrimaryKeySelective(blogForUpdate)>0){
            return "success";
        }
        return "修改失败";
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if(ids.length < 1){
            return false;
        }
        return blogDao.deleteBatch(ids)>0;
    }

    @Override
    public PageResult getBlogPage(PageUtil pageUtil) {
        List<Blog> blogs=blogDao.findBlogList(pageUtil);
        int total=blogDao.getTotalBlogs(pageUtil);
        PageResult pageResult = new PageResult(blogs,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public List<Blog> getBlogList(PageUtil pageUtil) {
        return blogDao.findBlogList(pageUtil);
    }

    @Override
    public Blog getBlogById(long blogId) {
        return blogDao.selectByPrimaryKey(blogId);
    }

    @Override
    public List<SimpleBlogListVo> getBlogListForIndexPage(int type) {
        List<SimpleBlogListVo> simpleBlogListVos=new ArrayList<>();
        List<Blog> blogs = blogDao.findBlogListByType(type,9);
        if(!CollectionUtils.isEmpty(blogs)){
            for (Blog blog:blogs){
                SimpleBlogListVo simpleBlogListVo = new SimpleBlogListVo();
                BeanUtils.copyProperties(blog,simpleBlogListVo);
                simpleBlogListVos.add(simpleBlogListVo);
            }
        }
        return simpleBlogListVos;
    }

    private List<BlogListVo> getBlogListVOsByBlogs(List<Blog> blogList){
        List<BlogListVo> blogListVos=new ArrayList<>();
        if(!CollectionUtils.isEmpty(blogList)){
            //使用stream的map方法映射blogList中id的结果，对blogList的每一个blog对象执行getBlogCategoryId
            List<Integer> categoryIds = blogList.stream().map(Blog::getBlogCategoryId).collect(Collectors.toList());
            Map<Integer,String> blogCategoryMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(categoryIds)){
                List<BlogCategory> blogCategories=blogCategoryDao.selectByCategoryIds(categoryIds);
                if(!CollectionUtils.isEmpty(blogCategories)){
                    //把Stream中的元素集合收集到一个结果容器中，再使用Collectors转成Map 既实现把list转成map 如果 (key1, key2)->key1则表示用前面的value覆盖后面的value，即保持不变
                    blogCategoryMap = blogCategories.stream().collect(Collectors.toMap(BlogCategory::getCategoryId,BlogCategory::getCategoryIcon,(key1,key2) -> key2));
                }
            }
            for(Blog blog : blogList){
                BlogListVo blogListVo = new BlogListVo();
                BeanUtils.copyProperties(blog,blogListVo);
                if(blogCategoryMap.containsKey(blog.getBlogCategoryId())){
                    blogListVo.setBlogCategoryIcon(blogCategoryMap.get(blog.getBlogCategoryId()));
                }else{
                    blogListVo.setBlogCategoryId(0);
                    blogListVo.setBlogCategoryName("默认分类");
                    blogListVo.setBlogCategoryIcon("/admin/dist/img/category/1.png");
                }
                blogListVos.add(blogListVo);
            }
        }
        return blogListVos;
    }

    @Override
    public PageResult getBlogsForIndexPage(int page){
        Map params = new HashMap();
        params.put("page",page);
        params.put("limit", 8);
        params.put("blogStatus",1);
        PageUtil pageUtil=new PageUtil(params);
        List<Blog> blogs=blogDao.findBlogList(params);
        List<BlogListVo> blogListVos=getBlogListVOsByBlogs(blogs);
        int total=blogDao.getTotalBlogs(params);
        PageResult pageResult = new PageResult(blogListVos,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public PageResult getBlogsPageBySearch(String keyword, int page) {
        Map params = new HashMap();
        params.put("page",page);
        params.put("limit", 8);
        params.put("blogStatus",1);
        params.put("keyword",keyword);
        PageUtil pageUtil=new PageUtil(params);
        List<Blog> blogs=blogDao.findBlogList(params);
        List<BlogListVo> blogListVos=getBlogListVOsByBlogs(blogs);
        int total=blogDao.getTotalBlogs(params);
        PageResult pageResult = new PageResult(blogListVos,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public PageResult getBlogsPageByCategory(String categoryName, int page) {
        if(PatternUtil.validKeyword(categoryName)){
            BlogCategory blogCategory=blogCategoryDao.selectByCategoryName(categoryName);
            if("默认分类".equals(categoryName) && blogCategory == null){
                blogCategory = new BlogCategory();
                blogCategory.setCategoryId(0);
            }
            if(blogCategory!=null && page > 0){
                Map params = new HashMap();
                params.put("page",page);
                params.put("limit", 8);
                params.put("blogStatus",1);
                params.put("blogCategoryId",blogCategory.getCategoryId());
                PageUtil pageUtil=new PageUtil(params);
                List<Blog> blogs=blogDao.findBlogList(params);
                List<BlogListVo> blogListVos=getBlogListVOsByBlogs(blogs);
                int total=blogDao.getTotalBlogs(params);
                PageResult pageResult = new PageResult(blogListVos,total,pageUtil.getLimit(),pageUtil.getPage());
                return pageResult;
            }
        }
        return null;
    }

    @Override
    public PageResult getBlogsPageByTagName(String tagName, int page) {
        if(PatternUtil.validKeyword(tagName)){
            BlogTag blogTag=blogTagDao.selectByTagName(tagName);
            if(blogTag!=null && page > 0){
                Map params = new HashMap();
                params.put("page",page);
                params.put("limit", 8);
                params.put("tagId",blogTag.getTagId());
                PageUtil pageUtil=new PageUtil(params);
                List<Blog> blogs=blogDao.getBlogPageByTagId(params);
                List<BlogListVo> blogListVos=getBlogListVOsByBlogs(blogs);
                int total=blogDao.getTotalBlogByTagId(params);
                PageResult pageResult = new PageResult(blogListVos,total,pageUtil.getLimit(),pageUtil.getPage());
                return pageResult;
            }
        }
        return null;
    }

    private BlogDetailVO getBlogDetailVO(Blog blog){
        if(blog !=null && blog.getBlogStatus() == 1){
            //增加浏览量
            blog.setBlogViews(blog.getBlogViews()+1);
            blogDao.updateByPrimaryKey(blog);
            BlogDetailVO blogDetailVO = new BlogDetailVO();
            BeanUtils.copyProperties(blog,blogDetailVO);
            //md格式转换
            blogDetailVO.setBlogContent(MarkDownUtil.mdToHtml(blogDetailVO.getBlogContent()));
            BlogCategory blogCategory=blogCategoryDao.selectByPrimaryKey(blog.getBlogCategoryId());
            if(blogCategory !=null){
                blogCategory = new BlogCategory();
                blogCategory.setCategoryId(0);
                blogCategory.setCategoryName("默认分类");
                blogCategory.setCategoryIcon("/admin/dist/img/category/00.png");
            }
            //分类信息
            blogDetailVO.setBlogCategoryIcon(blogCategory.getCategoryIcon());
            if(!StringUtils.isEmpty(blog.getBlogTags())){
                List<String> tags= Arrays.asList(blog.getBlogTags().split(","));
                blogDetailVO.setBlogTags(tags);
            }
            return blogDetailVO;
        }
        return null;
    }

    @Override
    public BlogDetailVO getBlogDetail(Long blogId) {
        Blog blog=blogDao.selectByPrimaryKey(blogId);
        //不为空且状态已经发布
        BlogDetailVO blogDetailVO=getBlogDetailVO(blog);
        if (blogDetailVO !=null){
            return blogDetailVO;
        }
        return null;
    }

    @Override
    public int getTotalBlogs() {
        return blogDao.getTotalBlogs(null);
    }

    @Override
    public List<Blog> getBlogListByCategory(Integer categoryId) {
        Map params = new HashMap();
        if(categoryId>0){
            params.put("blogStatus",1);
            params.put("blogCategoryId",categoryId);
            PageUtil pageUtil=new PageUtil(params);
            List<Blog> blogs=blogDao.findBlogList(params);
            return blogs;
        }else{
            return null;
        }
    }
}
