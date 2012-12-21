package com.hilatest.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

public class ModifylastModifyDateTest {

	private static Logger logger = Logger.getLogger(ModifylastModifyDateTest.class);
	
	private static String remoteServer = "\\\\csdmnas\\Annonymous";
	@Test
	public void testModifyDate() throws IOException{
		
		File directory = new File(FileUtils.getTempDirectory(),"xadiskdata");
		logger.info("create file directory:"+directory.mkdirs());
		logger.info("directory name:"+directory.getAbsolutePath());
		String fileName = RandomStringUtils.randomAlphanumeric(10);
		File file = new File(directory,fileName);
		//key
		file.setExecutable(true);
		logger.info("create file success:"+file.createNewFile());
		logger.info("file name:"+fileName);
		
		logger.info("Last modify Date:"+new Date(file.lastModified()));
		
		Long newDateLong = Math.abs(new Random().nextLong());

		logger.info("new modify Date:"+ (new Date(newDateLong)));
		//key
		logger.info("Last modify Date success:"+file.setLastModified(newDateLong));
		logger.info("Last modify Date:"+new Date(file.lastModified()));
		file.setExecutable(false);
	}
	
	
	@Test
	public void testRemoteModifyDate() throws IOException{
		
		File directory = new File(remoteServer);
		
		String fileName = RandomStringUtils.randomAlphanumeric(10);
		File file = new File(directory,fileName);
		//key
		file.setExecutable(true);
		logger.info("create file success:"+file.createNewFile());
		logger.info("file name:"+fileName);
		
		logger.info("Last modify Date:"+new Date(file.lastModified()));
		
		Long newDateLong = Math.abs(new Random().nextLong());

		logger.info("new modify Date:"+ (new Date(newDateLong)));
		//key
		logger.info("Last modify Date success:"+file.setLastModified(newDateLong));
		logger.info("Last modify Date:"+new Date(file.lastModified()));
		file.setExecutable(false);
	}
}
