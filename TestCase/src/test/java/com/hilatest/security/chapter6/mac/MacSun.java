package com.hilatest.security.chapter6.mac;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;

public class MacSun {

	//the algorithms sun support
	private List<String> algorithms;
	
	
	@Before
	public void initial(){
		algorithms = new LinkedList<String>();
		algorithms.add("HmacMD5");
		algorithms.add("HmacSHA1");
		algorithms.add("HmacSHA256");
		algorithms.add("HmacSHA384");
		algorithms.add("HmacSHA512");
	}
	
	public void testHmacMD5(String algorithm) throws NoSuchAlgorithmException, InvalidKeyException{
		System.out.println("算法："+algorithm);
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		
		SecretKey secretKey1 = keyGenerator.generateKey();
		
		//还原Key
		SecretKey secretKey = new SecretKeySpec(secretKey1.getEncoded(),algorithm);
		
		String input = algorithm;
		
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		
		mac.init(secretKey);
		String output = Hex.encodeHexString(mac.doFinal(input.getBytes()));
		
		System.out.println("密文："+output);
	}
	
	@Test
	public void testMAC() throws InvalidKeyException, NoSuchAlgorithmException{
		
		for(String algorithm:algorithms){
			this.testHmacMD5(algorithm);
		}
	}
}
