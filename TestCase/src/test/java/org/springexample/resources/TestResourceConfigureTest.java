package org.springexample.resources;

import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springexample.resource.ResourceConfigureBean;

public class TestResourceConfigureTest extends SpringTestBasic{


	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("ResourceConfigure.xml");
		return filelist;
	}
	@Test
	public void testBasic(){
		ResourceConfigureBean bean = this.getContext().getBean("resourceconfigure", ResourceConfigureBean.class);
	    System.out.println(bean.getR().getClass());
	}


	
}
