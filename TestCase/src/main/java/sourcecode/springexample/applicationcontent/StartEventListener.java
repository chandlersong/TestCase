package sourcecode.springexample.applicationcontent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;



public class StartEventListener implements ApplicationListener<ContextStartedEvent> {

    private static Logger logger = Logger.getLogger(StartEventListener.class);
    
    public StartEventListener(){
        logger.info("StartEventListener has been initialed");    
    }
    
    public void onApplicationEvent(ContextStartedEvent event) {
        logger.info("listen application start successfully");       
    }

}
