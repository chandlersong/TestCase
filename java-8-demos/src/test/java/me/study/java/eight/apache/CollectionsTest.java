package me.study.java.eight.apache;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class CollectionsTest {

    private static final Logger logger = LoggerFactory.getLogger(CollectionsTest.class);

    @Test
    public void testInvertMap(){
        HashMap<Integer,Integer> data = new HashMap<>();
        data.put(1,2);
        data.put(2,2);
        data.put(3,2);
        data.put(4,1);
        data.put(5,5);

        logger.info("after revert map:{}",MapUtils.invertMap(data));
    }
}
