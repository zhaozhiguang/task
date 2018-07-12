package com.hqjy.tiku.task.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Deprecated
@Component
public class NcDataSyncTask {

    @Scheduled(cron = "5 * * * * ?")
    public void start(){

    }
}
