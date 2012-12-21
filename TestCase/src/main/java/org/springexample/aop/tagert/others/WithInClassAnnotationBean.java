package org.springexample.aop.tagert.others;

import org.springexample.aop.tagert.annotation.ClassExample;


public class WithInClassAnnotationBean {
	
	@ClassExample
	public void test(){
		System.out.println(this.getClass()+"--Method:test");
	}
}
