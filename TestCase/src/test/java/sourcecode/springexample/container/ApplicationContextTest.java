package sourcecode.springexample.container;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sourcecode.springexample.applicationcontent.StartEventListener;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextStartListener.xml")
public class ApplicationContextTest extends AbstractJUnit4SpringContextTests{
    
    private static Logger logger = Logger.getLogger(ApplicationContextTest.class);
    
    /**
     * 写这个TestCase的目的在于，我看了code，发现AbstractApplicationContext的start方法会抛出一个事件。
     * 我只是想看看以下。默认能不能通过监听这个事件，来看能不能在ApplicationContext启动时，做一些事情。
     * 
     * 但是显示是失败的，那么这个start的作用又是什么呢？
     */
    @Resource
    private StartEventListener startListener;
    
    @Test
    public void testStartEventListener(){
        logger.info("start to listen application start event"); 
        logger.info("startListener:"+startListener); 
        logger.info("applicationContext:"+ this.applicationContext); 
       
        ((AbstractApplicationContext)this.applicationContext).start();
    }

}
