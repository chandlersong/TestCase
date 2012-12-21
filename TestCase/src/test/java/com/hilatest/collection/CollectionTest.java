package com.hilatest.collection;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

public class CollectionTest {

	@Test
	public void testRetainALL(){
		
		Collection<String> srcCollection = new LinkedList<String>();
		srcCollection.add("1");
		srcCollection.add("2");
		
		Collection<String> retainCollection = new LinkedList<String>();
		retainCollection.add("2");
		retainCollection.add("3");
		
		Boolean result ;
		System.out.println("before retain");
		System.out.println("source collection:"+srcCollection);
		System.out.println("retain collection:"+retainCollection);
		result = srcCollection.retainAll(retainCollection);		
		System.out.println("after retain");
		System.out.println("result:"+result);
		System.out.println("source collection:"+srcCollection);
		
		srcCollection.clear();
		retainCollection.clear();
		srcCollection.add("1");
		srcCollection.add("2");
		retainCollection.add("2");
		System.out.println("before retain");
		System.out.println("source collection:"+srcCollection);
		System.out.println("retain collection:"+retainCollection);
		result = srcCollection.retainAll(retainCollection);		
		System.out.println("after retain");
		System.out.println("result:"+result);
		System.out.println("source collection:"+srcCollection);
		
	}
	
	@Test
	public void testRemoveAll(){
		
		Collection<String> srcCollection = new LinkedList<String>();
		srcCollection.add("1");
		srcCollection.add("2");
		
		Collection<String> removeCollection = new LinkedList<String>();
		removeCollection.add("2");
		removeCollection.add("3");
		
		Boolean result ;
		System.out.println("before remove");
		System.out.println("source collection:"+srcCollection);
		System.out.println("remove collection:"+removeCollection);
		result = srcCollection.removeAll(removeCollection);		
		System.out.println("after retain");
		System.out.println("result:"+result);
		System.out.println("source collection:"+srcCollection);
		
		srcCollection.clear();
		removeCollection.clear();
		srcCollection.add("1");
		srcCollection.add("2");
		removeCollection.add("2");
		System.out.println("before remove");
		System.out.println("source collection:"+srcCollection);
		System.out.println("remove collection:"+removeCollection);
		result = srcCollection.removeAll(removeCollection);		
		System.out.println("after retain");
		System.out.println("result:"+result);
		System.out.println("source collection:"+srcCollection);
		
	}
}
