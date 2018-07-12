package com.hqjy.tiku.task.schedule;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.Date;
import java.util.TimeZone;

/**
 * 自定义触发器
 * 如果是第一次启动,则运行时间从当前时间开始计算
 * @author zhaozhiguang
 */
public class CustomTrigger implements Trigger {

    private String expression;

    private Boolean flag;

    private final CronSequenceGenerator sequenceGenerator;

    public CustomTrigger(String expression, Boolean flag) {
        this.expression = expression;
        this.flag = flag;
        this.sequenceGenerator = new CronSequenceGenerator(expression);
    }

    public CustomTrigger(String expression, TimeZone timeZone, Boolean flag) {
        this.expression = expression;
        this.flag = flag;
        this.sequenceGenerator = new CronSequenceGenerator(expression, timeZone);
    }

    public String getExpression() {
        return this.expression;
    }

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        Date date = triggerContext.lastCompletionTime();
        if (date != null && !this.flag) {
            Date scheduled = triggerContext.lastScheduledExecutionTime();
            if (scheduled != null && date.before(scheduled)) {
                date = scheduled;
            }
        } else {
            this.flag = false;
            date = new Date();
        }
        return this.sequenceGenerator.next(date);
    }

    @Override
    public boolean equals(Object other) {
        return (this == other || (other instanceof CustomTrigger &&
                this.sequenceGenerator.equals(((CustomTrigger) other).sequenceGenerator)));
    }

    @Override
    public int hashCode() {
        return this.sequenceGenerator.hashCode();
    }

    @Override
    public String toString() {
        return this.sequenceGenerator.toString();
    }

}

