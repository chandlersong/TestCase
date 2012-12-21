package org.springexample;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class SpringTestBasic {

	private ApplicationContext context;

	@Before
	public void inital(){
		context =
			new ClassPathXmlApplicationContext(this.getConfigureList());
	}
	
	protected final String[] getConfigureList(){	 

		List<String> filelist = this.addConfigfile(new ArrayList<String>());
		
		return filelist.toArray(new String[0]);
	}

	protected abstract List<String> addConfigfile(List<String> filelist);
	
	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	
	
}
