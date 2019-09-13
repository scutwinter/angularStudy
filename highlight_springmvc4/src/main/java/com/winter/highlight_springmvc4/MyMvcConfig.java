package com.winter.highlight_springmvc4;

import com.winter.highlight_springmvc4.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc // 1 @EnableWebMvc开启SpringMVC支持，若无此句，重写WebMvcConfigurerAdapter方法无效
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
}
