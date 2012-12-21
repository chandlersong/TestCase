package org.springexample.aop;

import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springexample.aop.tagert.others.WithInClassAnnotationBean;

public class TestOther extends SpringTestBasic{

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("aopother.xml");
		return filelist;
	}
	
	@Test
	public void testWithInAnnotationBean(){
		WithInClassAnnotationBean bean = this.getContext().getBean("withinclassannotationbean"
				                                                ,WithInClassAnnotationBean.class);	
		System.out.println(bean.getClass());
		
		bean.test();
	}
}
