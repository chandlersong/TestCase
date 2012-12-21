package com.hilatest.googledata.comm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.hilatest.googledata.comm.Login.GoogleLogin.AccountType;
import com.hilatest.googledata.exception.ConnectionException;
import com.hilatest.googledata.exception.GoogleServiceDownException;
import com.hilatest.googledata.exception.LoginException;
import com.hilatest.googledata.exception.LoginException.BadAuthenticationException;
import com.hilatest.googledata.exception.LoginException.NotVerifiedException;
import com.hilatest.googledata.exception.LoginException.TermsNotAgreedException;

public class LoginImpl implements Login {

	public static final int  STATUS_OK =200;
	public static final int  STATUS_FAIL =403;
	
	public static final String AUTH="Auth";
	public static final String ERROR="Error";
	
	private static interface ErrorCode{
		public static final String BadAuthentication="BadAuthentication";
		
		public static final String NotVerified="NotVerified";
		
		public static final String TermsNotAgreed="TermsNotAgreed";
	}
	private  String servicename;
	
	private  String username;
	
	private  String password;
	
	public LoginImpl() {
		super();
	
	}
	
	public LoginImpl(String username, String password,String servicename) {
		this();
		this.servicename = servicename;
		this.username = username;
		this.password = password;
	}

	public LoginImpl(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}
	
	public String getAuth(String username, String password,String servicename)
			throws LoginException {
		
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL); 
        try {
        	List <NameValuePair> nvps = new ArrayList <NameValuePair>(); 
        	nvps.add(new BasicNameValuePair(GoogleLogin.ACCOUNT_TYPE,AccountType.HOSTED_OR_GOOGLE));
        	nvps.add(new BasicNameValuePair(GoogleLogin.EMAIL, username));
        	nvps.add(new BasicNameValuePair(GoogleLogin.PASSWD, password));
        	nvps.add(new BasicNameValuePair(GoogleLogin.SERVICE, servicename));
        	nvps.add(new BasicNameValuePair(GoogleLogin.SOURCE, GoogleLogin.APPLIACTION_NAME));     
			post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
				throw new IllegalArgumentException("don't support utf-8");
		
		} 
		
		HttpResponse response;
		
		try {
			response = httpclient.execute(post);
		} catch (ClientProtocolException e) {
			throw new ConnectionException("can't connect:"+URL);
		} catch (IOException e) {
			throw new ConnectionException("can't connect:"+URL);
		}
		
		//parse the text of result.and encapsulate it into a property.
		String body=null; 
		Properties pp= null;
		try {
			body = EntityUtils.toString(response.getEntity());
			pp = new Properties();
			InputStream   is   =   new   ByteArrayInputStream(body.getBytes());
			pp.load(is);
			
	     } catch (ParseException e) {
	    	 throw new GoogleServiceDownException("can't not parse Google result:"+body);
		 } catch (IOException e) {
			 throw new GoogleServiceDownException("can't not parse Google result:"+body);
		 }
		 
		//login success 
		if(response.getStatusLine().getStatusCode()==STATUS_OK){
			return pp.getProperty(AUTH);
		}else{
			
			String errorcode = pp.getProperty(ERROR); 
			if(errorcode.equals(ErrorCode.BadAuthentication)){
				throw new BadAuthenticationException("The login request used a username or password that is not recognized");
			}else if(errorcode.equals(ErrorCode.NotVerified)){
				throw new NotVerifiedException();
			}else if(errorcode.equals(ErrorCode.TermsNotAgreed)){
				throw new TermsNotAgreedException();
			}
		}
		
		return null;
	}

	public String getAuth() throws LoginException {
		return this.getAuth(this.getUsername(),
				             this.getPassword(), 
				             this.getServicename());
	}

	public String getAuth(String servicename) throws LoginException {
		return this.getAuth(this.getUsername(),
							 this.getPassword(), 
	                          servicename);
	}

	public void setService(String servicename) {
		this.setService(servicename);
	}

	/**
	 * @return the servicename
	 */
	public String getServicename() {
		return servicename;
	}

	/**
	 * @param servicename the servicename to set
	 */
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
