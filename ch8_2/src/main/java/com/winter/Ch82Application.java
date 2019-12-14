package com.winter;

import com.winter.dao.PersonRepository;
import com.winter.support.CustomRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//在配置类上配置@EnableJpaRepositories，并指定repositoryFactoryBeanClass，让自定义的Repository实现起效
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class Ch82Application {
    @Autowired
    PersonRepository personRepository;


    public static void main(String[] args) {
        SpringApplication.run(Ch82Application.class, args);
    }

}
