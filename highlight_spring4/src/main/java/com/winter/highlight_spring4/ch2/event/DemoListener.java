package com.winter.highlight_spring4.ch2.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<DemoEvent> {//1实现ApplicationListener接口
    public void onApplicationEvent(DemoEvent event){//实现接口的onApplicationEvent方法对消息进行接受处理
        String msg=event.getMsg();
        System.out.println("我(bean-demoListener)接收到Bean-demoPublisher发布的消息："+ msg);
    }
}
