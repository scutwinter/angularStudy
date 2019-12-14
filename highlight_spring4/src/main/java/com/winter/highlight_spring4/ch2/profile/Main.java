package com.winter.highlight_spring4.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod"); //1 此处配置为生产环境，如果setActiveProfiles("dev")则是开发环境配置
        context.register(ProfileConfig.class);//后置注册Bean配置类
        context.refresh();//刷新容器

        DemoBean demoBean=context.getBean(DemoBean.class);
        System.out.println(demoBean.getContent());

        context.close();
    }
}
