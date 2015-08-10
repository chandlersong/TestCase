package com.hilatest.common.io.listener;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

public class SimpleListener implements FileAlterationListener {

    private static Logger logger = Logger.getLogger(SimpleListener.class);

    @Override
    public void onStart(FileAlterationObserver observer) {

    }

    @Override
    public void onDirectoryCreate(File directory) {
        logger.info(directory.toString() + " create");

    }

    @Override
    public void onDirectoryChange(File directory) {
        logger.info(directory.toString() + " change");

    }

    @Override
    public void onDirectoryDelete(File directory) {
        logger.info(directory.toString() + " delete");

    }

    @Override
    public void onFileCreate(File file) {
        logger.info(file.toString() + " create");
    }

    @Override
    public void onFileChange(File file) {
        logger.info(file.toString() + " change");
    }

    @Override
    public void onFileDelete(File file) {
        logger.info(file.toString() + " delete");
    }

    @Override
    public void onStop(FileAlterationObserver observer) {

    }

}
