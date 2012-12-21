package com.hilatest.googledata.exception;

/**
 * Login时候的Exception
 * @author chandler
 *
 */
public abstract class LoginException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3970939621005238140L;

	public LoginException() {
		super();
	}

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 密码错
	 * @author chandler
	 *
	 */
	public static class BadAuthenticationException extends LoginException{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2345697604662689061L;

		public BadAuthenticationException() {
			super();
		}

		public BadAuthenticationException(String message, Throwable cause) {
			super(message, cause);
		}

		public BadAuthenticationException(String message) {
			super(message);
		}

		public BadAuthenticationException(Throwable cause) {
			super(cause);

		}
		
	}

	/**
	 * The account email address has not been verified. 
	 * The user will need to access their Google account directly to resolve the issue before logging in using a non-Google application.
	 * @author chandler
	 *
	 */
	public static class NotVerifiedException extends LoginException{



		/**
		 * 
		 */
		private static final long serialVersionUID = 7647091257990152616L;

		public NotVerifiedException() {
			super();
		}

		public NotVerifiedException(String message, Throwable cause) {
			super(message, cause);
		}

		public NotVerifiedException(String message) {
			super(message);
		}

		public NotVerifiedException(Throwable cause) {
			super(cause);

		}
		
	}
	
	/**
	 * The account email address has not been verified. 
	 * The user will need to access their Google account directly to resolve the issue before logging in using a non-Google application.
	 * @author chandler
	 *
	 */
	public static class TermsNotAgreedException extends LoginException{


		/**
		 * 
		 */
		private static final long serialVersionUID = -7936686934896452707L;

		public TermsNotAgreedException() {
			super();
		}

		public TermsNotAgreedException(String message, Throwable cause) {
			super(message, cause);
		}

		public TermsNotAgreedException(String message) {
			super(message);
		}

		public TermsNotAgreedException(Throwable cause) {
			super(cause);

		}
		
	}
}
