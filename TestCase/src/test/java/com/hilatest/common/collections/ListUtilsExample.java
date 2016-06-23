package com.hilatest.common.collections;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

public class ListUtilsExample {

    @Test
    public void testPartition() {
        List<Integer> example = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            example.add(i);
        }

        List<List<Integer>> result = ListUtils.partition(example, 3);
        System.out.println(result.size());
        for (List<Integer> small : result) {
            System.out.println(small);
        }

        result = ListUtils.partition(example, 11);
        System.out.println(result.size());
        for (List<Integer> small : result) {
            System.out.println(small);
        }
    }
}
