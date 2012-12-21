package org.springexample.beanconfig;

import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springexample.beanconfig.methodreplace.MethodReplaceTarget;

public class TestMethodBeReplaced extends SpringTestBasic{
	
	public final static String BEAN_NAME ="replacetraget";

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("MethodReplace.xml");
		return filelist;
	}
	@Test
	public void testBasic(){
		
		MethodReplaceTarget bean = this.getContext().getBean(BEAN_NAME, MethodReplaceTarget.class);
		
		bean.Success();
	}
}
