package sourcecode.springexample.container;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.jettyexample.HelloWorld;
import com.jettyexample.SimpleWebAppContext;

/**
 * 想要测试一下Start方法被调用的情况
 * @author Chandler.Song
 *
 */
public class ApplicationContentStartTest {
    
    private static Logger logger = Logger.getLogger(HelloWorld.class);
    
    /**
     * 在Web容器中
     * @throws Exception
     */
    @Test
    public void WebTest() throws Exception{
        Server server = new Server(8080);
           
        SimpleWebAppContext  context = new SimpleWebAppContext();
        //web.xml文件
        context.setDescriptor("SpringApplicationContentStart.xml");
       
        server.setHandler(context);
        
        logger.info("start server");
        server.start();
        //server.join(); 
        server.stop();
    }
    
    /**
     * 在Web容器中
     * @throws Exception
     */
    @Test
    public void XMLSpringFactory() throws Exception{
        FileSystemXmlApplicationContext content = new FileSystemXmlApplicationContext("classpath:applicationContextStartListener.xml");
        logger.info("ApplicationContext initial");
        content.start();
        logger.info("ApplicationContext start");
    }
}
