package com.hqjy.tiku.task.schedule;

import lombok.Data;
import org.springframework.scheduling.Trigger;

/**
 * 定时任务包装类
 * @author zhaozhiguang
 * @see ScheduledConfig 自定义任务调度
 */
@Data
public class TaskWrapper {

    /**
     * id
     */
    private Integer id;

    /**
     * 是否执行
     */
    private Boolean flag;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 包装的触发条件
     */
    private Trigger trigger;

    /**
     * 包装的任务
     */
    private Runnable runnable;

    public TaskWrapper() {

    }

    /**
     * 设置执行时间
     * @param cron
     */
    public void setCron(String cron){
        this.cron = cron;
        initTrigger(false);
    }

    /**
     * 设置是否执行
     * @param flag
     */
    public void setFlag(Boolean flag){
        this.flag = flag;
        initTrigger(flag);
    }

    /**
     * 初始化触发条件
     */
    private void initTrigger(Boolean flag) {
        this.trigger = triggerContext -> {
            this.trigger = new CustomTrigger(this.cron, flag);
            return this.trigger.nextExecutionTime(triggerContext);
        };
    }

    public TaskWrapper(Task task) {
        runnable = () -> {
            if(flag){
                task.schedule();
            }
        };
    }

    public void setRunnable(Task task) {
        this.runnable = () -> {
            if(flag){
                task.schedule();
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)){
            return super.equals(obj);
        }
        if(!TaskWrapper.class.isInstance(obj)){
            return false;
        }
        if(!this.id.equals(((TaskWrapper)obj).getId())){
            return false;
        }
        return true;
    }

    /**
     * 执行任务函数接口
     */
    public interface Task {

        /**
         * 执行的任务
         */
        void schedule();

    }

}
