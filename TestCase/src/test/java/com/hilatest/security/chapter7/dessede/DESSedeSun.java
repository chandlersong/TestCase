package com.hilatest.security.chapter7.dessede;


import java.util.LinkedList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

public class DESSedeSun {

	//the algorithms sun support
	private List<String> algorithms;
	
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	
	@Before
	public void initial(){
		algorithms = new LinkedList<String>();
		algorithms.add("DESede");
	}
	
	public void testDES(String algorithm) throws Exception{
		System.out.println("算法："+algorithm);
		KeyGenerator kg = KeyGenerator.getInstance(algorithm);
		
		//112,168
		kg.init(168);
		SecretKey secretKey1 = kg.generateKey();
		
		String input = algorithm;
		System.out.println("原文："+input);
		
		//还原Key
		DESedeKeySpec dks = new DESedeKeySpec(secretKey1.getEncoded());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(algorithm);	
		SecretKey secretKey = keyfactory.generateSecret(dks);
		
		System.out.println("密钥："+Base64.encodeBase64String(secretKey.getEncoded()));
		
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		byte[] secret =cipher.doFinal(input.getBytes());
		System.out.println("密文："+ Base64.encodeBase64String(secret));
		
		Cipher cipher2 = Cipher.getInstance(algorithm);
		cipher2.init(Cipher.DECRYPT_MODE, secretKey);
		
		String output = new String(cipher2.doFinal(secret));
		System.out.println("明文："+output);
	}
	
	@Test
	public void testDES()  throws Exception{
		
		for(String algorithm:algorithms){
			this.testDES(algorithm);
		}
	}
}
