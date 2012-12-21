package com.hilatest.common;

import java.security.SecureRandom;

import org.junit.Test;


public class RandomTest {

    @Test
    public void testSecureRandom(){
        SecureRandom r = new SecureRandom();
        
        byte[] salt = r.generateSeed(8);
        
        System.out.print(salt);
    }
}
