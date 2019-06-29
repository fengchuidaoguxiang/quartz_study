package com.main;

import com.job.HelloJobListener;
import com.listener.MySchedulerListener;
import com.listener.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;

public class HelloSchedulerDemoSchedulerListener {

    public static void main(String[] args) throws Exception {
        // 1. 调度器（Scheduler）,从工厂中获取调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //设置任务的开始时间
        Date startDate = new Date();
        //任务的开始时间推迟3秒
        startDate.setTime(startDate.getTime()+3000);
        //设置任务的结束时间
        Date endDate = new Date();
        //任务的结束时间推迟5秒
        endDate.setTime(endDate.getTime()+10000);

        // 2. 任务实例（JobDetail）
        JobDetail jobDetail = JobBuilder.newJob(HelloJobListener.class)//加载任务类，与HelloJob类完成绑定，要求HelloJob类实现Job接口
                .withIdentity("job1","group1")//参数1：任务的名称（唯一实例）； 参数2：任务组的名称（默认组：DEAFAULT）
                .build();
        // 3.触发器（Trigger）
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","triggerGroup1")//参数1：触发器的名称（唯一实例）； 参数2：触发器组的名称
                .startNow()//马上开始
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5).withRepeatCount(5))//日历 每5秒执行一次
                .build();
        //让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        scheduler.scheduleJob(jobDetail,trigger);

        //创建SchedulerListener
        scheduler.getListenerManager().addSchedulerListener(new MySchedulerListener());

        //移除SchedulerListener
//        scheduler.getListenerManager().removeSchedulerListener(new MySchedulerListener());
        //启动
        scheduler.start();
        //延迟7秒后关闭
        Thread.sleep(7000L);
        //关闭
      scheduler.shutdown();
    }
}
