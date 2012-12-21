package org.springexample.beanconfig.methodreplace;

import java.lang.reflect.Method;

import org.springexample.BasicBean;
import org.springframework.beans.factory.support.MethodReplacer;

public class MethodReplacerBean extends BasicBean implements MethodReplacer {

	public Object reimplement(Object obj, Method method, Object[] args)
			throws Throwable {
		
		return this.Success();
	}

}
