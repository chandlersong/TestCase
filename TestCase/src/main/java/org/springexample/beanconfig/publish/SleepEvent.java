package org.springexample.beanconfig.publish;

import org.springframework.context.ApplicationEvent;

public class SleepEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7176584785631098369L;

	private int hour;
	
	public SleepEvent(Object source, int hour) {
		super(source);
		this.hour = hour;
	}

	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	

}
