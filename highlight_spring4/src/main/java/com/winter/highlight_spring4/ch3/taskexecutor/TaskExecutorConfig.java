package com.winter.highlight_spring4.ch3.taskexecutor;

import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan("com.winter.highlight_spring4.ch3.taskexecutor")
@EnableAsync //1 此注解开启异步任务支持
public class TaskExecutorConfig implements AsyncConfigurer { //2 配置类实现AsyncConfigurer

    @Override
    public Executor getAsyncExecutor(){
        //2 重写getAsyncExecutor方法，并返回一个ThreadPoolTaskExecutor,从而获取一个基于线程池TaskExecutor
        ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        taskExecutor.setCorePoolSize(5);
        //线程池维护线程的最大数量
        taskExecutor.setMaxPoolSize(10);
        //线程池所使用的缓冲队列
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return  taskExecutor;
    }
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return  null;
    }


}
