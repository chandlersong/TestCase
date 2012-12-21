package org.springexample.aop;

import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springexample.aop.tagert.arg.GeneraicBean;
import org.springexample.aop.tagert.arg.OneArgBean;

public class TestArgs extends SpringTestBasic {

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("aoparg.xml");
		return filelist;
	}

	@Test
	public void getOneArg(){
		OneArgBean bean = this.getContext().getBean("oneargbean",OneArgBean.class);
		
		System.out.println("AOP run");
		bean.getTest("a");
		
		System.out.println("////////////////////////////////////////////");
		System.out.println("AOP Not run");
		bean.getTest("a","b");
	}
	
	@Test
	public void getGenericArg(){
		GeneraicBean bean = this.getContext().getBean("GenBean",GeneraicBean.class);
		
		System.out.println("AOP run");
		bean.getTest(1.0);
		bean.getTest(1.0,"a",1L);
		System.out.println("////////////////////////////////////////////");
		System.out.println("AOP Not run");
		bean.getTest("a",1L);
	}
}
