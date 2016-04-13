package com.hilatest.io;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

public class FileNameUtilsTest {

    @Test
    public void testConcat() {
        System.out.println(FilenameUtils.concat("a/b", "c"));
        System.out.println(FilenameUtils.concat("a\\b", "c"));
        System.out.println(FilenameUtils.concat("", "c"));
        System.out.println(FilenameUtils.concat(null, "c"));
        System.out.println(FilenameUtils.concat("a/b", ""));
        System.out.println(FilenameUtils.concat("a/b", null));
    }
}
