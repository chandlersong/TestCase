package org.springexample.beanconfig;

import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springexample.beanconfig.publish.SleepServer;

public class PublishEventTest extends SpringTestBasic {

	public static final String SERVER="publishserver";

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("publishevent.xml");
		return filelist;
	}
	
	/**
	 * here will test only have one event listener
	 */
	@Test
	public void testBasic(){
		
		System.out.println("test bean configue,publish:one event handler");
		SleepServer bean = this.getContext().getBean(SERVER, SleepServer.class);
         
		System.out.println("going to publish");
        //publish
		bean.sleep(SleepServer.ONE_EVENT);
		
		System.out.println("finsih publish");
		
		System.out.println("not going to publish");
        //publish
		bean.sleep(SleepServer.NOT_PUBLISH);
		
		System.out.println("finsih not publish");
		
	}
	
	/**
	 * here will test have multi event listener
	 */
	@Test
	public void testMultihandler(){
		
		System.out.println("test bean configue,publish:one event handler");
		SleepServer bean = this.getContext().getBean(SERVER, SleepServer.class);
         
		System.out.println("going to publish");
        //publish
		bean.sleep(SleepServer.Multi_EVENT);
		
		System.out.println("finsih publish");
				
	}
	
	/**
	 * here will test if a sub event has been throw, the handler for super event will be invoked or not
	 * 
	 * the super handler will be invoke
	 */
	@Test
	public void testSubhandler(){
		
		System.out.println("test bean configue,publish:one event handler");
		SleepServer bean = this.getContext().getBean(SERVER, SleepServer.class);
         
		System.out.println("going to publish");
        //publish
		bean.sleep(SleepServer.SUB_EVENT);
		
		System.out.println("finsih publish");
				
	}
	
	/**
	 * here will test if a super event has been throw, the handler for sub event will be invoked or not
	 * 
	 * the sub  handler will not be invoke
	 */
	@Test
	public void testSuperhandler(){
		
		System.out.println("test bean configue,publish:one event handler");
		SleepServer bean = this.getContext().getBean(SERVER, SleepServer.class);
         
		System.out.println("going to publish");
        //publish
		bean.sleep(SleepServer.SUPER_EVENT);
		
		System.out.println("finsih publish");
				
	}
	
	
	/**
	 * here will test if a news has been publish, and do other information
	 * 
	 * the sub  handler will not be invoke
	 */
	@Test
	public void testConitue(){
		
		System.out.println("test bean configue,publish:one event handler");
		SleepServer bean = this.getContext().getBean(SERVER, SleepServer.class);
         
		System.out.println("going to publish");
        //publish
		bean.sleep(SleepServer.NOT_RETURN);
		
		System.out.println("finsih publish");
				
	}

}
