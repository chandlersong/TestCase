package org.springexample.beanconfig.publish.superevent;

import org.springframework.context.ApplicationListener;

public class SuperEventListener implements ApplicationListener<SuperEvent> {

	public void onApplicationEvent(SuperEvent event) {
		long thread_id = Thread.currentThread().getId();
		System.out.println("handler thread id:"+thread_id);
		
        System.out.println("Super handler");
        System.out.println(this);

	}

}
