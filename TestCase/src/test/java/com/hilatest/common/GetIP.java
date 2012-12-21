package com.hilatest.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class GetIP {

	@Test
	public void GetIPTest() throws UnknownHostException{
		   InetAddress inet = InetAddress.getLocalHost();
		    System.out.println("本机的ip=" + inet.getHostAddress());
	}
}
