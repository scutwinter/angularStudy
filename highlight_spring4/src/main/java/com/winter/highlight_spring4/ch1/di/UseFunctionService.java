package com.winter.highlight_spring4.ch1.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service //1 使用@Service注解声明当前类是Spring管理的一个Bean：被Spring管理的JAVA对象
public class UseFunctionService {
    @Autowired //2 使用@Autowired将FunctionService的实体Bean注入到UseFunctionService中
    FunctionService functionService;

    public String SayHello(String word){
        return functionService.sayHello(word);
    }
}
