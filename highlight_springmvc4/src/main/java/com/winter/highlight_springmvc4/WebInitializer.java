package com.winter.highlight_springmvc4;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;


public class WebInitializer implements WebApplicationInitializer {
    //1 WebApplicationInitializer 是spring提供用来配置Servlet3.0+配置的接口，从而实现了
    //替代web.xml的位置。实现此接口将会自动被SpringServletContainerInitializer(用来启动Servlet3.0容器)获取到

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context =new AnnotationConfigWebApplicationContext();
        context.register(MyMvcConfig.class);
        context.setServletContext(servletContext);//2 新建WebApplicationContext，注册配置类，并将和其当前servletContext关联

        //3 注册Spring MVC的DispatcherServlet
        Dynamic servlet=servletContext.addServlet("dispatcher",new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);//开启异步方法支持
    }
}
