package com.hilatest.reflect;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class ReflectPropertyTest {

	Class<ReflectBean> reflectbean;
	
	@SuppressWarnings("unchecked")
	@Before
	public void initail() throws ClassNotFoundException{
		reflectbean = (Class<ReflectBean>) Class.forName("com.hilatest.reflect.ReflectBean");
	}
	
	@Test
	public void GetPropertylist() throws IllegalArgumentException, IllegalAccessException{
		
		Field[] propertyArray = this.reflectbean.getFields();
		
		for(Field property:propertyArray){
			System.out.println("name:"+property.getName());
			System.out.println("value:"+property.get(new Object()));
		}
	}
}
