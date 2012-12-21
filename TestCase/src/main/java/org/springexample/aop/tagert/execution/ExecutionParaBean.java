package org.springexample.aop.tagert.execution;

import java.rmi.AlreadyBoundException;

public class ExecutionParaBean implements ExecutionBean {

	public void testAop() {


	}

	public void testAop(int throwexception) {
	   System.out.println("int");

	}

	public String getValue() {

		return null;
	}

	public String throwsException() throws AlreadyBoundException {
		
		return null;
	}

	public void testAop(int throwexception, String arg) {
		System.out.println("int,string");
	}

	public void testAop(int throwexception,double b) {
		
		System.out.println("int,double");
	}
}
