package com.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class MyTriggerListener implements TriggerListener {

    private String name;

    //自定义传递的触发器的名称
    public MyTriggerListener( String name ){
        this.name = name;
    }

    @Override
    public String getName() {
//        String simpleName = this.getClass().getSimpleName();//获取默认的触发器的名称
        System.out.println("触发器的名称：" + this.name);
        return this.name;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        String name = trigger.getKey().getName();
        System.out.println(name+"被触发");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        String name = trigger.getKey().getName();
       //在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。TriggerListener 给了一个选择去否决 Job 的执行。假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行。
        System.out.println(name + "没有被触发");
        return false;//true 表示不会执行job的方法
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        String name = trigger.getKey().getName();
        //Scheduler 调用这个方法是在Trigge错过触发时
        System.out.println(name + "错过触发被执行此方法");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        String name = trigger.getKey().getName();
        //Trigger被触发并且完成了Job的执行时，Scheduler 调用这个方法
        System.out.println(name + " 完成之后触发");
    }
}
