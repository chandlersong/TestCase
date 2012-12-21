package com.hilatest.security.chapter3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestMessageDigest {

	@Test
	public void digestString() throws NoSuchAlgorithmException{
		
		byte[] input = "sha".getBytes();
		
		MessageDigest sha = MessageDigest.getInstance("SHA");
		
		sha.update(input);
		
		byte[] output = sha.digest();
		
		System.out.println(byteArrayToHexString(output));
	}
	
	
	/**
	 * try to convert to Hex code
	 */
	 private final static String[] hexDigits = { 
	      "0", "1", "2", "3", "4", "5", "6", "7", 
	      "8", "9", "a", "b", "c", "d", "e", "f"}; 

	  public static String byteArrayToHexString(byte[] b) { 
	    StringBuffer resultSb = new StringBuffer(); 
	    for (int i = 0; i < b.length; i++) { 
	      resultSb.append(byteToHexString(b[i])); 
	    } 
	    return resultSb.toString(); 
	  } 

	  private static String byteToHexString(byte b) { 
	    int n = b; 
	    if (n < 0) 
	      n = 256 + n; 
	    int d1 = n / 16; 
	    int d2 = n % 16; 
	    return hexDigits[d1] + hexDigits[d2]; 
	  } 

}
