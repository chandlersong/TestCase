package com.hilatest.security.chapter5.base64;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.hilatest.security.SuperTestCase;

public class Base64Test extends SuperTestCase {

	
	@Test
	public void bouncyCastleImplement() throws UnsupportedEncodingException{
	
		System.out.println("Bouncy Castle implment Base64");
	
		String input = "Base64";
		
		System.out.println("原文:"+input);
		
		String secert =  new String(
			             org.bouncycastle.util.encoders.Base64.encode(input.getBytes(ENCODING))
			             ,ENCODING);
		
		System.out.println("密文:"+secert);
		
		String out =  new String(
	             org.bouncycastle.util.encoders.Base64.decode(secert.getBytes(ENCODING))
	             ,ENCODING);
		
		System.out.println("解码后:"+out);
	}
	
	@Test
	public void apacheCodecImplement() throws UnsupportedEncodingException{
	
		System.out.println("apache Codec implment Base64");
		
		String input = "Base64";
		
		System.out.println("原文:"+input);
		
		String secert =  new String(
				          org.apache.commons.codec.binary.Base64.encodeBase64(input.getBytes(ENCODING))
			             ,ENCODING);
		
		System.out.println("密文:"+secert);
		
		String out =  new String(
				 org.apache.commons.codec.binary.Base64.decodeBase64(secert.getBytes(ENCODING))
	             ,ENCODING);
		
		System.out.println("解码后:"+out);
	}
}
