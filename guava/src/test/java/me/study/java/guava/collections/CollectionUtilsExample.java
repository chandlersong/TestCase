package me.study.java.guava.collections;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.Collections;

import static org.slf4j.LoggerFactory.getLogger;

public class CollectionUtilsExample {

    private static final Logger logger = getLogger(CollectionUtilsExample.class);


    @Test(expected = IllegalArgumentException.class)
    public void testSomeUsefulMethod() {
        Iterable<Integer> concatenated = Iterables.concat(Lists.newArrayList(1, 2, 3, 4, 5),
                Sets.newHashSet(5, 11, 6, 0),
                Ints.asList(5, 6, 7, 8, 9, 0));

        Assert.assertEquals(2, Iterables.frequency(concatenated, 6));
        logger.info("{} partition", Iterables.partition(concatenated, 3));
        logger.info("{} patterns", Iterables.paddedPartition(concatenated, 2));
        Assert.assertEquals(Integer.valueOf(1), Iterables.getFirst(concatenated, 6));
        Assert.assertEquals(Integer.valueOf(0), Iterables.getLast(concatenated, 6));
        Assert.assertEquals(Integer.valueOf(6), Iterables.getLast(Collections.EMPTY_LIST, 6));
        logger.info("{} limit", Iterables.limit(concatenated, 2));
        logger.info("{} only element", Iterables.getOnlyElement(concatenated, -1)); //throw here,

    }
}
