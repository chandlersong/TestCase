package com.hilatest.googledata.basic.clientlogin;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Before;

import com.hilatest.googledata.basic.clientlogin.testclientlogin.GoogleLogin.AccountType;
import com.hilatest.googledata.comm.GoogleService;
import com.hilatest.googledata.comm.Login;
import com.hilatest.googledata.comm.LoginImpl;
import com.hilatest.googledata.exception.LoginException;

public class testclientlogin {

	public static final String URL="https://www.google.com/accounts/ClientLogin";
	
	public static interface GoogleLogin{
		/*
		 * Type of account to request authorization for. Possible values are:
		 * GOOGLE (get authorization for a Google account only) 
		 * HOSTED (get authorization for a hosted account only) 
		 * HOSTED_OR_GOOGLE (get authorization first for a hosted account; if attempt fails, get authorization for a Google account)
		 * Use HOSTED_OR_GOOGLE 
		 * if you're not sure which type of account you want authorization for.
		 * If the user information matches both a hosted and a Google account, only the hosted account is authorized
		 */
		public static final String ACCOUNT_TYPE = "accountType";
		
		public static interface AccountType{
			public static String GOOGLE = "GOOGLE";
			
			public static String HOSTED = "HOSTED";
			
			public static String HOSTED_OR_GOOGLE = "HOSTED_OR_GOOGLE";
		}
		
		//User's full email address. It must include the domain (i.e. johndoe@gmail.com).
		public static final String EMAIL= "Email";
		
		//User's password.
		public static final String PASSWD="Passwd";
		
		/*
		 *  Name of the Google service you're requesting authorization for. 
		 *  Each service using the Authorization service is assigned a name value;
		 *  for example, the name associated with Google Calendar is 'cl'. 
		 *  This parameter is required when accessing services based on Google Data APIs. 
		 *  For specific service names, refer to the service documentation.
		 */
		public static final String SERVICE="service";
		
		/*
		 * Short string identifying your application, for logging purposes. This string should take the form:
		 * "companyName-applicationName-versionID".
		 */
		public static final String SOURCE="source";
		
		public static final String APPLIACTION_NAME = "hilatest-googletest-1.0";
		/*
		 *(optional) Token representing the specific CAPTCHA challenge.
		 * Google supplies this token and the CAPTCHA image URL in a login failed response with the error code "CaptchaRequired".
		 */
		public static final String lOGINTOKEN ="logintoken";
		
		//(optional) String entered by the user as an answer to a CAPTCHA challenge.
		public static final String LOGINCAPTCHA ="logincaptcha";
		
	}
	
	/*
	 * Exception
	 * Error code	Description
	   BadAuthentication	The login request used a username or password that is not recognized.
       NotVerified	The account email address has not been verified. The user will need to access their Google account directly to resolve the issue before logging in using a non-Google application.
       TermsNotAgreed	The user has not agreed to terms. The user will need to access their Google account directly to resolve the issue before logging in using a non-Google application.
       CaptchaRequired	A CAPTCHA is required. (A response with this error code will also contain an image URL and a CAPTCHA token.)
       Unknown	The error is unknown or unspecified; the request contained invalid input or was malformed.
       AccountDeleted	The user account has been deleted.
       AccountDisabled	The user account has been disabled.
       ServiceDisabled	The user's access to the specified service has been disabled. (The user account may still be valid.)
       ServiceUnavailable	The service is not available; try again later.
	 * 
	 */
	private String account;
	private String password;
	
	@Before
	public void initial(){
		account="";
		password= "";
	}
	
	public void testClientLogin() throws ClientProtocolException, IOException{
		
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL); 
        
        List <NameValuePair> nvps = new ArrayList <NameValuePair>(); 
        nvps.add(new BasicNameValuePair(GoogleLogin.ACCOUNT_TYPE,AccountType.HOSTED_OR_GOOGLE));
        nvps.add(new BasicNameValuePair(GoogleLogin.EMAIL, account));
        nvps.add(new BasicNameValuePair(GoogleLogin.PASSWD, password));
        nvps.add(new BasicNameValuePair(GoogleLogin.SERVICE, "cl"));
        nvps.add(new BasicNameValuePair(GoogleLogin.SOURCE, GoogleLogin.APPLIACTION_NAME));
        post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));            //将参数传入post方法中
      /* 
         HttpParams qparams = new BasicHttpParams()
        qparams.setParameter(GoogleLogin.ACCOUNT_TYPE, AccountType.HOSTED_OR_GOOGLE);
        qparams.setParameter(GoogleLogin.EMAIL, account);
        qparams.setParameter(GoogleLogin.PASSWD, password);
        qparams.setParameter(GoogleLogin.SERVICE, "cl");
        qparams.setParameter(GoogleLogin.SOURCE, GoogleLogin.APPLIACTION_NAME);
        post.setParams(qparams);*/
        
        System.out.println("post:");
        System.out.println(post.getURI());
        System.out.println("/////////////////////////////////////");
        
        HttpResponse response = httpclient.execute(post);
    
        Header[] headers =response.getAllHeaders();
        
        System.out.println("status:"+response.getStatusLine().getStatusCode());
        System.out.println("/////////////////////////////////////");
        for(Header h:headers){
        	System.out.println(h.getName()+":"+h.getValue());
        }
        
        System.out.println("body:");
        String body = EntityUtils.toString(response.getEntity());
        System.out.println(body);
        System.out.println("/////////////////////////////////////");
        Properties pp = new Properties();
        //Improve
        InputStream   is   =   new   ByteArrayInputStream(body.getBytes());
        pp.load(is);       
        System.out.print("Auth:"+pp.getProperty("Auth"));
        
        httpclient.getConnectionManager().shutdown();
	}
	
	public void testLoginSuccess() throws LoginException{
		System.out.println("Start to test login successful flow");
		Login login = new LoginImpl();
		String auth = login.getAuth(account, password,GoogleService.CALENDAR_DATA_API);		
		Assert.assertNotNull(auth);
		System.out.println("auth:"+auth);
	}
	

	public void testLoginWrongPassword() throws LoginException{
		Login login = new LoginImpl();
		String wrongpassword = "xxxxxx";
		
		this.testLoginSuccess();
		
		Assert.assertNotSame(wrongpassword, password);
		
		String auth = login.getAuth(account, wrongpassword,GoogleService.CALENDAR_DATA_API);
		
		System.out.println("auth:"+auth);
	}
}
