package com.hilatest.apachecommon3.text;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.text.CompositeFormat;
import org.junit.Before;
import org.junit.Test;



public class CompositeFormatTest {

    private Format parse;
    private Format formater;
    
    private CompositeFormat compositeFormat;
    
    @Before
    public void initial(){
        parse =  new SimpleDateFormat("yyyy.MM.dd");       
        formater =  new SimpleDateFormat("MM.dd.yyyy");
        
        compositeFormat = new CompositeFormat(parse,formater);    
    }
    
    
    @Test
    public void testFormat(){

        
        System.out.println(compositeFormat.format(new Date()));
    }
 
}
