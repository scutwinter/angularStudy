package com.winter.highlight_springmvc4.web.ch4_5;

import com.winter.highlight_springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConverterController {
    //1 指定返回的媒体类型为我们自定义的媒体类型
    @RequestMapping(value="/convert",produces = {"application/x-winter"})
    public @ResponseBody DemoObj convert(@RequestBody DemoObj demoObj){
        return demoObj;
    }
}
