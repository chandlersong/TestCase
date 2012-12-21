package com.hilatest.xadisk;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.xadisk.bridge.proxies.interfaces.Session;
import org.xadisk.bridge.proxies.interfaces.XAFileSystem;
import org.xadisk.bridge.proxies.interfaces.XAFileSystemProxy;
import org.xadisk.filesystem.exceptions.XAApplicationException;
import org.xadisk.filesystem.standalone.StandaloneFileSystemConfiguration;

public class XADiskHelloWordTest {

    @Test
    public void HelloWorld(){
        String xadiskSystemDirectory = XADiskConstant.PATH;
        File sampleDataDir1 = new File(XADiskConstant.DATA_PATH);
        XAFileSystem xafs = null;

        try {
            StandaloneFileSystemConfiguration configuration = 
            new StandaloneFileSystemConfiguration(xadiskSystemDirectory, "id-1");
            
            xafs = XAFileSystemProxy.bootNativeXAFileSystem(configuration);
            
            System.out.println("\nBooting XADisk...\n");
            
            xafs.waitForBootup(-1);
            
            System.out.println("\nXADisk is now available for use.\n");

            Session session = xafs.createSessionForLocalTransaction();
            
            try {
                session.createFile(sampleDataDir1, true);
                session.createFile(new File(sampleDataDir1, "a.txt"), false);
                
                session.commit();
                
                System.out.println("\nCongratulations! You have successfully run the XADisk-Hello-World.\n");
            } catch (XAApplicationException xaae) {
                session.rollback();
                throw xaae;
            }
            
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (xafs != null) {
                try {
                    xafs.shutdown();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
