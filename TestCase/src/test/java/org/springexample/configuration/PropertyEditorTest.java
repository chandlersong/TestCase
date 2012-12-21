package org.springexample.configuration;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springexample.propertyeditor.DependsOnExoticType;

public class PropertyEditorTest extends SpringTestBasic{

    @Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("propertyeditor.xml");
		return filelist;
	}

    @Test
    public void testBasic(){
    	DependsOnExoticType bean = this.getContext().getBean("DependsOnExoticType", DependsOnExoticType.class);
    	System.out.println(bean.getType().getName());
		Assert.assertEquals("SUCCESS",bean.getType().getName());
    }
}
