package com.hilatest.xadisk;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnectionFactory;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.junit.Test;
import org.xadisk.bridge.proxies.interfaces.XAFileSystem;
import org.xadisk.bridge.proxies.interfaces.XASession;
import org.xadisk.connector.outbound.XADiskConnectionFactory;
import org.xadisk.connector.outbound.XADiskManagedConnectionFactory;
import org.xadisk.filesystem.exceptions.FileAlreadyExistsException;
import org.xadisk.filesystem.exceptions.FileNotExistsException;
import org.xadisk.filesystem.exceptions.InsufficientPermissionOnFileException;
import org.xadisk.filesystem.exceptions.LockingFailedException;
import org.xadisk.filesystem.exceptions.NoTransactionAssociatedException;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.jettyexample.SimpleWebAppContext;





public class JCAResourceTest {

	private static Logger logger = Logger.getLogger(JCAResourceTest.class);
	
	private static final String XAid = "JCA-ResourceTest";
	
	
	private static final String JNID_ID = "xaDisk";
	@Test
	public void testHelloWorld() throws NamingException, ResourceException{
		//XAFileSystem xfs = XADiskConstant.generateXAFileSystem(XAid);
		
		XADiskManagedConnectionFactory mcf = new XADiskManagedConnectionFactory();
		Object jndiObject = mcf.createConnectionFactory( new ConnectionManager(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Object allocateConnection(ManagedConnectionFactory mcf,
					ConnectionRequestInfo cxRequestInfo)
					throws ResourceException {
				return null;
			}
			
		});
		Context ctx = new InitialContext();
		ctx.rebind(JNID_ID, jndiObject);
		ctx.close();		
	}
	
	
	@Test
	public void testCreateFile() throws NamingException, ResourceException, NotSupportedException, SystemException, FileAlreadyExistsException, FileNotExistsException, InsufficientPermissionOnFileException, LockingFailedException, NoTransactionAssociatedException, InterruptedException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException{
		
		final XAFileSystem xfs = XADiskConstant.generateXAFileSystem(XAid);
		
		//initial Data source
		XADiskManagedConnectionFactory mcf = new XADiskManagedConnectionFactory();
		mcf.setInstanceId(JNID_ID);
		
		Object jndiObject = mcf.createConnectionFactory( new ConnectionManager(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Object allocateConnection(ManagedConnectionFactory mcf,
					ConnectionRequestInfo cxRequestInfo)
					throws ResourceException {
				return xfs.createSessionForXATransaction();
			}
			
		});
		Context ctx = new InitialContext();
		System.out.println(jndiObject+" will be bind");
		ctx.rebind(JNID_ID, jndiObject);
	
		
		
		XADiskConnectionFactory cf1 = (XADiskConnectionFactory)ctx.lookup(JNID_ID);
		System.out.println("XADiskConnectionFactory:"+cf1);
		
		
		UserTransaction userTransaction = new com.atomikos.icatch.jta.UserTransactionImp();
		userTransaction.setTransactionTimeout(60);
		userTransaction.begin();
		
		XASession xaSession = xfs.createSessionForXATransaction();
		
		String file = XADiskConstant.DATA_PATH + File.separator+RandomStringUtils.randomAlphanumeric(10);
		System.out.println("create new file:"+ file);
		xaSession.createFile(new File(file), false);
		 
		userTransaction.commit();
		
		
		ctx.close();
	}
	
	@Test
	public void testJNDIConfigurationFile() throws Exception{
			

		 	Server server = new Server(8080);
		
		 	SimpleWebAppContext  context = new SimpleWebAppContext("XAdiskJNDIConfigurweb.xml");


		 	EnvConfiguration ec = new EnvConfiguration();
		 	ec.setJettyEnvXml(new URL("file:target/classes/xadisk_JNDI.xml"));
		 	
		 	PlusConfiguration pc = new PlusConfiguration();
		 	
		 	Configuration[] configurationList = new Configuration[]{ec,pc};
		 	context.addConfiguration(configurationList);
	
		 	//context.setTempDirectory(FileUtils.getTempDirectory());
		 	
		 	//Resource web_inf = context.getBaseResource().addPath("WEB-INF/");
		 	//web_inf.getFile().mkdirs();

		 	
		 	server.setHandler(context);
		 	
		 
	        server.start();
	        
	        /**
	        logger.info("start server");
	        logger.info("ContextPath:"+context.getContextPath());
	        logger.info("War:"+context.getWar());
	        logger.info("Temp Directory:"+context.getTempDirectory());
	        logger.info("Logger:"+context.getLogger());
	        logger.info("Base Resource:"+context.getBaseResource());
	        logger.info("Web Inf:"+context.getWebInf());
	        logger.info("WebAppContext Current Context:"+WebAppContext.getCurrentContext());
	        logger.info("WebAppContext Current Web Context:"+WebAppContext.getCurrentWebAppContext());
	        **/
	      
	        server.join();
	        server.stop();
	}
	
	public AtomikosDataSourceBean getDADiskSourceBean(){
	    
	    AtomikosDataSourceBean result = new AtomikosDataSourceBean();
	    result.setUniqueResourceName(JNID_ID);
	    result.setXaDataSourceClassName("org.xadisk.connector.outbound.XADiskConnectionFactory");
	    Properties p = new Properties();
	    p.setProperty ( "instanceId" , "jcaExample" );
	    p.setProperty ( "xaDiskHome" , XADiskConstant.PATH );
	    
	    result.setXaProperties(p);
	    return result;
	}
	
	
   @Test
    public void HelloWorld() throws Exception{
        AtomikosDataSourceBean ds = this.getDADiskSourceBean();
        Context ctx = new InitialContext();
 
        ctx.rebind(JNID_ID, ds);
        logger.info("data souce has been bindded");
      
        
        Object cf1 = new InitialContext().lookup(JNID_ID);
        AtomikosDataSourceBean xadiskDs = (AtomikosDataSourceBean)ds;
        logger.info("find datasouce:"+cf1);
        logger.info("datasouce type:"+cf1.getClass());
        logger.info("datasouce type:"+xadiskDs.getReference().getClassName());
    
    }
}
