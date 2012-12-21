package org.springexample.aop;

import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springexample.aop.introduce.IntroduceInterface;

public class TestIntroduce extends SpringTestBasic{

	@Override
	protected List<String> addConfigfile(List<String> filelist){
		
		filelist.add("aopintroduce.xml");
		return filelist;
	}
	
	@Test
	public void testBasic(){
		IntroduceInterface ibean = this.getContext().getBean("ibean",IntroduceInterface.class);
		
		System.out.println("Class:"+ibean.getClass());
		ibean.testIntroduce();
	}

}
