package com.winter.highlight_springmvc4.web.ch4_3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winter.highlight_springmvc4.domain.DemoObj;

@RestController //1 使用@RestController,声明是控制器，并且返回数据时不需要@ResponseBody.
@RequestMapping("/rest")
public class DemoRestController {
    //2 返回数据的媒体类型为json
    @RequestMapping(value = "/getjson",produces = {"application/json;charset=UTF-8"})
    public DemoObj getjson(DemoObj obj){
        //3 直接返回对象，对象会自动转成JSON
        return new DemoObj(obj.getId()+1,obj.getName()+"yy");
    }
    //4 返回数据的媒体类型为xml
    @RequestMapping(value="/getxml",produces ={"application/xml;charset=UTF-8"})
    public DemoObj getxml(DemoObj obj){
        //5 直接返回对象，对象自动转成xml
        return new DemoObj(obj.getId()+1,obj.getName()+"yy");
    }
}
