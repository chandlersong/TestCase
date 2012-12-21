package com.hilatest.xadisk;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.xadisk.connector.outbound.XADiskConnectionFactory;

@Deprecated
public class XADiskServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(XADiskServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3970838556698229274L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
		logger.info("XADiskServlet initialled");
		
	       //server.join(); 
        
        
		try {
			InitialContext context = new InitialContext();
			
			XADiskConnectionFactory cf1 = (XADiskConnectionFactory) context.lookup("java:comp/env/xadisk");
			System.out.println(cf1);
		} catch (NamingException e) {
			logger.error("find XAdisk fail", e);
		}
        
	}
	
	
	

}
