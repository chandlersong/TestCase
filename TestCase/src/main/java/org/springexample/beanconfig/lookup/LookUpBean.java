package org.springexample.beanconfig.lookup;

import org.springexample.BasicBean;

public class LookUpBean extends BasicBean {

	private String message;
	
	public LookUpBean(){
		
	}
	
	public LookUpBean(String message){
		
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
