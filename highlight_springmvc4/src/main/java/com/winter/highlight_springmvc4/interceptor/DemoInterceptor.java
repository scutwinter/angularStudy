package com.winter.highlight_springmvc4.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1 继承HandlerInterceptorAdapter类来实现自定义拦截器
public class DemoInterceptor extends HandlerInterceptorAdapter {
    @Override
    //2 重写preHandle方法，在请求发生前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        long startTime=System.currentTimeMillis();
        //setAttribute(String name,Object o)方法，将数据作为request对象的一个属性存放到request对象中
        request.setAttribute("startTime",startTime);
        return true;
    }

    @Override
    //3 重写postHandle方法，在请求完成后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        //getAttribute(String name)方法，获取request对象的name属性的属性值
        long startTime=(Long)request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime=System.currentTimeMillis();
        System.out.println("本次请求处理的时间为:"+new Long(endTime-startTime)+"ms");
        request.setAttribute("handlingTime",endTime-startTime);

    }

}
