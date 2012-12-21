package com.hilatest.googledata.ajaxsearch;

import org.junit.Test;

public class CursorTest extends AJAXSearchBasic{
	
	@Test
	public void cursorTest(){
		
		System.out.print("first page");
		this.printResult(this.doSearch("0"));
		
		System.out.print("second page");
		this.printResult(this.doSearch("8"));
	}

}
