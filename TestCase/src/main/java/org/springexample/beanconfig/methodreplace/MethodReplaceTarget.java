package org.springexample.beanconfig.methodreplace;

import org.springexample.BasicBean;

public class MethodReplaceTarget extends BasicBean{

	public String toBeReplaced(){
		
		return this.Failed();
	}
}
