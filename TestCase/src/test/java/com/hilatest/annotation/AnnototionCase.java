package com.hilatest.annotation;

import org.junit.Test;

import com.hilatest.annotation.exmaple.sub;
import com.hilatest.annotation.exmaple.superclass;

public class AnnototionCase {

	/**
	 * the main propose is to check
	 * when super class and sub has same annototion, which annototion would get
	 * 
	 * get sub class annototion
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testsubAnnototion() throws SecurityException, NoSuchMethodException{
		superclass obj = new sub();
		obj.printAnnation();
		
	}
}
