package org.springexample.beanconfig;

import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;

public class TestMergeBean extends SpringTestBasic {


	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("MergeList.xml");
		return filelist;
	}

	@Test
	public void testMerge(){
		System.out.println("Text Merge");
		
		MergeBean bean = this.getContext().getBean("mergebean", MergeBean.class);
		
		List<String> mergelist = bean.getMergeitem();
		
		for(String i:mergelist){
			System.out.println(i);
		}
	}
	
	/**
	 * Default value of merge attr is false;
	 */
	@Test
	public void testNotMerge(){
		System.out.println("Text Not Mergen");
		
		MergeBean bean = this.getContext().getBean("notmergebean", MergeBean.class);
		
		List<String> mergelist = bean.getMergeitem();
		
		for(String i:mergelist){
			System.out.println(i);
		}
		
	}
}
