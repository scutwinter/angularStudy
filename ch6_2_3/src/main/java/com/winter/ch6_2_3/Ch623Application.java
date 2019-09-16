package com.winter.ch6_2_3;

import com.winter.ch6_2_3.config.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Ch623Application {

    /**
     * 1 用@Autowired直接注入该配置
     */
    @Autowired
    private AuthorSettings authorSettings;

    @RequestMapping("/")
    public String index(){
        return "author name is "+ authorSettings.getName()+" and author age is "+authorSettings.getAge().toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(Ch623Application.class, args);
    }

}
