package org.springexample.beanconfig;

import org.springframework.context.SmartLifecycle;

public class InitialOrder2 implements SmartLifecycle{

	private String description;
	
	
	
	public InitialOrder2(String description) {
		super();
		this.description = description;
		
		System.out.println("descritpion "+description +" has been initial");
	}

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

	public void start() {
	
		
	}

	public void stop() {
		
	}

	public boolean isRunning() {
	
		return false;
	}

	public int getPhase() {
		return Integer.MAX_VALUE;
	}

	public boolean isAutoStartup() {
		return false;
	}

	public void stop(Runnable callback) {
		
	}

}
