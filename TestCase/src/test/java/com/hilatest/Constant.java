
package com.hilatest;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class Constant {

    public static final File OutputPath = new File(FileUtils.getTempDirectory() + "\\hilatest");

    static {
        if (!OutputPath.exists()) {
            OutputPath.mkdirs();
        }
    }
}
