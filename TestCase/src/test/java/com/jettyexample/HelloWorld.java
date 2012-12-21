package com.jettyexample;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;

/**
 * Jetty基本的例子
 * @author Chandler.Song
 *
 */
public class HelloWorld {
    
    private static Logger logger = Logger.getLogger(HelloWorld.class);
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.start();
        server.join();
    }
    
    @Test
    public void HelloWorldTest() throws Exception{
        Server server = new Server(8080);
        server.start();
        server.join(); 
        
        server.stop();
    }
    
    /**
     * 如果跑一个用code，来部署一个项目
     * @throws Exception
     */
    @Test
    public void CodeWebAppTest() throws Exception{
        Server server = new Server(8080);
     
        
        
        WebAppContext  context = new WebAppContext();
        context.setDescriptor("target/classes/web.xml");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
 
        server.setHandler(context);
        
        
        server.start();
        server.join(); 
        server.stop();
    }
    
    
    /**
     * 如果跑一个用code，来部署一个项目
     * 集成Spring
     * @throws Exception
     */
    @Test
    public void CodeWebAppSpringTest() throws Exception{
        Server server = new Server(8080);
     
        
        
        SimpleWebAppContext  context = new SimpleWebAppContext();
        //web.xml文件
        context.setDescriptor("web_spring.xml");
       
        server.setHandler(context);
        
        logger.info("start server");
        server.start();
        //server.join(); 
        server.stop();
    }

}
