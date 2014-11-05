package com.jettyexample;

import org.apache.log4j.Logger;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 
 * @author chandler.song
 *
 */
public class SimpleWebAppContext extends WebAppContext {

    private static Logger logger = Logger.getLogger(SimpleWebAppContext.class);
    /**
     * web.xml
     */
    public final static String SimpleWebXML = "";

    private final static String ROOT = "target/classes/%1$s";
    private final static String CLASS_PATH = "target/classes";
    private final static String LIBRARY = "target/lib";
    private final static String MAVEN = "MAVEN";

    public SimpleWebAppContext() {
        super.setContextPath("/");
        super.setParentLoaderPriority(true);
        // define the folder
        super.setResourceBase(CLASS_PATH);
        // define the dependency
        super.setResourceAlias(MAVEN, LIBRARY);

        try {
            /*
             * initial the configuration first. and then I can add and set configuration.
             * the new one won't override the existing ones.
             */
            super.loadConfigurations();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public SimpleWebAppContext(String descriptor) {
        this();

        this.setDescriptor(descriptor);
    }

    @Override
    public void setDescriptor(String descriptor) {
        super.setDescriptor(String.format(ROOT, descriptor));
    }

    public void addConfiguration(Configuration c) {

        Configuration[] currentConfigList = this.getConfigurations();

        Configuration[] newConfigList = new Configuration[currentConfigList.length + 1];
        System.arraycopy(currentConfigList, 0, newConfigList, 0, currentConfigList.length);
        newConfigList[currentConfigList.length] = c;

        super.setConfigurations(newConfigList);
    }

    public void addConfiguration(Configuration[] configlist) {

        Configuration[] currentConfigList = this.getConfigurations();

        Configuration[] newConfigList = new Configuration[currentConfigList.length + configlist.length];
        System.arraycopy(currentConfigList, 0, newConfigList, 0, currentConfigList.length);
        System.arraycopy(configlist, 0, newConfigList, currentConfigList.length, configlist.length);
        super.setConfigurations(newConfigList);
    }

}
