package me.study.java.java8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalTest {

    private Logger logger = LoggerFactory.getLogger(OptionalTest.class);

    /**
     * orElse won't refresh the object in Optional
     */
    @Test
    public void testOrElseGet(){
        Optional<Object> test = Optional.empty();

        Object o1 = test.orElseGet(() -> new Object());
        Object o2 = test.orElseGet(() -> new Object());

        logger.info("o1 and o2 is same:{}",(o1==o2));
        logger.info("test.get():{}",test.orElse("still null"));
        Object o3 = test.orElseGet(this::createObject);
        Object o4 = test.orElseGet(this::createObject);
        logger.info("o3 and o4 is same:{}",(o3==o4));
    }

    private Object createObject(){
        return new Object();
    }
}

