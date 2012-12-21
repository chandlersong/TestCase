package com.hilatest.common.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.hilatest.common.io.listener.SimpleListener;

/**
 * 
 * 关于FileAlterationMonitor这个类。我首先的猜想，就是这个类，只是一个容器。
 * 并且这个容器，是一个线程。
 * 
 * @author chandler.song
 *
 */
public class FileAlterationMonitorTest {

	private static final String MonitorFolder = "E:\\Temp";
	
	private static Logger logger = Logger.getLogger(FileAlterationMonitorTest.class);
	
	
	/**
	 *  简单的一个测试。设定一个测试的文件夹。看待下面的方式。
	 * @throws Exception 
	 */
	@Test
	public void testBasic() throws Exception{
		
		FileAlterationMonitor monitor = new FileAlterationMonitor();
		
		File directory = new File(MonitorFolder);
	    FileAlterationObserver observer = new FileAlterationObserver(directory);
	    
	    SimpleListener listener = new SimpleListener();
	    observer.addListener(listener);
	    
	    monitor.addObserver(observer);
	    
	    monitor.start();
   
	    logger.info("start monitoring");
        
        logger.info("new file create");
        this.createFile();
        
        Thread.sleep(10000);
        
        logger.info("stop monitoring");
        monitor.stop();
	}
	    
    /**
     * 测试一下checkandAlert方法
     * @throws Exception 
     */
    @Test
    public void testCheckandAlert() throws Exception{
        
        FileAlterationMonitor monitor = new FileAlterationMonitor();
        
        File directory = new File(MonitorFolder);
        FileAlterationObserver observer = new FileAlterationObserver(directory);
        
        SimpleListener listener = new SimpleListener();
        observer.addListener(listener);
        
        monitor.addObserver(observer);
        
        monitor.start();
   
        logger.info("start monitoring");
        
        logger.info("new file create");
        this.createFile();
        
        observer.checkAndNotify();
        
        logger.info("stop monitoring");
        monitor.stop();
    }
    
    
    public File createFile() throws IOException{
        return this.createFile(RandomStringUtils.randomAlphanumeric(5));
    }
    
    
    public File createFile(String fileName) throws IOException{
        
        File file = new File(MonitorFolder+"\\"+fileName);
        
        if(!file.exists()){
            file.createNewFile();
        }
        return file;
    }
}
