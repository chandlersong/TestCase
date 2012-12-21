package com.hilatest.format;


import java.text.DecimalFormat;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

public class NumberParse {

	@Test
	public void testParseEmpty(){
		String s;
		Double d;
		
	/*	s ="";
		d = Double.parseDouble(s);
		System.out.println("empty is:"+d);*/
		
		s =null;
		d = Double.parseDouble(s);
		System.out.println("null is:"+d);
	}
	
	@Test
	public void testNumberUtils(){
		String value1 = "1.2";
		System.out.println("value 1:"+value1);
		System.out.println("value 1 isDigits:"+NumberUtils.isDigits(value1));
		System.out.println("value 1 isNumber:"+NumberUtils.isNumber(value1));
		
        String value2 = "1,000";
        System.out.println("value 2:"+value2);
		System.out.println("value 2 isDigits:"+NumberUtils.isDigits(value2));
		System.out.println("value 2 isNumber:"+NumberUtils.isNumber(value2));
		
		String value3 = ".1";
		System.out.println("value 3:"+value3);	
		System.out.println("value 3 isDigits:"+NumberUtils.isDigits(value3));
		System.out.println("value 3 isNumber:"+NumberUtils.isNumber(value3));
	}
	
	@Test
	public void formateNumber(){
		String pattern = "###,###.000";
		Double[] examples = new Double[]{
			                         Double.valueOf(1234567),
			                         Double.valueOf(123456789012345L),
			                         123456789012345.12345,
			                         123456789012345.12};
		DecimalFormat df = new DecimalFormat(pattern);
		for(Double example:examples){
			System.out.println("number:"+example);
			System.out.println("formate:"+df.format(example));
		}
		
	}
}
