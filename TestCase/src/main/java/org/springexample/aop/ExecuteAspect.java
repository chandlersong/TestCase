package org.springexample.aop;

import org.springexample.BasicBean;

public class ExecuteAspect extends BasicBean{

	public void testScope(){
		
		System.out.println("test Scope");
		this.Success();
		
	}
	
	
	public void testReturnValue(){
		
		System.out.println("test return vaule");
		this.Success();
		
	}
	
	
	public void testNoPackageName(){	
		System.out.println("test No Package Name");
		this.Success();		
	}
	
	public void testThrowException(){	
		System.out.println("test throw exception");
		this.Success();		
	}
	
	public void testOnePara(){	
		System.out.println("test One Para");
		this.Success();		
	}
	
	public void testTwoPara(){	
		System.out.println("test Two Para");
		this.Success();		
	}
}
