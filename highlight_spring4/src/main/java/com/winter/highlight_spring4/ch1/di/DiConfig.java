package com.winter.highlight_spring4.ch1.di;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //1 声明当前类是一个配置类
@ComponentScan("com.winter.highlight_spring4.ch1.di") //2 自动扫描包名下面所有使用@Service、@Component、@Repository和@Controller,并注册Bean
public class DiConfig {
}
