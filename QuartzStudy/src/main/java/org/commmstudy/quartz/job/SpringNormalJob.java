package org.commmstudy.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SpringNormalJob extends QuartzJobBean implements ApplicationContextAware {

    private static Logger log = Logger.getLogger(SpringNormalJob.class);

    private ApplicationContext springContext;

    private String value1;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String value2 = context.getJobDetail().getJobDataMap().getString("value2");
        log.info("value1:" + value1);
        log.info("value2:" + value2);

        if (springContext == null) {
            log.info("I'm not created by spring");
        } else {
            log.info("I'm created by spring");
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

}
