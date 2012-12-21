package org.springexample.beanconfig.publish;

import org.springexample.beanconfig.publish.multhandler.MultiEvent;
import org.springexample.beanconfig.publish.subevent.SubEvent;
import org.springexample.beanconfig.publish.superevent.SuperEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class SleepServer implements ApplicationEventPublisherAware {
	
	public static final int NOT_PUBLISH =0;
	public static final int ONE_EVENT =1;
	public static final int Multi_EVENT =2;
	public static final int SUB_EVENT =3;
	public static final int SUPER_EVENT =4;
	public static final int NOT_RETURN =5;
	
	public ApplicationEventPublisher publisher;
	
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
			
		this.publisher = applicationEventPublisher;
	}
	
	public void sleep(int hour){
		
		long thread_id = Thread.currentThread().getId();
		System.out.println("publish server thread id:"+thread_id);
		//go to sleep
		
		
		switch(hour){
		case ONE_EVENT:{
			
			SleepEvent event = new SleepEvent(this,hour);
			this.publisher.publishEvent(event);
			return;
		
		}
		case Multi_EVENT:{
			
			MultiEvent event =new MultiEvent(this);
			this.publisher.publishEvent(event);
			return;
		}
		case SUB_EVENT:{
			
			SubEvent event =new SubEvent(this);
			this.publisher.publishEvent(event);
			return;
		}
		case SUPER_EVENT:{
			
			SuperEvent event =new SuperEvent(this);
			this.publisher.publishEvent(event);
			return;
		}
		case NOT_RETURN:{
			SleepEvent event = new SleepEvent(this,hour);
			this.publisher.publishEvent(event);
			break;
		}
		}
		
		System.out.println("don't need to sleep");
		
	}

}
