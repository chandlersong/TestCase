package org.springexample.lifecycle;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationInitialListener.xml")
public class ContentInitialListener {

    private static Logger logger = Logger.getLogger(ContentInitialListener.class);

    /**
     * the listener is listen to the refresh event, there's no initial event.
     * only have refresh event which will be called when content initial and refresh.
     */
    @Test
    public void testInitial() {
        logger.info("testInitial start run");

    }
}
