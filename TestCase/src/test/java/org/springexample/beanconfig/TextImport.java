package org.springexample.beanconfig;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springexample.BasicBean;
import org.springexample.SpringTestBasic;

/**
 * 
 * @author Chandler.Song
 *
 */
public class TextImport extends SpringTestBasic{


	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("import_super.xml");
		return filelist;
	}
	
	@Test
	public void testBasic(){
		
		System.out.println("Text Import XML File");
		ImportBean bean = this.getContext().getBean("importtest", ImportBean.class);
		bean.Success();
		Assert.assertEquals(BasicBean.SUCCESS, bean.Success());
	}

}
