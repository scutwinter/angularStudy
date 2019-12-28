package com.java.website.myblog.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties = new Properties();

        properties.put("kaptcha.border","no");

        properties.put("kaptcha.textproducer.font.color","black");

        properties.put("kaptcha.image.width","160");

        properties.put("kaptcha.image.height","40");

        properties.put("kaptcha.textproducer.font.size","30");

        properties.put("kaptcha.textproducer.font.space","5");

        properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
