package org.springexample;

public class BasicBean {
	
	public static final String SUCCESS="success";
	
	public static final String FAILED="Failed";
	
	public String Success(){
		System.out.println(SUCCESS);
		return SUCCESS;
	}
	
	public String Failed(){
		System.out.println(FAILED);
		return FAILED;
	}
}
