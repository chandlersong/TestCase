package me.study.java.guava.collections;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

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


    @Test
    public void testSetStaticMethod() {
        Set<String> set1 = Sets.newHashSet("a", "b", "c", "d", "e");
        Set<String> set2 = Sets.newHashSet("b", "c", "d", "f", "g");

        logger.info("Sets. union:{}", Sets.union(set1, set2));
        logger.info("Sets.different:{}", Sets.difference(set1, set2));
        logger.info("Sets.intersection:{}", Sets.intersection(set1, set2));
        logger.info("Sets.symmetricDifference:{}", Sets.symmetricDifference(set1, set2));
        logger.info("Sets.cartesianProduct:{}", Sets.cartesianProduct(set1, set2));
        logger.info("Sets.powerSet:{}", Sets.powerSet(set1));

    }

    @Test
    public void testMapCompareMethod() {
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        logger.info("diff.entriesInCommon:{}", diff.entriesInCommon()); // {"b" => 2}
        logger.info("diff.entriesInCommon:{}", diff.entriesDiffering()); // {"c" => (3, 4)}
        logger.info("diff.entriesInCommon:{}", diff.entriesOnlyOnLeft()); // {"a" => 1}
        logger.info("diff.entriesInCommon:{}", diff.entriesOnlyOnRight()); // {"d" => 5}
    }
}
