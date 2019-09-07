package com.winter.highlight_spring4.ch1.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args){
        //1
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AopConfig.class);

        DemoAnnotationService demoAnnotationService=context.getBean(DemoAnnotationService.class);

        DemoMethodService demoMethodService=context.getBean(DemoMethodService.class);

        demoAnnotationService.add();

        demoMethodService.add();

        context.close();
    }
}
