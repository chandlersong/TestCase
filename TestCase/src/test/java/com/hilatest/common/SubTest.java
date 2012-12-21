package com.hilatest.common;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.hilatest.commB.SubObject;

public class SubTest {

	Logger logger = Logger.getLogger(SubTest.class);
	
	@Test
	public void testProperty(){
		SubClass sub = new SubClass();
		
		logger.info(sub.getA());
		
	}
	
	@Test
	public void testParameter(){
		  Sub s = new Sub();
		  Parent p = s;
		  SubClass b = new SubClass();
		  SuperClass a = b;
		 
		  a.fun(p);
		  a.fun(s);
		  b.fun(p);
		  b.fun(s);
	}
	
	@Test
	public void testProtectedProperty(){
	    SubObject test = new SubObject();
	    test.test();
	    test.add();
	    test.test();
	}
}

class Parent {}
class Sub extends Parent {}

class SuperClass{
	protected int a = 10;

	
	public void fun(Parent p){
		System.out.println("a");
	}
	/**
	 * @return the a
	 */
	public int getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(int a) {
		this.a = a;
	}	
}

class SubClass extends SuperClass{
	
	public void fun(Sub b){
		System.out.println("b");
	}
	
	/**
	 * @return the a
	 */
	public int getA() {
		return this.a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(int a) {
		this.a = a;
	}	
}