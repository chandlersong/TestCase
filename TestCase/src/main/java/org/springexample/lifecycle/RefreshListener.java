package org.springexample.lifecycle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class RefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = Logger.getLogger(RefreshListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("content refresh");
    }

}
