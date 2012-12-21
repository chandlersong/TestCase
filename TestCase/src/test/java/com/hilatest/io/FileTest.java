package com.hilatest.io;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileTest {

    @Test
    public void testFilePath() throws IOException{
        File file = new File("E:/temp/test.zip");
        File nasFile = new File("//csdmnas/Database/patient/0/8afa203131467f23013146ab9d9207c0/8afa203131467f23013146abb87d07c3/TD_8afa203131467f23013146abba8107c7.png");
        
        System.out.println("file getAbsolutePath:"+file.getAbsolutePath() );
        System.out.println("file getCanonicalPath:"+file.getCanonicalPath() );
        System.out.println("nas getAbsolutePath:"+nasFile.getAbsolutePath() );
        System.out.println("nas getCanonicalPath:"+nasFile.getCanonicalPath() );
    }
}
