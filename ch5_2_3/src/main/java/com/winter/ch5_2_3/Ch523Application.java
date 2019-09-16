package com.winter.ch5_2_3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * @SpringBootApplication 是Spring Boot项目的核心注解，主要目的就是开启自动配置。
 * @SpringBootApplication 注解组合了@Configuration、@EnableAutoConfiguration、@ComponentScan
 * @EnableAutoConfiguration 让SpringBoot根据类路径中的jar包依赖为当前项目的进行自动配置
 * SpringBoot会自动扫描@SpringBootApplication所在类的同级包以及下级包里的Bean，建议入口类放置的位置在groupid+arctifactID
 * 组合包名下
 * 关闭特定的自动配置应该使用@SpringBootApplication注解的exclude参数
 * @SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
 */
@SpringBootApplication
public class Ch523Application {

    @Value("${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;

    @RequestMapping("/")
    String index(){
        return "Hello Spring Boot,book name is:"+bookName+" and book author is:"+bookAuthor;
    }

    public static void main(String[] args) {

//        SpringApplication.run(Ch523Application.class, args);

        SpringApplication app=new SpringApplication(Ch523Application.class);
        /**
         *  app.setShowBanner(false);这个是老方法不显示Banner,新方法是setBannerMode
         */
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

}
