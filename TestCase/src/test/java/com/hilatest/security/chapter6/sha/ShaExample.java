package com.hilatest.security.chapter6.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import com.hilatest.security.SuperTestCase;

public class ShaExample extends SuperTestCase {

	public static final List<String> KEY_ALGORITHM = new LinkedList<String>();
	
	@Before
	public void initial(){
		KEY_ALGORITHM.add("SHA");
		KEY_ALGORITHM.add("SHA-256");
		KEY_ALGORITHM.add("SHA-384");
		KEY_ALGORITHM.add("SHA-512");
	}
	
	
	@Test
	public void sunImplement() throws NoSuchAlgorithmException{
		
		for(String algorithm:KEY_ALGORITHM){
			System.out.println("Sun implment:"+algorithm);
			MessageDigest md = MessageDigest.getInstance(algorithm);
			
			String input = "algorithm";
			
			String output = Hex.encodeHexString(md.digest(input.getBytes()));
			
			System.out.println("密文："+output);
			
		}


	}
	
	@Test
	public void bouncyCastleImplement() throws NoSuchAlgorithmException, NoSuchProviderException{
	
		for(String algorithm:KEY_ALGORITHM){
			System.out.println("bouncy Castle implment:"+algorithm);
			MessageDigest md = MessageDigest.getInstance(algorithm,BC);
			
			String input = "algorithm";
			
			String output = Hex.encodeHexString(md.digest(input.getBytes()));
			
			System.out.println("密文："+output);
			
		}
		
		
		String algorithm="SHA-224";
		System.out.println("Sun implment:"+algorithm);
		MessageDigest md = MessageDigest.getInstance(algorithm);
		
		String input = "algorithm";
		
		String output = Hex.encodeHexString(md.digest(input.getBytes()));
		
		System.out.println("密文："+output);

	}
	
	@Test
	public void apacheCodecImplement(){
	
		System.out.println("apache Codec:SHA");
		String input = "algorithm";
		
		String output = DigestUtils.shaHex(input);
		System.out.println("密文："+output);
		
		System.out.println("apache Codec:SHA-256");
		output = DigestUtils.sha256Hex(input);
		System.out.println("密文："+output);
		
		System.out.println("apache Codec:SHA-384");	
		output = DigestUtils.sha384Hex(input);
		System.out.println("密文："+output);

		System.out.println("apache Codec:SHA-512");	
		output = DigestUtils.sha512Hex(input);
		System.out.println("密文："+output);

	}
}
