package org.springexample.beanconfig;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springexample.BasicBean;
import org.springexample.SpringTestBasic;
import org.springframework.context.support.AbstractApplicationContext;

public class TestinitialAndDestory extends SpringTestBasic{

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("InitialAndDestroy.xml");
		return filelist;
	}
	
	@Test
	public void testBasic(){
		
		InitialAndDestoryBean bean = this.getContext().getBean("initialanddestory", InitialAndDestoryBean.class);
		Assert.assertEquals(BasicBean.SUCCESS, bean.Success());
		
		//close context
		((AbstractApplicationContext)this.getContext()).registerShutdownHook();
	}

}
