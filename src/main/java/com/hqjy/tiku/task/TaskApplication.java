package com.hqjy.tiku.task;

import com.hqjy.tiku.task.schedule.ScheduledConfig;
import com.hqjy.tiku.task.schedule.TaskWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.hqjy.tiku.task.repository")
@EnableTransactionManagement
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

    @Bean
    public ScheduledConfig scheduledConfig(){
        ScheduledConfig scheduledConfig = new ScheduledConfig();
        TaskWrapper taskWrapper = new TaskWrapper();
        taskWrapper.setId(123);
        taskWrapper.setCron("0/5 * * * * *");
        taskWrapper.setFlag(true);
        taskWrapper.setRunnable(() -> {
            System.err.println("执行----" + new Date());
        });
        scheduledConfig.addTask(taskWrapper);
        new Thread(() -> {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
            }
            System.err.println("修改执行时间");
            taskWrapper.setCron("0/10 * * * * *");
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(25000);
            } catch (InterruptedException e) {
            }
            System.err.println("暂停");
            taskWrapper.setFlag(false);
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(40000);
            } catch (InterruptedException e) {
            }
            System.err.println("开始");
            taskWrapper.setFlag(true);
        }).start();
        return scheduledConfig;
    }

}
