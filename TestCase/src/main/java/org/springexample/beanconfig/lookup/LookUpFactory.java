package org.springexample.beanconfig.lookup;

/**
 * 这里测试Spring的Look-up测试。
 * @author Chandler.Song
 *
 */
public abstract class LookUpFactory {

	public abstract LookUpBean getBean();
	
	public LookUpBean getBeanImplemented(){
		return null;
	}
	
}
