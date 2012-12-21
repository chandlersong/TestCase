package org.springexample.aop.tagert.execution;

public class ReturnValueBean implements ExecutionBean {

	public void testAop() {
		System.out.println("testAOP");

	}

	public void testAop(int throwexception) {
		System.out.println("testAOP");
	}

	public String getValue() {
		
		return "test return value";
	}

	public String throwsException(){
		return null;
	}

	public void testAop(int throwexception, String arg) {
	
	}

}
