package org.springexample.beanconfig.lookup;

/**
 * Look up test
 * 
 * @author Chandler.Song
 *
 */
public abstract class LookUpFactory {

    public abstract LookUpBean getBean();

    public LookUpBean getBeanImplemented() {
        return null;
    }

}
