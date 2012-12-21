package com.hilatest.security.chapter7.des;


import java.util.LinkedList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import com.hilatest.security.SuperTestCase;

public class DESBouncyCastle extends SuperTestCase {

	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS7Padding";
	
	//the algorithms sun support
	private List<String> algorithms;
	
	
	@Before
	public void initial(){
		algorithms = new LinkedList<String>();
		algorithms.add("DES");
	}
	
	public void testDES(String algorithm) throws Exception{
		System.out.println("算法："+algorithm);
		KeyGenerator kg = KeyGenerator.getInstance(algorithm,BC);
		
		/**
		 * Bouncy Castle 支持32位
		 */
		kg.init(64);
		SecretKey secretKey1 = kg.generateKey();
		
		String input = algorithm;
		System.out.println("原文："+input);
		
		//还原Key
		DESKeySpec dks = new DESKeySpec(secretKey1.getEncoded());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(CIPHER_ALGORITHM);	
		SecretKey secretKey = keyfactory.generateSecret(dks);
		
		System.out.println("密钥："+Base64.encodeBase64String(secretKey.getEncoded()));
		
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM,BC);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		byte[] secret =cipher.doFinal(input.getBytes());
		System.out.println("密文："+ Base64.encodeBase64String(secret));
		
		Cipher cipher2 = Cipher.getInstance(CIPHER_ALGORITHM,BC);
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
