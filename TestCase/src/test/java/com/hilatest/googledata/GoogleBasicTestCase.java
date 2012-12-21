package com.hilatest.googledata;

import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Before;

import com.hilatest.googledata.comm.Login;
import com.hilatest.googledata.comm.LoginImpl;
import com.hilatest.googledata.exception.LoginException;

public abstract class GoogleBasicTestCase {

	private String auth;
	
	@Before
	public void init() throws LoginException{
		String username ="";
		String password ="";
		Login loginutil = new LoginImpl(username,password,this.getServicename());
		auth = loginutil.getAuth();
	}
	
	public<T extends HttpRequestBase> T  sethead(T method){
		String name = "Authorization";
		String value = String.format("GoogleLogin auth=%1$s"
				                      ,auth);
		
		method.setHeader(name, value);
		method.setHeader("GData-Version", "2");
		return method;
	}
	
	protected abstract String getServicename();
}
