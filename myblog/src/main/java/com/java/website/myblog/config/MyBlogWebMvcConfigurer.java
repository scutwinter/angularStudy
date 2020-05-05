package com.java.website.myblog.config;

import com.java.website.myblog.controller.common.Constants;
import com.java.website.myblog.entity.BlogConfig;
import com.java.website.myblog.interceptor.AdminLoginInterceptor;
import com.java.website.myblog.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyBlogWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Resource
    private ThymeleafViewResolver thymeleafViewResolver;

    @Resource
    private ConfigService configService;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对该拦截器所拦截的路径进行配置，由于后端管理系统的所有请求路径都以 /admin 开头，所以拦截的路径为 /admin/** ，但是登陆页面以及部分静态资源文件也是以 /admin 开头，所以需要将这些路径排除
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/dist/**").excludePathPatterns("/admin/plugins/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //增加一个自定义静态资源映射，使得 upload 下的静态资源可以通过该映射地址被访问到
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/winter/source/gitSource/myblog/upload/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        if (thymeleafViewResolver !=null){
            Map<String,Object> vars=new HashMap<>(1);
            vars.put("configurations",configService.getAllConfigs());
            thymeleafViewResolver.setStaticVariables(vars);
        }
        WebMvcConfigurer.super.configureViewResolvers(registry);

    }
}
