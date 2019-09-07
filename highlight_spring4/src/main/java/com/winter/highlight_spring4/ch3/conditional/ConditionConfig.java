package com.winter.highlight_spring4.ch3.conditional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {
    @Bean
    @Conditional(WindowsCondition.class) //1 通过@Conditional 注解，符合windows条件则实例化windowsListService
    public ListService windowsListService(){
        return new WindowsListService();
    }
    @Bean //使用@Bean注解当前返回一个Bean
    @Conditional(LinuxCondition.class)  //2 通过@Conditional 注解，符合Linux条件则实例化linuxListService
    public ListService linuxListService(){
        return new LinuxListService();
    }
}
