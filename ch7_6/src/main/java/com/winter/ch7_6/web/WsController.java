package com.winter.ch7_6.web;

import com.winter.ch7_6.domain.WinterMessage;
import com.winter.ch7_6.domain.WinterResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
    @MessageMapping("/welcome")//1 当浏览器向服务器端发送请求时，通过@MessageMapping映射/welcome这个地址，类似@RequestMapping
    @SendTo("/topic/getResponse")//2 当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
    public WinterResponse say(WinterMessage message) throws Exception{
        Thread.sleep(3000);
        return new WinterResponse("Welcome,"+message.getName()+"!");
    }
}
