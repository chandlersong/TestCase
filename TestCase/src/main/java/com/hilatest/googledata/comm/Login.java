package com.hilatest.googledata.comm;

import com.hilatest.googledata.exception.LoginException;

public interface Login {

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
	
	public String getAuth(String username,String password,String servicename) throws LoginException;
	
	public String getAuth() throws LoginException;
	
	public String getAuth(String servicename) throws LoginException;
	
	public void setService(String servicename);
}
