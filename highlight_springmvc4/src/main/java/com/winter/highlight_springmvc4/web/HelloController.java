package com.winter.highlight_springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //1 利用@Controller注解声明是一个控制类
public class HelloController {
    @RequestMapping("/index") //2 利用@RequestMapping配置URL和方法之间的映射
    public String hello(){
        return "index"; //3 通过上面的ViewResolver的Bean配置，返回值为index,说明页面放置的路径为/WEB-INF/classes/views/index.jsp
    }
}
