package org.commmstudy.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringPOJOJob implements ApplicationContextAware {

    private static Logger log = Logger.getLogger(SpringPOJOJob.class);;

    private ApplicationContext springContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;

    }

    public void work() throws JobExecutionException {

        if (springContext == null) {
            log.info("I'm not created by spring");
        } else {
            log.info("I'm created by spring");
        }

    }

}
