package org.springexample.beanconfig.publish.subevent;

import org.springframework.context.ApplicationListener;

public class SubEventlistener implements ApplicationListener<SubEvent> {

	public void onApplicationEvent(SubEvent event) {
		long thread_id = Thread.currentThread().getId();
		System.out.println("handler thread id:"+thread_id);
		
        System.out.println("Sub handler");
        System.out.println(this);
		
	}

}
