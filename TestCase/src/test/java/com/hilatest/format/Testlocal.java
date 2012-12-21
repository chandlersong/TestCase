package com.hilatest.format;

import java.util.Locale;

import org.junit.Test;

public class Testlocal {

	@Test
	public void testBasic(){
		Locale local = Locale.getDefault();
		
		System.out.println("Display Language:"+local.getDisplayLanguage());
		System.out.println("Display Countrye:"+local.getDisplayCountry());
		System.out.println("Display Name:"+local.getDisplayName());
		System.out.println("Display Variant:"+local.getDisplayVariant());
		System.out.println("ISO3 Country:"+local.getISO3Country());
		System.out.println("ISO3 Language:"+local.getISO3Language());
		System.out.println("get Language:"+local.getLanguage());
		System.out.println("get countrey:"+local.getCountry());
		System.out.println("Variant:"+local.getVariant());
		System.out.println("toString:"+local.toString());
	}
}
