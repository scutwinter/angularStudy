package com.java.website.myblog.controller.blog;

import com.java.website.myblog.service.BlogService;
import com.java.website.myblog.service.TagService;
import com.java.website.myblog.util.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyBlogController {

    @Resource
    private BlogService blogService;
    @Resource
    private TagService tagService;

    @GetMapping({"/","/index","index.html"})
    public String index(HttpServletRequest request){
        return this.page(request,1);
    }

    @GetMapping({"/page/{pageNum"})
    public String page(HttpServletRequest request, @PathVariable("pageNum") int pageNum){
        PageResult blogPageResult = blogService.getBlogsForIndexPage(pageNum);
        if(blogPageResult ==null ){
            return "error/error_404";
        }
        request.setAttribute("blogPageResult",blogPageResult);
        request.setAttribute("newBlogs",blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs",blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags",tagService.getBlogTagCountForIndex());
        request.setAttribute("pageName","首页");
        return "blog/index";
    }
}
