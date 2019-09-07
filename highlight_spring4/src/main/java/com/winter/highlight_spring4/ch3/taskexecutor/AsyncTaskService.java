package com.winter.highlight_spring4.ch3.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

//任务执行类
@Service
public class AsyncTaskService {
    //1 通过@Async 注解表明该方法是异步方法，如果注解在类级别，则表明该类所有的方法都是异步方法，
    // 而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
    @Async
    public void executeAsyncTask(Integer i){
        System.out.println("执行异步任务"+i);
    }
    @Async
    public void executeAsyncTaskPlus(Integer i){
        System.out.println("执行异步任务+1:"+(i+1));
    }
}
