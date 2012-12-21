package org.springexample.beanconfig;

import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;

public class TestProperty  extends SpringTestBasic{

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("ProperityExample.xml");
		return filelist;
	}
	
	@Test
	public void testBasic(){
		PropertyBean bean = this.getContext().getBean("propertybean",PropertyBean.class);
		
		System.out.println(bean.getP());
	}
	
}
