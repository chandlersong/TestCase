package com.hilatest.xadisk;

import java.io.IOException;

import javax.naming.Binding;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class XADiskListListener implements Filter{

	private static Logger logger = Logger.getLogger(XADiskListListener.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("XADiskServlet initialled");
		  
		try {
			InitialContext context = new InitialContext();
			
			NamingEnumeration<Binding> bindingList = context.listBindings("");
			logger.info("iterator java:comp");
			while(bindingList.hasMoreElements()){
				logger.info("iterator item:"+ bindingList.nextElement());
			}
			logger.info("iterator java:comp stop");
			
			Object cf1 = context.lookup("java:comp/env/xadisk");
			logger.info("cf1 is null:"+ cf1==null);
			logger.info("cf1 class:"+ cf1.getClass());
			
		} catch (NamingException e) {
			logger.error("find XAdisk fail", e);
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
	}

	public void destroy() {
		
	}

}
