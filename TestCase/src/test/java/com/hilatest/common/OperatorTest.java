package com.hilatest.common;

import org.junit.Test;

public class OperatorTest {
	
	/**
	 * 测试左移和右移
	 */
	@Test
	public void testShift(){
		System.out.println("8>>>1:"+(8>>>1));
		System.out.println("8>>>2:"+(8>>>2));
		System.out.println("8>>>3:"+(8>>>3));		
		
		System.out.println("7>>>1:"+(7>>>1));
		System.out.println("7>>>2:"+(7>>>2));
		System.out.println("7>>>3:"+(7>>>3));
		
		System.out.println("6>>>1:"+(6>>>1));
		System.out.println("6>>>2:"+(6>>>2));
		System.out.println("6>>>3:"+(6>>>3));
	}
	
	/**
	 * 在看HashMap源代码的时候，看到了两个int之间用&来做运算符，
	 * 不知道有什么用，所以也就实验一下
	 * 
	 * 弄好了之后，明白了意义。就是把int型转换成2进制。然后两个数字每一位进行比较
	 * 如果都是1。那么这一位就是1。反之。则为0.
	 */
	@Test
	public void testAnd(){
	    
	    int numberA = 1;
	    int numberB = 2;
	    
	    System.out.println("1&2:"+(numberA&numberB));
	    System.out.println("100&2:"+(100&2));
	    System.out.println("1&200:"+(1&200));
	    System.out.println("100&200:"+(100&200));
	    System.out.println("2&3:"+(2&3));
	    
	}
}
