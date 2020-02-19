package com.java.website.myblog.controller.admin;

import com.java.website.myblog.controller.common.Result;
import com.java.website.myblog.controller.common.ResultGenerator;
import com.java.website.myblog.entity.Blog;
import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.service.BlogCategoryService;
import com.java.website.myblog.service.BlogService;
import com.java.website.myblog.util.MyBlogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Resource
    private BlogService blogService;


    @GetMapping("/blogs/edit")
    public String edit(HttpServletRequest request){
        List<BlogCategory> blogCategories=blogService.getAllBlogCategory();
        request.setAttribute("categories",blogCategories);
        request.setAttribute("path","edit");
        return "admin/edit";
    }
    @PostMapping("/blogs/md/uploadfile")
    public void uploadFileByEditormd(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam(name = "editormd-image-file",required = true)
                                     MultipartFile file) throws IOException, URISyntaxException{
//        String FILE_UPLOAD_DIC = "/home/project/upload/";
        String FILE_UPLOAD_DIC ="D:\\SourceControl\\Git\\angularStudy\\myblog\\upload\\";
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random random=new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(random.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        //创建文件
        File destFile = new File(FILE_UPLOAD_DIC+newFileName);
        String fileUrl = MyBlogUtils.getHost(new URI(request.getRequestURL() + ""))+"/upload/"+newFileName;
        File fileDirectory = new File(FILE_UPLOAD_DIC);
        try{
            if(!fileDirectory.exists()){
                if(!fileDirectory.mkdir()){
                    throw new IOException("文件夹创建失败，路径为"+fileDirectory);
                }
            }
            file.transferTo(destFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            response.getWriter().write("{\"success\": 1, \"message\":\"success\",\"url\":\"" + fileUrl + "\"}");
        } catch (UnsupportedEncodingException e) {
            response.getWriter().write("{\"success\":0}");
        } catch (IOException e) {
            response.getWriter().write("{\"success\":0}");
        }
    }

    @PostMapping("/blogs/save")
    @ResponseBody
    public Result save(@RequestParam("blogTitle") String blogTitle,
                       @RequestParam(name="blogSubUrl",required = false) String blogSubUrl,
                       @RequestParam("blogCategoryId") Integer blogCategoryId,
                       @RequestParam("blogTags") String blogTags,
                       @RequestParam("blogContent") String blogContent,
                       @RequestParam("blogCoverImage") String blogCoverImage,
                       @RequestParam("blogStatus") Byte blogStatus,
                       @RequestParam("enableComment") Byte enableComment){
        if(StringUtils.isEmpty(blogTitle)){
            return ResultGenerator.genFailResult("请输入文章标题");
        }
        if(blogTitle.trim().length()>150){
            return ResultGenerator.genFailResult("标题过长");
        }
        if(StringUtils.isEmpty(blogTags)){
            return ResultGenerator.genFailResult("请输入文章标签");
        }
        if(blogTags.trim().length()>150){
            return ResultGenerator.genFailResult("标签过长");
        }
        if(blogSubUrl.trim().length()>150){
            return ResultGenerator.genFailResult("路径过长");
        }
        if(StringUtils.isEmpty(blogContent)){
            return ResultGenerator.genFailResult("请输入文章内容");
        }
        if(blogContent.trim().length()>100000){
            return ResultGenerator.genFailResult("文章内容过长");
        }
        if(StringUtils.isEmpty(blogCoverImage)){
            return ResultGenerator.genFailResult("封面图不能为空");
        }
        Blog blog = new Blog();
        blog.setBlogTitle(blogTitle);
        blog.setBlogSubUrl(blogSubUrl);
        blog.setBlogCategoryId(blogCategoryId);
        blog.setBlogTags(blogTags);
        blog.setBlogContent(blogContent);
        blog.setBlogCoverImage(blogCoverImage);
        blog.setBlogStatus(blogStatus);
        blog.setEnableComment(enableComment);
        String saveBlogResult = blogService.saveBlog(blog);
        if("success".equals(saveBlogResult)){
            return ResultGenerator.genSuccessResult("添加成功");
        }else{
            return ResultGenerator.genFailResult(saveBlogResult);
        }
    }



}
