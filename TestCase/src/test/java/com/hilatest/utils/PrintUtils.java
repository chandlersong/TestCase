package com.hilatest.utils;

public class PrintUtils {

    public static void printArray(Object[] array){
       
        System.out.println("array size:"+array.length);
        int index = 1;
        for(Object object:array){
           System.out.println("index "+index+":"+object.toString());
           index++;
        }
    }
}
