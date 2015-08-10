package org.springexample.beanconfig.publish.superevent;

import org.springframework.context.ApplicationEvent;

public class SuperEvent extends ApplicationEvent {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1079836657312817369L;

    public SuperEvent(Object source) {
        super(source);
    }

}
