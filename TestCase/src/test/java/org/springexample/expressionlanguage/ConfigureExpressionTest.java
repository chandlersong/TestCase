package org.springexample.expressionlanguage;

import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;

public class ConfigureExpressionTest extends SpringTestBasic {

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("configureexpression.xml");
		return filelist;
	}
	
	@Test
	public void testRandomNumber(){
		ConfigureExpressionBean bean = this.getContext().
		                               getBean("configureexpression",ConfigureExpressionBean.class);
		
		System.out.println(bean.getRandomNumber());
	}
	
	/**
	 * this one seems failed
	 */
	@Test
	public void testSystemConfigure(){
		ConfigureExpressionBean bean = this.getContext().
		                               getBean("configureexpression",ConfigureExpressionBean.class);
		
		System.out.println(bean.getDefaultLocale());
	}
	
	/**
	 * this one seems failed
	 */
	@Test
	public void testAnotherBean(){
		ConfigureExpressionBean bean = this.getContext().
		                               getBean("anotherbean",ConfigureExpressionBean.class);
		
		System.out.println(bean.getRandomNumber());
	}

}
