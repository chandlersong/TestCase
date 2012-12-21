package com.hilatest.thread;

import org.junit.Test;

public class TestThread {

	@Test
	public void TestDuplicateStart(){
		   RunnableObject testThread = new RunnableObject();
		   Thread thread = new Thread(testThread);
		   testThread.run();
		   thread.start();
		   thread.start();
	}
}

class RunnableObject implements Runnable{
	public void run() {
		  System.out.println("Running");
    }
}