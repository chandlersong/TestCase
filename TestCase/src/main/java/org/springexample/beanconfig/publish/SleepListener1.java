package org.springexample.beanconfig.publish;

import org.springframework.context.ApplicationListener;

public class SleepListener1 implements ApplicationListener<SleepEvent> {

	public void onApplicationEvent(SleepEvent event) {
        
		long thread_id = Thread.currentThread().getId();
		System.out.println("handler thread id:"+thread_id);
		
        System.out.println("it's "+event.getHour()+" o'clock, go to bed");
        System.out.println(this);
	}



}
