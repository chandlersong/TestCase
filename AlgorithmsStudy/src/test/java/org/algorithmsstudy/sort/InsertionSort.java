package org.algorithmsstudy.sort;

import org.apache.log4j.Logger;
import org.junit.Test;

public class InsertionSort {

    private static Logger logger = Logger.getLogger(InsertionSort.class);

    @Test
    public void testSort() {
        logger.info("InsertionSort--testSort");
        int length = 10;
        int[] array = arrayUtils.createRandomLittleIntArrays(length);
        logger.info("initial order:" + arrayUtils.printIntArray(array));
        for (int j = 1; j < length; j++) {
            int key = array[j];

            int i = j - 1;
            while (i >= 0 && array[i] > key) {
                array[i + 1] = array[i];
                i = i - 1;
                logger.info("in i loop, i = " + i + ",j= " + j + ",key =" + key + ":" + arrayUtils.printIntArray(array));

            }

            array[i + 1] = key;
            logger.info("in j loop, i = " + i + ",j= " + j + ",key =" + key + ":" + arrayUtils.printIntArray(array));
        }

        logger.info("final order:" + arrayUtils.printIntArray(array));

    }

    ArraysUtils arrayUtils = new ArraysUtils();
}
