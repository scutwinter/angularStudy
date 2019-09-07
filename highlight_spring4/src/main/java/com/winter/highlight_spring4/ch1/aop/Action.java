package com.winter.highlight_spring4.ch1.aop;
//编写拦截规则的注解，注解本身没有什么功能，注解就是配置
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//注解的功能来自用这个注解的地方
@Documented
public @interface Action {
    String name();
}
