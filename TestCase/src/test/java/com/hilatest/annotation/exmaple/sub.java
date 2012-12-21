package com.hilatest.annotation.exmaple;

public class sub extends superclass {

	final String cont = "sub";
	@AnnototionTest(id=3,description=cont)
	@Override
	public void printAnnation() throws SecurityException, NoSuchMethodException {
		// TODO Auto-generated method stub
		super.printAnnation();
	}

}
