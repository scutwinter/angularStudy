package com.winter.highlight_spring4.ch2.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher {
    @Autowired
    ApplicationContext applicationContext; //1 注入ApplicationContext用于发布事件
    public void publish(String msg){
        //2使用applicationContext的publishEvent方法来发布
        applicationContext.publishEvent(new DemoEvent(this,msg));
    }
}
