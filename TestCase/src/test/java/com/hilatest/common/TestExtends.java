package com.hilatest.common;

import org.junit.Test;

public class TestExtends {

	@Test
	public void testExtends(){
		subClass sub = new subClass();
		
		sub.testExtends();
	}
	
	
	private class superClass{
		
		public void testExtends(){
			System.out.println(this.getClass().getName());
		}
	}
	
	private class subClass extends superClass{
		
	}
}

