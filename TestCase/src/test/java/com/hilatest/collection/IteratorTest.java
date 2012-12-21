package com.hilatest.collection;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class IteratorTest {

	@Test(expected=ConcurrentModificationException.class)
	public void testFastAndFailinOneThread(){
		
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(1);
		list.add(2);
		
		Iterator<Integer> iter = list.iterator();
		
		list.add(3);
		
		iter.next(); //here will throw ConcurrentModificationException
	}
}
