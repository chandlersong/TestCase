package org.springexample.beanconfig.publish.multhandler;

import org.springframework.context.ApplicationListener;

public class MultiEventHandler implements ApplicationListener<MultiEvent> {
     
	private String description;
	
	
   
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	public void onApplicationEvent(MultiEvent event) {
	
		System.out.println("it's "+this.description);
		long thread_id = Thread.currentThread().getId();
		System.out.println("handler thread id:"+thread_id);
        System.out.println(this);
	}

}
