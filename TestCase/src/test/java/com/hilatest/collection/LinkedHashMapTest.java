package com.hilatest.collection;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class LinkedHashMapTest {

    @Test
    public void testOrder() {

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        for (int i = 0; i < 10; i++) {
            String key = RandomStringUtils.randomAlphabetic(10);
            String value = RandomStringUtils.randomAlphabetic(10);
            map.put(key, value);
            System.out.println("key:" + key + ",value:" + value);
        }
        System.out.println("start to iterator");
        Iterator<String> keyiter = map.keySet().iterator();

        while (keyiter.hasNext()) {
            System.out.println("key:" + keyiter.next());
        }
    }
}
