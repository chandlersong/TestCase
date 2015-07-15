package org.algorithmsstudy.sort;

import org.apache.log4j.Logger;
import org.junit.Test;

public class MergeSortTest {

    private static Logger logger = Logger.getLogger(MergeSortTest.class);

    @Test
    public void testSort() {
        logger.info("start MergeSortTest -- testSort");
        Integer[] array = arrayUtils.createRandomLittleIntArrays(10);
        // Integer[] array = new Integer[] { 161, 115, 324, 770, 602, 691, 105, 556, 987, 63 };
        logger.info("initial order:" + arrayUtils.printIntArray(array));

        sortTools.sort(array);

        logger.info("final order:" + arrayUtils.printArray(array));
    }

    ArraysUtils arrayUtils = new ArraysUtils();

    MergeSort<Integer> sortTools = new MergeSort<Integer>();
}
