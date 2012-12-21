package org.springexample.aop.tagert.execution;

import java.rmi.AlreadyBoundException;

import org.springexample.aop.tagert.TargetBean;

public interface ExecutionBean extends TargetBean {

	public String getValue();
	
	public String throwsException() throws AlreadyBoundException;
	
	public void testAop(int throwexception,String arg);
}
