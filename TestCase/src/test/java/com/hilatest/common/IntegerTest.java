package com.hilatest.common;

import org.junit.Test;

public class IntegerTest {

	
	@Test
	public void StaticToStringTest(){
		
		int value = 3;
		
		//2进制
		String binaryValue = Integer.toString(value, 2);
		
		System.out.println("二进制的 3:" + binaryValue);
		
		int value16 = 31;
		
		System.out.println("十六进制的23:" + Integer.toString(value16, 16));
	}
	
	/**
	 * 由此可见，如果Integer超过最大的数，那么会循环，变成最小的。
	 */
	@Test
	public void testBiggerThanMax(){
		
		int max = Integer.MAX_VALUE;
		
		max ++;
		
		System.out.print(max); //-2147483648
	}
	
	
	/**
	 * 获得数字位数的函数
	 */
	@Test
	public void testPosition(){
		
		int i = 32;
		
		System.out.println(this.stringSizeOfInt(i));
	}
	
	final private static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
         99999999, 999999999, Integer.MAX_VALUE };

	// Requires positive x
	private int stringSizeOfInt(int x) {
		for (int i=0; ; i++){
			if (x <= sizeTable[i]){
				return i+1;
			}
		}
	}
}      
