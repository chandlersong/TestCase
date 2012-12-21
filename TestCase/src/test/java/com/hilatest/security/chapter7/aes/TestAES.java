package com.hilatest.security.chapter7.aes;



import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.hilatest.security.SuperTestCase;



public class TestAES extends SuperTestCase{
	

	@Test
	public void test() throws Exception{
		String inputStr = "AES";
		
		byte[] inputData = inputStr.getBytes();
		
		System.out.println("原文："+inputStr);
		
		byte[] key = AESCoder.initKey();
		
		System.out.println("密钥："+new String(Base64.encodeBase64String((key))));
		
		byte[] secret = AESCoder.encrypt(inputData, key);
	
		System.out.println("密文："+new String(Base64.encodeBase64String((secret))));
		
		String output = new String(AESCoder.decrpt(secret, key));
		
		System.out.println("还原："+output);
	}
}
