package me.study.java.guava.collections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multiset;
import com.google.common.collect.SetMultimap;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class MultiCollectionsExamples {

    private static final Logger logger = getLogger(MultiCollectionsExamples.class);

    @Test
    public void testMultiSet() {
        Multiset<String> multiset = HashMultiset.create();
        addDuplicateToSet(multiset, "a", 3);
        addDuplicateToSet(multiset, "b", 4);
        addDuplicateToSet(multiset, "c", 5);
        addDuplicateToSet(multiset, "d", 6);
        addDuplicateToSet(multiset, "e", 7);


        Assert.assertEquals(3, multiset.count("a"));

        multiset.entrySet().forEach(e -> logger.info("{} has {} entry", e.getElement(), e.getCount()));
    }


    @Test
    public void testMultiMap() {
        SetMultimap<String, String> map = MultimapBuilder.hashKeys().hashSetValues().build();

        map.put("a", "abc");
        map.put("a", "bcd");
        map.put("b", "123");

        Assert.assertEquals(3, map.size());

        map.asMap().forEach((key, collection) -> {
            Assert.assertTrue(((collection instanceof Set)));
            logger.info("key is {}, collection is {}", key, collection);
        });
    }

    private void addDuplicateToSet(Multiset<String> multiset, String value, int times) {

        for (int i = 0; i < times; i++) {
            multiset.add(value);
        }
    }
}
