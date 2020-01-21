package me.study.java.guava.collections;

import com.google.common.collect.*;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class RangeCollectionExamples {

    private static final Logger logger = getLogger(RangeCollectionExamples.class);

    @Test
    public void testRangeSet() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}

        Assert.assertTrue(rangeSet.contains(2));
        Assert.assertEquals(Range.closed(1, 5), rangeSet.rangeContaining(2));
        Assert.assertTrue(rangeSet.encloses(Range.closed(2, 3)));
        Assert.assertEquals(Range.closedOpen(1, 20), rangeSet.span());
    }

    @Test
    public void testRangeMap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); // {

        rangeMap.asMapOfRanges().forEach((range, value) -> {
            logger.info("range:{},value is {}", range, value);
        });

        logger.info("range:{}", rangeMap.subRangeMap(Range.closed(1, 2)));
        logger.info("range:{}", rangeMap.subRangeMap(Range.closed(-1, 2)));
        logger.info("range:{}", rangeMap.subRangeMap(Range.closed(1, 6)));

        Assert.assertEquals("foo", rangeMap.get(2));
    }
}
