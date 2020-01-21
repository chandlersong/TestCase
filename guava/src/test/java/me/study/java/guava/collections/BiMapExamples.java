package me.study.java.guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Assert;
import org.junit.Test;

public class BiMapExamples {

    @Test
    public void testHowToCreate() {
        BiMap<String, Integer> userId = HashBiMap.create();
        userId.put("abc", 123);
        userId.put("aab", 567);

        Assert.assertEquals("abc", userId.inverse().get(123));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testHowToCreateDuplicate() {
        BiMap<String, Integer> userId = HashBiMap.create();
        userId.put("abc", 123);
        userId.put("bcd", 123);
    }
}
