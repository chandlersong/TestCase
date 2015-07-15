package org.algorithmsstudy.sort;

import java.util.Random;

public class ArraysUtils {

    private static final String STRING_BLANK = " ";
    private Random r = new Random();

    public Integer[] createRandomIntArrays(int length, int maxNumber) {
        Integer[] result = new Integer[length];
        for (int i = 0; i < length; i++) {
            result[i] = r.nextInt(maxNumber);
        }
        return result;
    }

    public Integer[] createRandomIntArrays(int length) {
        return createRandomIntArrays(length, Integer.MAX_VALUE);
    }

    public Integer[] createRandomLittleIntArrays(int length) {
        return createRandomIntArrays(length, 1000);
    }

    public String printIntArray(Integer[] array) {
        StringBuffer printer = new StringBuffer();

        int length = array.length;
        for (int i = 0; i < length; i++) {
            printer.append(array[i]);
            printer.append(STRING_BLANK);
        }

        return printer.toString();
    }
}
