package org.springexample.beanconfig;


import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springexample.beanconfig.lookup.LookUpBean;
import org.springexample.beanconfig.lookup.LookUpFactory;

/**
 * Tip:
 * 1.因为lookup的方法是不能加入参数的。所以可以用一个来作为loopup的方法，其他调用这个来传参数。
 * @author chandler.song
 *
 */
public class TestLookUp extends SpringTestBasic {

	public static final String LookupBean = "Lookup";
	
	public static final String LookupFactroy = "LookupFactory";
	
	
	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("LookUpBean.xml");
		return filelist;
	}
	
	@Test
	public void testBasic(){
		
		LookUpFactory factory = this.getContext().getBean(LookupFactroy, LookUpFactory.class);
		
		LookUpBean bean = factory.getBean();
		
		bean.Success();
	}
	
	
	/**
	 * test with parameter
	 */
	@Test
	public void testImplementedMethod(){
		
		LookUpFactory factory = this.getContext().getBean(LookupFactroy, LookUpFactory.class);
		
		LookUpBean bean = factory.getBeanImplemented();
		
		bean.Success();
	}

}
