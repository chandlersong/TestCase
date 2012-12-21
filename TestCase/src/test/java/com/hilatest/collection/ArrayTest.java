package com.hilatest.collection;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ArrayTest {
	
	private static Logger logger = Logger.getLogger(ArrayTest.class);
	
	@Test
	public void ArrayToList(){
		String[] arr = new String[] {"1", "2"};
		List<String> list = Arrays.asList(arr);
		list.add("a");
		logger.info("array type:"+list.getClass());
		logger.info(list);
	}
	
	/**
	 * 主要作用目的是让数组能够完成插入的操作。
	 * 比方说长度为10的char数组，在位置5，插入一个长度为2的数组，产生一个新的数组，长度为12。 
	 */
	@Test
	public void insertChar(){
		
		String src ="abcdefghjk";
		String insert = "xy";
		
		
		char[] srcArray = src.toCharArray();
		char[] desArray = new char[12];
		char[] insertArray = insert.toCharArray();
		
		int position = 5;
		
		//复制Position之前的
		System.arraycopy(srcArray, 0, desArray, 0, position);
		System.arraycopy(insertArray, 0, desArray, position, insert.length());
		System.arraycopy(srcArray, position, desArray, position + insert.length(),src.length()- position);
		
		logger.info("des array size:"+srcArray.length);
		logger.info("des array:"+srcArray.toString());
		
		int i = 1;
		for(char c:desArray){
			logger.info(i+" char:"+c);
			i++;
		}
		
	}
	
	
	@Test
	
	public void testCopyEfficiency(){
		this.testCopyEfficiency(1000,1000);
		
		this.testCopyEfficiency(10000,1000);
		
		this.testCopyEfficiency(1000000,1000);
	}
	
	public void testCopyEfficiency(int size,int loopTimes){
		System.out.println("array Size:"+size);
		System.out.println("loop Times:"+loopTimes);
		
		Object[] srcArray = this.generateArray(size);

		
		this.copyLoop(srcArray);
		
		
		long beginTime;
		long endTime;
		Long LoopSum = 0L;
		
		for(int i =0;i<loopTimes;i++){
			beginTime = new Date().getTime();
			this.copyLoop(srcArray);
			endTime = new Date().getTime();
			LoopSum = LoopSum + (endTime - beginTime);
		}
		System.out.println("loop copy total time:"+(LoopSum));
		System.out.println("loop copy average time:"+(LoopSum.doubleValue()/loopTimes));
		
		
		Long JDKSum = 0L;
		this.copyJDK(srcArray);
		for(int i =0;i<loopTimes;i++){
			beginTime = new Date().getTime();
			this.copyJDK(srcArray);
			endTime = new Date().getTime();
			JDKSum = JDKSum + (endTime - beginTime);
		}
		System.out.println("JDK copy total time:"+(JDKSum));
		System.out.println("JDK copy average time:"+(JDKSum.doubleValue()/loopTimes));
		
	}
	
	
	private Object[] generateArray(int length){
		
		Object[] result = new Object[length];
		for(int i=0;i<length;i++){
			result[i] = new Object();
		}
		
		return result;
		
	}
	private Object[] copyLoop(Object[] orgin){
		Object[] result = new Object[orgin.length];
		
		for(int i=0;i<orgin.length;i++){
			result[i] = orgin[i];
		}	
		return result;
	}
	
	private Object[] copyJDK(Object[] orgin){
		Object[] result = new Object[orgin.length];	
		System.arraycopy(orgin, 0, result, 0, orgin.length);
		return result;
	}
}
