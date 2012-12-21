package com.hilatest.format;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Ã§â€�Â¨Ã¦Â­Â£Ã¥Ë†â„¢Ã¨Â¡Â¨Ã¨Â¾Â¾Ã¥Â¼ï¿½Ã¯Â¼Å’Ã¦ï¿½Â¥Ã©ÂªÅ’Ã¨Â¯ï¿½numberÃ£â‚¬â€š
 * @author chandler
 *
 */
public class VaildateNumber {

	List<String> validate;                                //Success
	
	List<String> invalidate;                              //fail
	
	@Before
	public void initialtestCase(){
		validate = new LinkedList<String>();
		validate.add(".20");
		validate.add("1,234");
		validate.add("01,234");
		validate.add("1,234.56");
		validate.add("234.56");
		validate.add("134,567");
		validate.add("12345.67");
		validate.add("12345678");
		validate.add("012");
		validate.add("0.02");
		validate.add("0.10");
		validate.add("00000001");
		validate.add("00000000");
		validate.add("00123");
		validate.add("0,567");
		
		invalidate = new LinkedList<String>();
		invalidate.add(".2");
		invalidate.add("0.002");
		invalidate.add("0.002");
		invalidate.add("0.00,02");
		invalidate.add("1.2");
		invalidate.add("123.2");
		invalidate.add("1234,567");
		invalidate.add("1234,567.5");		
		invalidate.add(",567");
		invalidate.add("12.00.25");
		invalidate.add("1,2,00.25");
	}
	
	public void vailate(String regex,Boolean asserted){
		
		Boolean result;
		//Validate		
		for(String tc:validate){			
			System.out.println("vaildate:"+tc);
			result = Pattern.matches(regex, tc);
			System.out.println("result:"+result);
			
			if(asserted){
				Assert.assertTrue(result);
			}
		}
		System.out.println("invalidate///////////////////////////////////////////////////");
		//inValidate		
		for(String tc:invalidate){			
			System.out.println("vaildate:"+tc);
			result = Pattern.matches(regex, tc);
			System.out.println("result:"+result);
			
			if(asserted){
				Assert.assertFalse(result);
			}
		}		
	}
	
	@Test
	public void testRegx1(){
		String regex = "^\\d{1,3}(,\\d{3})*(\\.\\d{2})?|\\d+(\\.\\d{2})?|(\\.\\d{2})$";
		this.vailate(regex, true);
		
		String output = "1,567";
		System.out.print(Double.parseDouble(output.replace(",", "")));
	}
}
