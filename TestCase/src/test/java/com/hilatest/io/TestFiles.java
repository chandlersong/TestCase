/*
it can't complie at jdk1.6
package com.hilatest.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class TestFiles {

    
    @Test
    public void testMove() throws IOException{
        Path source = FileSystems.getDefault().getPath("D:/target/test.zip");
        Path target = FileSystems.getDefault().getPath("E:/temp");
        
        Files.move(source, target.resolve(source.getFileName()));
    }
}
*/