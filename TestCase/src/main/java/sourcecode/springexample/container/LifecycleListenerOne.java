package sourcecode.springexample.container;

import org.apache.log4j.Logger;
import org.springframework.context.Lifecycle;


public class LifecycleListenerOne implements Lifecycle {

    private static Logger logger = Logger.getLogger(LifecycleListenerOne.class);
    
    public void start() {
        logger.info("life cycle Listener One start");
    }

    public void stop() {
        logger.info("life cycle Listener One stop");

    }

    public boolean isRunning() {
        logger.info("life cycle Listener is Running");
        return false;
    }

}
