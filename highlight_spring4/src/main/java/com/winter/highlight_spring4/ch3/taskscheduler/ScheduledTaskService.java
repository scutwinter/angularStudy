package com.winter.highlight_spring4.ch3.taskscheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 5000) //1 通过@Scheduled声明该方法是计划任务，使用fixedRate属性每隔固定时间执行
    public void reportCurrentTime(){
        System.out.println("每隔五秒执行一次 "+dateFormat.format(new Date()));
    }

    @Scheduled(cron="0 24 20 ? * *")//2 使用cron属性客按照指定的时间执行,本例子指的是每天11点28分执行；
    //cron是UNIX和类UNIX（linux）系统下的定时任务
    public void fixTimeExecution(){
        System.out.println("在指定事件 "+dateFormat.format(new Date())+"执行");
    }
}
