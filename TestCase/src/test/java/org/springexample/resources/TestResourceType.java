package org.springexample.resources;


import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springframework.context.ApplicationContext;

import org.springframework.core.io.Resource;

public class TestResourceType extends SpringTestBasic{

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		return filelist;
	}

	
	@Test
	public void testBasic(){
		
		ApplicationContext ctx = this.getContext();
		
		Resource ClassPathContextResource = ctx.getResource("resourcestype.txt");
		System.out.println(ClassPathContextResource.getClass());
		
		Resource ClassPathResource = ctx.getResource("classpath:resourcestype.txt");
		System.out.println(ClassPathResource.getClass());
		
		Resource UrlResource = ctx.getResource("file:resourcestype.txt");
		System.out.println(UrlResource.getClass());
		
		Resource template = ctx.getResource("http://myhost.com/resource/path/resourcestype.txt");
		System.out.println(template.getClass());

	}

	
}
