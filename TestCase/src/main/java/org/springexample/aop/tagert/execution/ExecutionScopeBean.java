package org.springexample.aop.tagert.execution;

import org.springexample.aop.tagert.TargetBean;

public class ExecutionScopeBean implements TargetBean{

	public void testAop(){
		System.out.println("run method:test Execution Scope");
	}

	public void testAop(int throwexception) {
		testscope(throwexception);
		
	}
	
   
    public void testscope(int throwexception){
	   
	   if(throwexception==THROWEXCPTION){
			throw new RuntimeException();
		}
	   else if(throwexception==NOTTHROWEXCPTION){
			testAop();
	   }
   }
}
