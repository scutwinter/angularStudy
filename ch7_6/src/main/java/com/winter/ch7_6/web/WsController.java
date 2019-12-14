package com.winter.ch7_6.web;

import com.winter.ch7_6.domain.WinterMessage;
import com.winter.ch7_6.domain.WinterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;// 通过SimpMessagingTemplate向浏览器发送消息
    @MessageMapping("/welcome")//1 当浏览器向服务器端发送请求时，通过@MessageMapping映射/welcome这个地址，类似@RequestMapping
    @SendTo("/topic/getResponse")//2 当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
    public WinterResponse say(WinterMessage message) throws Exception{
        Thread.sleep(3000);
        return new WinterResponse("Welcome,"+message.getName()+"!");
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal,String msg){// 在SpringMVC中，可以直接在参数中获得principal,principal总包含当前用户的信息
        if (principal.getName().equals("wyf")){ // 根据发送人确定被发送人
            // 通过messagingTemplate.convertAndSendToUser向用户发送消息，第一个参数是接收消息的用户，第二个是浏览器订阅的地址，第三个是消息本体
            messagingTemplate.convertAndSendToUser("winter","/queue/notifications",principal.getName()+"-send:"+msg);
        }else{
            messagingTemplate.convertAndSendToUser("wyf","/queue/notifications",principal.getName()+"-send:"+msg);
        }
    }
}
