package com.hqjy.tiku.task;

import com.hqjy.tiku.task.schedule.ScheduledConfig;
import com.hqjy.tiku.task.schedule.TaskWrapper;
import com.hqjy.tiku.task.support.DefaultTask;
import com.hqjy.tiku.task.webservice.SendTeacherList;
import com.hqjy.tiku.task.webservice.SendTeacherListResponse;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.hqjy.tiku.task.repository")
@EnableTransactionManagement
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(){
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(SendTeacherList.class, SendTeacherListResponse.class);
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        return webServiceTemplate;
    }

    @Bean
    public DefaultTask defaultTask(){
        return new DefaultTask();
    }

    @Bean
    public ScheduledConfig scheduledConfig(){
        ScheduledConfig scheduledConfig = new ScheduledConfig();
        TaskWrapper taskWrapper = new TaskWrapper();
        taskWrapper.setId(123);
        taskWrapper.setCron("0/5 * * * * *");
        taskWrapper.setFlag(true);
        taskWrapper.setRunnable(defaultTask());
        scheduledConfig.addTask(taskWrapper);
        return scheduledConfig;
    }

}
