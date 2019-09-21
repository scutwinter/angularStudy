package com.winter.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
/**
 * 1 通过@EnableWebSocketMessageBroker注解开启使用STOMP协议来传输基于代理（message broker）的消息
 *   这时控制器支持使用@MessageMapping，就像使用@RequestMapping一样
 */
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    /**
     * 2 注册STOMP协议的节点（endpoint）,并映射到指定的url
     */
    public void registerStompEndpoints(StompEndpointRegistry registry){
        //3 注册一个STOMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/endpointWinter").withSockJS();
        // 注册一个名为/endpointChat的endpoint
        registry.addEndpoint("/endpointChat").withSockJS();
    }

    @Override
    //4 配置消息代理（Message Broker）
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //5 广播式应配置一个/topic消息代理
        //点对点式应增加一个/queue消息代理
        registry.enableSimpleBroker("/queue","/topic");
    }
}
