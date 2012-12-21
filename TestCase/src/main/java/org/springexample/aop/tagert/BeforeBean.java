package org.springexample.aop.tagert;

public class BeforeBean implements TargetBean{

	public void testAop(){
		System.out.println("run method:test advice before");
	}

	public void testAop(int throwexception) {
	
	}
}
