package com.hilatest.common;

import java.util.Random;

import org.junit.Test;

public class DoubleTest {

	/**
	 * 以2为低的指数。
	 * 且，不会小数。只是取小数部分
	 */
	@Test
	public void getExponentTest(){
		
		int exponent =new Random().nextInt(100);
		Double value = Math.pow(2,exponent)+3;
		
		System.out.println("value:"+value);
		System.out.println("exponent:"+exponent);
		System.out.println("Math.getExponent(value):"+Math.getExponent(value));
	}
	
	@Test
	public void NaNTest(){
		System.out.println("Double.NaN == Double.NaN:"+(Double.NaN == Double.NaN));
	}
	
	/**
	 * 1>NaN:false
     *1<NaN:false
	 */
	@Test
	public void NanTest(){
		System.out.println("1>NaN:"+(1.0>Double.NaN));
		System.out.println("1<NaN:"+(1.0<Double.NaN));
	}
}
