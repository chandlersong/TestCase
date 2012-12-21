package org.springexample.beanconfig;

import org.springexample.BasicBean;

public class InitialAndDestoryBean extends BasicBean{
	
	public void initialMethod(){
		System.out.println("initial");
	}

	public void destoryMethod(){
		System.out.println("destory");
	}
}
