package com.hilatest.security;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;

public class SuperTestCase {
	
	public static final String BC = "BC";
	
	public static final String ENCODING = "UTF-8";
	@Before
	public void before(){
		Security.addProvider(new BouncyCastleProvider());
		
		/**
		 * BouncyCastleProvider为默认的provider
		 */
		//Security.insertProviderAt(new BouncyCastleProvider(), 1);
	}
}
