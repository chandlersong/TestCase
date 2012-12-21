package com.hilatest.security.chapter5.base64url;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.UrlBase64;
import org.junit.Test;

import com.hilatest.security.SuperTestCase;

public class Base64URL extends SuperTestCase {

	@Test
	public void bouncyCastleImplement() throws UnsupportedEncodingException{
	
		System.out.println("Bouncy Castle implment Base64");
	
		String input = "Base64";
		
		System.out.println("原文:"+input);
		
		String secert =  new String(
			               UrlBase64.encode(input.getBytes(ENCODING))
			             ,ENCODING);
		
		System.out.println("密文:"+secert);
		
		String out =  new String(
				          UrlBase64.decode(secert.getBytes(ENCODING))
	             ,ENCODING);
		
		System.out.println("解码后:"+out);
	}
	
	@Test
	public void apacheCodecImplement() throws UnsupportedEncodingException{
	
		System.out.println("apache Codec implment Base64");
		
		String input = "Base64";
		
		System.out.println("原文:"+input);
		
		String secert =  new String(
				          Base64.encodeBase64URLSafe(input.getBytes(ENCODING))
			             ,ENCODING);
		
		System.out.println("密文:"+secert);
		
		String out =  new String(
				            Base64.decodeBase64(secert.getBytes(ENCODING))
	             ,ENCODING);
		
		System.out.println("解码后:"+out);
	}
}
