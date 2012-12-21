package com.hilatest.common.io.listener;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

public class SimpleListener implements FileAlterationListener {

	private static Logger logger = Logger.getLogger(SimpleListener.class);
	
	public void onStart(FileAlterationObserver observer) {
		// TODO Auto-generated method stub

	}

	public void onDirectoryCreate(File directory) {
		logger.info(directory.toString()+" create");

	}

	public void onDirectoryChange(File directory) {
		logger.info(directory.toString()+" change");

	}

	public void onDirectoryDelete(File directory) {
		logger.info(directory.toString()+" delete");

	}

	public void onFileCreate(File file) {
		logger.info(file.toString()+" create");
	}

	public void onFileChange(File file) {
		logger.info(file.toString()+" change");
	}

	public void onFileDelete(File file) {
		logger.info(file.toString()+" delete");
	}

	public void onStop(FileAlterationObserver observer) {
		// TODO Auto-generated method stub

	}

}
