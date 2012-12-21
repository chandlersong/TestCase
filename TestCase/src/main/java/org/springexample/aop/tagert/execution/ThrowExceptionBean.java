package org.springexample.aop.tagert.execution;

import java.rmi.AlreadyBoundException;

public class ThrowExceptionBean implements ExecutionBean {

	public void testAop() {

	}

	public void testAop(int throwexception) {
	
	}

	public String getValue() {
		return null;
	}

	public String throwsException() throws AlreadyBoundException {
		throw new AlreadyBoundException();
	}

	public void testAop(int throwexception, String arg) {

		
	}

}
