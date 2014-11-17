package com.hilatest.common;

import org.junit.Test;

import com.hilatest.utils.PrintUtils;

public class StringTest {

    @Test
    public void testContains() {
        String st = "";
        String test = "abcd";

        System.out.println(test.contains(st));

    }

    @Test
    public void testIndexOf() {
        char existedCH = '.';
        char notExistedCH = '@';
        String test = "ab.cd";

        int indexof = test.indexOf(existedCH);
        System.out.println("existed index of:" + indexof);
        System.out.println("not existed index of:" + test.indexOf(notExistedCH));

        System.out.println("after:" + test.substring(indexof));
    }

    /**
     * replace，或者其他String的方法，并不改变原来的值，
     * 因为String是不可变的。处理的往往是返回值。
     */
    @Test
    public void testReplace() {

        String s = "java";

        String result = s.replace("j", "l");
        System.out.println("origin s:" + s);
        System.out.println("replaced result:" + result);
    }

    /**
     * 演示SubString泄漏内存
     */
    @Test
    public void testSubStringLeak() {

        String subString = this.subString();

        /**
         * 在这里设断点。可以看到subString中的Value还是10个Char的数组
         * value的值
         * [a, b, c, d, e, f, g, h, i, g, h, m, n]
         */
        System.out.println(subString);
    }

    private String subString() {

        String origin = "abcdefghighmn";

        return origin.substring(0, 2);

    }

    @Test
    public void testSplit() {

        String target = ",2";
        String[] result = target.split(",");
        System.out.println("split ',2':");
        PrintUtils.printArray(result);

        target = "2,";
        result = target.split("2,");
        System.out.println("split '2,':");
        PrintUtils.printArray(result);

        target = ",";
        result = target.split(",");
        System.out.println("split ',':");
        PrintUtils.printArray(result);
    }

    @Test
    public void testSplitWithTwoParamter() {

        String target = "1:2:3";
        String[] result = target.split(":", 2);
        System.out.println("split '1^2^3':");
        PrintUtils.printArray(result);

    }

    @Test
    public void testString() {

        if (true) {
            String a = "a";
            String b = "a";
            System.out.println("a:" + System.identityHashCode(a));
            System.out.println("b:" + System.identityHashCode(b));
            System.out.println("a==b:" + (a == b));
        }

        if (true) {
            String a = "a";
            // 打印内存地址
            System.out.println("a:" + System.identityHashCode(a));
        }

    }
}
