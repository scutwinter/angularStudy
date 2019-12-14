package com.winter.highlight_spring4.ch2.event;

import org.springframework.context.ApplicationEvent;
//消息的主体
public class DemoEvent extends ApplicationEvent{
    //serialVersionUID用来作为Java对象序列化中的版本标示之用,
    // final所修饰的属性的意思是:必须初始化并且只能初始化一次，以后该属性的值不能改变
    private static final long serialVersionUID = 1L;
    private String msg;

    public DemoEvent(Object source,String msg){
        //super放在构造函数的第一行代码
        super(source);
        this.msg=msg;
    }
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }



}
