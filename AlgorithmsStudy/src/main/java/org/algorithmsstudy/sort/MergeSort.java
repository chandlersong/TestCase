package org.algorithmsstudy.sort;

import java.util.Arrays;

import org.apache.log4j.Logger;

public class MergeSort<T> {

    private static Logger logger = Logger.getLogger(MergeSort.class);

    private ArraysUtils arrayUtls = new ArraysUtils();

    public void sort(Comparable<T>[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(Comparable<T>[] array, int start, int end) {
        if (start == end) {
            return;
        }
        logger.info("MergeSort,initial:" + arrayUtls.printArray(array, start, end + 1) + ",start:" + start + ",end:"
                + end);
        int middle = (start + end) / 2;
        sort(array, start, middle);
        logger.info("MergeSort--first,array:" + arrayUtls.printArray(array, start, middle + 1) + ",start:" + start
                + ",end is middle:" + middle);
        sort(array, middle + 1, end);
        logger.info("MergeSort--second,array:" + arrayUtls.printArray(array, middle + 1, end + 1)
                + ",start is middle+1, middle:" + middle
                + ",end:" + end);
        Merge(array, start, middle, end);
        logger.info("after sort, arrays:" + arrayUtls.printArray(array, start, end + 1)
                + ",start" + start
                + ",end:" + end);

    }

    @SuppressWarnings("unchecked")
    private void Merge(Comparable<T>[] array, int start, int middle, int end) {

        logger.info("MergeSort,Merge:" + arrayUtls.printArray(array, start, end + 1) + ",start:" + start + ",middle:"
                + middle + ",end:" + end);

        Comparable<T>[] firstPart = Arrays.copyOfRange(array, start, middle + 1);
        Comparable<T>[] secondPart = Arrays.copyOfRange(array, middle + 1, end + 1);

        int firstPartIndex = 0;
        int secondPartIndex = 0;
        int firstPartLength = firstPart.length;
        int secondPartLength = secondPart.length;

        for (int i = start; i <= end; i++) {

            if (firstPartIndex >= firstPartLength) {
                for (int j = secondPartIndex; j < secondPartLength; j++) {
                    array[i] = secondPart[j];
                    i++;
                }
                return;
            }

            if (secondPartIndex >= secondPartLength) {
                for (int j = firstPartIndex; j < firstPartLength; j++) {
                    array[i] = firstPart[j];
                    i++;
                }
                return;
            }

            Comparable<T> firstEntry = firstPart[firstPartIndex];
            Comparable<T> secondaryEntry = secondPart[secondPartIndex];
            if (firstEntry.compareTo((T) secondaryEntry) < 0) {
                array[i] = firstEntry;
                firstPartIndex++;
            } else {
                array[i] = secondaryEntry;
                secondPartIndex++;
            }

        }
    }
}
