package com.hilatest.common.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.hilatest.Constant;

public class FileUtilsTest {

    private static Logger logger = Logger.getLogger(FileUtilsTest.class);
    private File file;
    
    @Before
    public void initial() throws IOException{
        file = new File(Constant.OutputPath,RandomStringUtils.randomAlphabetic(5)+".txt");
        
        file.createNewFile();
        
        logger.info("file:"+file.getAbsolutePath());        
    }
    
    /**
     * defualt is not append, and will overwrite the previous input 
     * @throws IOException
     */
    @Test
    public void testWriteFileAppend() throws IOException{
        String s = "aaa";
        FileUtils.write(file, s);
        s = "bb";
        FileUtils.write(file, s);
        s = "cc";
        FileUtils.write(file, s,true); //in file, it will record bbcc
    }
    
    
    /**
     * @throws IOException
     */
    @Test
    public void testWriteLines() throws IOException{
        List<String> data = new ArrayList<String>();
        data.add("aa");
        data.add("bb");
        data.add("cc");
        FileUtils.writeLines(file,data );
    }
}
