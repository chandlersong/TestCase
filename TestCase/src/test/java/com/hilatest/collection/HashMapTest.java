package com.hilatest.collection;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

public class HashMapTest {

	private static Logger logger = Logger.getLogger(HashMapTest.class);
	
	/**
	 * put方法，如果放入的是新值，那么返回的是null
	 * 如果放入是key是duplicate的，返回的是oldValue
	 */
	@Test
	public void testDuplicate(){
		
		Map<String,String> map = new HashMap<String,String>();
		
		String key = "Key";
		String oldValue = "oldValue";
		String newValue = "newValue";
		
		String value1 =map.put(key, oldValue);
		logger.info("value1:"+value1);
		
		String value2 = map.put(key, newValue);
		logger.info("value2:"+value2);
		logger.info("key's value:"+map.get(key));
		
		
	}
}
