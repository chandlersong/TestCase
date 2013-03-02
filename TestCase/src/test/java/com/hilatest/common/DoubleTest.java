package com.hilatest.common;

import java.util.Random;

import org.junit.Test;

public class DoubleTest {

    /**
     * 以2为低的指数。
     * 且，不会小数。只是取小数部分
     */
    @Test
    public void getExponentTest() {

        int exponent = new Random().nextInt(100);
        Double value = Math.pow(2, exponent) + 3;

        System.out.println("value:" + value);
        System.out.println("exponent:" + exponent);
        System.out.println("Math.getExponent(value):" + Math.getExponent(value));
    }

    @Test
    public void NaNTest() {
        System.out.println("Double.NaN == Double.NaN:" + (Double.NaN == Double.NaN));
    }

    /**
     * 1>NaN:false
     * 1<NaN:false
     */
    @Test
    public void NanTest() {
        System.out.println("1>NaN:" + (1.0 > Double.NaN));
        System.out.println("1<NaN:" + (1.0 < Double.NaN));
    }

    /**
     * -0.5:0
     * -0.9:0
     * -1.1:-1
     * 1.1:1
     * 0.9:0
     */
    @Test
    public void toIntValueTest() {
        Double value = -0.5;
        System.out.println("-0.5:" + value.intValue());
        value = -0.9;
        System.out.println("-0.9:" + value.intValue());
        value = -1.1;
        System.out.println("-1.1:" + value.intValue());
        value = 1.1;
        System.out.println("1.1:" + value.intValue());
        value = 0.9;
        System.out.println("0.9:" + value.intValue());
    }
}
