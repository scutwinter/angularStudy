package com.winter.highlight_spring4.ch1.di;
import org.springframework.stereotype.Service;
@Service //1 使用@Service注解声明当前类是Spring管理的一个Bean：被Spring管理的JAVA对象
public class FunctionService {
    public String sayHello(String Word){
        return "Hello " + Word + "!";
    }
}
