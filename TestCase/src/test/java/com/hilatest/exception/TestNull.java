package com.hilatest.exception;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestNull {

	@Test
	public void testCase(){
		
		Map<String,String> testcase = null;
		
		HashMap<String,String> map =  (HashMap<String,String>)testcase;
		
		System.out.println(map);
	}
}
