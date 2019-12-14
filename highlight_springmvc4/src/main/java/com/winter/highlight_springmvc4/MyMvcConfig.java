package com.winter.highlight_springmvc4;

import com.winter.highlight_springmvc4.interceptor.DemoInterceptor;
import com.winter.highlight_springmvc4.messageconverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Configuration
@EnableWebMvc // 1 @EnableWebMvc开启SpringMVC支持，若无此句，重写WebMvcConfigurerAdapter方法无效
@EnableScheduling
@ComponentScan("com.winter.highlight_springmvc4")
public class MyMvcConfig implements WebMvcConfigurer {//2 继承WebMvcConfigurerAdapter
    /*从Spring 5开始，WebMvcConfigure接口包含了WebMvcConfigurerAdapter类中所有方法的默认实现,此处
      直接使用implements WebMvcConfigurer
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //3 addResourceLocations指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }
    @Bean
    // 配置拦截器的Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }
    @Override
    // 重写addInterceptors方法，注册拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(demoInterceptor());
    }

    @Override
    // 重写addViewControllers来实现快速进行页面转向，这样是代码更简洁，管理更集中
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/index").setViewName("/index");
        //添加转向upload页面的viewcontroller
        registry.addViewController("/toUpload").setViewName("/upload");
        //添加转向converter页面的viewController
        registry.addViewController("/converter").setViewName("/converter");
        //添加转向see页面的viewController
        registry.addViewController("/sse").setViewName("/sse");

        registry.addViewController("/async").setViewName("/async");
    }

    @Override
    // 重写configurePathMatch方法可不忽略"."后面的参数
    // http://localhost:8080/highlight_springmvc4/anno/pathvar/xx.yy 如果不重写这个方法，"."后面的yy被忽略
    public void configurePathMatch(PathMatchConfigurer configurer){
        configurer.setUseSuffixPatternMatch(false);
    }

    @Bean
    // MultipartResolver配置，处理文件上传
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    /**配置自定义的HttpMessageConverter的Bean
     * 有两种方法:
     * 1）configureMessageConverters:重载会覆盖掉spring Mvc默认注册的多个HttpMessageConverter
     * 2) extendMessageConverters:仅添加一个自定义的HttpMessageConverter，不覆盖默认注册的HttpMessageConverter
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(converter());
    }
    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }

}
