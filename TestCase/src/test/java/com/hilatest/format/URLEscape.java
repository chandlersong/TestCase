package com.hilatest.format;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

public class URLEscape {

	@Test
	public void testBasic() throws UnsupportedEncodingException{
		
		System.out.println("example:");
		String example = "Paris Hilton - Wikipedia, the free encyclopedia";
		System.out.println(example);
		
		System.out.println("result:");
		System.out.println(URLEncoder.encode(example, "ISO-8859-1"));
	}
	
	@Test
	public void testutf() throws UnsupportedEncodingException{
		
		System.out.println("example:");
		String example = "Paris Hilton - Wikipedia, the free encyclopedia";
		System.out.println(example);
		
		System.out.println("result:");
		System.out.println(URLEncoder.encode(example, "ASCII"));
	}
}
