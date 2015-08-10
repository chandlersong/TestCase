package org.springexample.beanconfig.publish.multhandler;

import org.springframework.context.ApplicationEvent;

public class MultiEvent extends ApplicationEvent {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3290008560363983265L;

    public MultiEvent(Object source) {
        super(source);
    }

}
