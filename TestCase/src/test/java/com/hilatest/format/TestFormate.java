package com.hilatest.format;


import org.junit.Test;

public class TestFormate {
   public static String foluma = "<button type=\"submit\" name=\"%1$s\" onclick=\"aa;location.href='%2$s'\">%3$s</button>";
   
   @Test
   public void testcase1(){
	   
	   String out = String.format(foluma,"bb","cc","dd");
	   
	   System.out.print(out);
   }
}
