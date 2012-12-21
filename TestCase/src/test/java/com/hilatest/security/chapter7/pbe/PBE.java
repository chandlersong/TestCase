package com.hilatest.security.chapter7.pbe;


import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import com.hilatest.security.SuperTestCase;

/**
 * Java6 支持
 * PBEWithMD5AndDES 
 * PBEWithMD5AndTripleDES 
 * PBEWithSHA1AndDESede
 * PBEWithSHA1AndRC2_40
 * @author chandler
 *
 */
public class PBE extends SuperTestCase {

	//the algorithms sun support
	private List<String> algorithms;
	
	
	public static final int ITRRATION_COUNT = 100;
	@Before
	public void initial(){
		algorithms = new LinkedList<String>();
		algorithms.add("PBEWithMD5AndDES");
		algorithms.add("PBEWithMD5AndTripleDES");
		algorithms.add("PBEWithSHA1AndDESede");
		algorithms.add("PBEWithSHA1AndRC2_40");
	}
	
	public void testDES(String algorithm) throws Exception{
		System.out.println("算法："+algorithm);
		
		String pwd = "chandler";
		//初始化盐值
		SecureRandom random = new SecureRandom();
		byte[] salt = random.generateSeed(8);
				
		String input = algorithm;
		System.out.println("原文："+input);
		
		//还原Key
		PBEKeySpec secretKey = new PBEKeySpec(pwd.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
		SecretKey key = keyFactory.generateSecret(secretKey);
		
		System.out.println("密钥："+Base64.encodeBase64String(key.getEncoded()));
		
		//加密
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt,ITRRATION_COUNT);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key,paramSpec);
        byte[] secret = cipher.doFinal(input.getBytes());      
		System.out.println("密文："+ Base64.encodeBase64String(secret));
		
		 PBEParameterSpec paramSpec2 = new PBEParameterSpec(salt,ITRRATION_COUNT);
	     Cipher cipher2 = Cipher.getInstance(algorithm);
	     cipher2.init(Cipher.DECRYPT_MODE, key,paramSpec2);
	      	
		String output = new String(cipher2.doFinal(secret));
		System.out.println("明文："+output);
	}
	
	@Test
	public void testIDEA()  throws Exception{
		
		for(String algorithm:algorithms){
			this.testDES(algorithm);
		}
	}
}
