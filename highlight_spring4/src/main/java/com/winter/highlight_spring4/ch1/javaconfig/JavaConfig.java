package com.winter.highlight_spring4.ch1.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //1 声明当前类是一个配置类
public class JavaConfig {
    @Bean //2使用@Bean注解声明当前方法的返回值是一个Bean,Bean的名称是方法名
    public FunctionService functionService(){
        return new FunctionService();
    }

    @Bean
    public UseFunctionService useFunctionService(){
        UseFunctionService useFunctionService = new UseFunctionService();
        //3注入FunctionService的Bean时候直接调用functionService
        useFunctionService.setFunctionService(functionService());
        return useFunctionService;
    }

    @Bean
    //4另外一种注入方式是直接将FunctionService作为参数传给useFunctionService()，
    // 在Spring容器中，只要容器存在某个Bean，就能在另外一个Bean的声明方法的参数中注入
    public UseFunctionService useFunctionService(FunctionService functionService){
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService);
        return useFunctionService;
    }
}
