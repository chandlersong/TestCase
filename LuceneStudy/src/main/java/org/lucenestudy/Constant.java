package org.lucenestudy;

import java.io.File;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;

public class Constant {

	private final static Logger logger = Logger.getLogger(Constant.class);
	public final static File rootIndex;

	static {
		File tempPath = SystemUtils.getJavaIoTmpDir();
		rootIndex = new File(tempPath, "LucenceExample");

		if (!rootIndex.exists()) {
			rootIndex.mkdirs();
		}

		logger.info("temp folder is:" + rootIndex.getAbsolutePath());

	}
}
