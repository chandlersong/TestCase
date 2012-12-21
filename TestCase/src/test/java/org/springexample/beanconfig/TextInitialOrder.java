package org.springexample.beanconfig;


import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;

public class TextInitialOrder extends SpringTestBasic {

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("InitialOrder.xml");
		return filelist;
	}
	/**
	 * this test failed
	 * 
	 * please see Startup and shutdown callbacks in reference.
	 */
	@Test
	public void testBasic(){
		this.getContext();
	
	}

}
