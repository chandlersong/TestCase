package sourcecode.springexample.container;

import org.apache.log4j.Logger;
import org.springframework.context.Lifecycle;


public class LifecycleListenerTwo implements Lifecycle {

    private static Logger logger = Logger.getLogger(LifecycleListenerTwo.class);
    
    public void start() {
        logger.info("life cycle Listener Two start");
    }

    public void stop() {
        logger.info("life cycle Listener Two stop");

    }

    public boolean isRunning() {
        logger.info("life cycle Listener Two is Running");
        return false;
    }

}
