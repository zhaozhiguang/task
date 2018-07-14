package com.hqjy.tiku.task.support;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * 配置文件操作类
 * 读取配置/写入配置
 * @author zhaozhiguang
 */
@Slf4j
public class ConfigUtil {

    /**
     * 路径
     */
    private static final String path = System.getProperty("user.home");

    private static final String fileName = "tikuSyncConfig.properties";

    private static final File file = new File(path, fileName);

    private static final Properties properties = new Properties();

    static {
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("配置文件创建失败", e);
            }
        }
        try (
            FileReader fileReader = new FileReader(file)
        ) {
            properties.load(fileReader);
        } catch (IOException e) {
            log.error("关联配置文件失败", e);
        }
    }

    /**
     * 读取时间
     * @return
     */
    public static Date read(){
        String currentDate = properties.getProperty("currentDate");
        if(currentDate == null){
            return DateUtil.parseDateTime("2014-01-01 00:00:00", DateUtil.DATE_TIME_FORMAT);
        }
        return DateUtil.parseDateTime(currentDate, DateUtil.DATE_TIME_FORMAT);
    }

    /**
     * 写入时间
     */
    public static void write(){
        String currentDate = DateUtil.format(new Date(), DateUtil.DATE_TIME_FORMAT);
        properties.setProperty("currentDate", currentDate);
        try (
            FileWriter fileWriter = new FileWriter(file)
        ) {
            properties.store(fileWriter, "更新写入时间");
        } catch (IOException e) {
            log.error("更新配置文件失败", e);
        }
    }


}
