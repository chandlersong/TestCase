package org.springexample.expressionlanguage;

import net.sf.cglib.core.Local;

public class ConfigureExpressionBean {

	private int randomNumber;

	private Local defaultLocale;
	
	public int getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	public Local getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(Local defaultLocale) {
		this.defaultLocale = defaultLocale;
	}
		
	
}
