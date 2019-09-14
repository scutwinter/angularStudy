package com.winter.highlight_springmvc4.web.ch4_5;

import com.winter.highlight_springmvc4.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class AysncController {

    /**
     * 1 使用@Autowired将PushService的实体Bean注入到AysnController,在PushService里产生DeferredResult给控制器使用
     * 通过@Scheduled注解的方法定时更新DeferredResult
     */
    @Autowired
    PushService pushService;

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredResult(){
        return pushService.getAsyncUpdate();
    }
}
