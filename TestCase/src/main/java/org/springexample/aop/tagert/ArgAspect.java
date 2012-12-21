package org.springexample.aop.tagert;

import org.springexample.BasicBean;

public class ArgAspect extends BasicBean{

	public void testOneArg(){
		System.out.println("test one Arg");
		this.Success();
	}
	
	
	public void testGenericArg(){
		System.out.println("test Generic Arg");
		this.Success();
	}
}
