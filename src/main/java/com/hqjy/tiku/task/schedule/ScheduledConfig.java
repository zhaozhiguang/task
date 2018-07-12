package com.hqjy.tiku.task.schedule;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义定时任务调度
 * 可启动,暂停,修改执行周期
 * 支持设置多个任务
 * @author zhaozhiguang
 * @see TaskWrapper 任务包装类
 */
public class ScheduledConfig implements SchedulingConfigurer {

    /**
     * 需要执行任务的集合
     */
    private Set<TaskWrapper> tasks;

    public ScheduledConfig() {

    }

    public ScheduledConfig(Set<TaskWrapper> tasks) {
        this.tasks = tasks;
    }

    /**
     * 设置执行任务集合
     * @param tasks
     */
    public void setTasks(Set<TaskWrapper> tasks) {
        this.tasks = tasks;
    }

    /**
     * 添加执行任务
     * @param taskWrapper
     */
    public void addTask(TaskWrapper taskWrapper) {
        if(tasks == null) tasks = new HashSet<>(16);
        tasks.add(taskWrapper);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        tasks.stream().forEach(taskWrapper -> {
            scheduledTaskRegistrar.addTriggerTask(taskWrapper.getRunnable(), taskWrapper.getTrigger());
        });
    }
}
