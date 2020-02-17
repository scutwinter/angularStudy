package com.java.website.myblog.config;

import com.java.website.myblog.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyBlogWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对该拦截器所拦截的路径进行配置，由于后端管理系统的所有请求路径都以 /admin 开头，所以拦截的路径为 /admin/** ，但是登陆页面以及部分静态资源文件也是以 /admin 开头，所以需要将这些路径排除
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/dist/**").excludePathPatterns("/admin/plugins/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //增加一个自定义静态资源映射，使得 upload 下的静态资源可以通过该映射地址被访问到
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/SourceControl/Git/angularStudy/myblog/upload/");

    }
}
