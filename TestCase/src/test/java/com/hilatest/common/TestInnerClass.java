package com.hilatest.common;

import org.junit.Test;

public class TestInnerClass {

	@Test
	public void testDuplicateMethod(){
		OuterClass outer  = new OuterClass();
		OuterClass.InnerClass inner = outer.new InnerClass();
		inner.capsule();
	}
}

class OuterClass{
	
	public void duplicateMethod(){
		System.out.println("outer class");
	}
	
	
	public class InnerClass{
		
		public void duplicateMethod(){
			System.out.println("inner class");
		}
		
		public void capsule(){
			OuterClass.this.duplicateMethod();
		}
	}
}