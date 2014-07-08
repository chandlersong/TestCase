package com.hilatest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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

    /**
     * 
     * 1)not all ListIterator support remove, so I convert it to LinkedList first.
     */
    @Test
    public void testListIteratorRemove(){
        List<String> objectList = new LinkedList<String>(Arrays.asList("1","2","3","4","5"));

        ListIterator<String> iterator = objectList.listIterator();

        while(iterator.hasNext()){
            String object = iterator.next();

            if(object.equals("3")){
                iterator.remove();
            }
        }

        for(String object:objectList){
            System.out.println(object);
        }



    }
}
