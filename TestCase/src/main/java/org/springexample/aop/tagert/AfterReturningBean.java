package org.springexample.aop.tagert;

public class AfterReturningBean implements TargetBean{

	public void testAop(){
		System.out.println("run method:test advice after");
	}

	public void testAop(int throwexception) {

		if(throwexception==THROWEXCPTION){
			throw new RuntimeException();
		}
		else if(throwexception==NOTTHROWEXCPTION){
			this.testAop();
		}
		
	}
}
