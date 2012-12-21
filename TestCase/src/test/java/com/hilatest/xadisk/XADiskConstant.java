package com.hilatest.xadisk;

import java.io.File;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;
import org.xadisk.bridge.proxies.interfaces.XAFileSystem;
import org.xadisk.bridge.proxies.interfaces.XAFileSystemProxy;
import org.xadisk.filesystem.standalone.StandaloneFileSystemConfiguration;


public class XADiskConstant {

    public final static String PATH = SystemUtils.getJavaIoTmpDir()+File.separator+"xadisk";
    
    public final static String DATA_PATH = SystemUtils.getJavaIoTmpDir()+File.separator+"data";
    
    private static Logger logger = Logger.getLogger(XADiskConstant.class);
    static{
        logger.info("system path:"+PATH);
        logger.info("data path path:"+DATA_PATH);
    }
    
    public static XAFileSystem generateXAFileSystem(String systemPath,String id){
		XAFileSystem xafs = null;

        System.out.println("data file::"+systemPath);
  
            StandaloneFileSystemConfiguration configuration = 
            new StandaloneFileSystemConfiguration(systemPath, id);
            //configuration.setSynchronizeDirectoryChanges(false);
            xafs = XAFileSystemProxy.bootNativeXAFileSystem(configuration);
            
            System.out.println("\nBooting XADisk...\n");
           

            System.out.println("\nXADisk is now available for use.\n");
        
            return xafs;
      
	}
    
    public static XAFileSystem generateXAFileSystem(){	    
            return generateXAFileSystem(PATH,"id-1");
      
	}
    
    
    public static XAFileSystem generateXAFileSystem(String id){	    
        return generateXAFileSystem(PATH,id);
  
}
}
