package com.winter.highlight_springmvc4.web.ch4_5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class SseController {
    //1 这里使用输出类型为text/evnet-stream,这是服务器端SSE的支持，本例演示每5秒向浏览器推送随机消息。
    //  produces要添加charset=UTF-8
    @RequestMapping(value="/push",produces = "text/event-stream;charset=UTF-8")
    public @ResponseBody String push(){
        Random r=new Random();
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "data:Testing 1,2,3"+r.nextInt()+"\n\n";
    }
}
