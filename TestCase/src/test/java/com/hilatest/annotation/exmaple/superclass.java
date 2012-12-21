package com.hilatest.annotation.exmaple;

import java.lang.reflect.Method;

public class superclass {
 
	/**
	 * when you want to add a object to the annototion,you can create a final object to it
	 */
	final String cont = "super";
	@AnnototionTest(id=2,description=cont)
	public void printAnnation() throws SecurityException, NoSuchMethodException{
		 Class<?> objclass= this.getClass();
		 
		 Method m = objclass.getMethod("printAnnation",new Class<?>[]{});
		 AnnototionTest annototion = m.getAnnotation(AnnototionTest.class);
		 
		 if(annototion!=null){
			 System.out.println(annototion.id());
			 System.out.println(annototion.description());
		 }
	}
}
