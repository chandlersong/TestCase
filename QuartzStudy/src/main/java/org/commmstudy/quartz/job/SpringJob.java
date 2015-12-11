package org.commmstudy.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringJob implements ApplicationContextAware, Job {

    private static Logger log = Logger.getLogger(SpringJob.class);;

    private ApplicationContext springContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;

    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

        if (springContext == null) {
            log.info("I'm not created by spring");
        } else {
            log.info("I'm created by spring");
        }

    }

    public void work() throws JobExecutionException {

        if (springContext == null) {
            log.info("I'm not created by spring");
        } else {
            log.info("I'm created by spring");
        }

    }

}
