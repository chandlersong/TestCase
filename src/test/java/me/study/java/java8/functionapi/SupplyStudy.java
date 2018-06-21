package me.study.java.java8.functionapi;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class SupplyStudy {

    private Logger logger = LoggerFactory.getLogger(SupplyStudy.class);

    @Test
    public void testLazyInitialize() {
        LazyInitization lazy = new LazyInitization();
        logger.info("hash code:{}", lazy.getFoo());
        logger.info("hash code:{}", lazy.getFoo());
        logger.info("hash code:{}", lazy.getFoo());
    }
}

class LazyInitization {

    private Logger logger = LoggerFactory.getLogger(LazyInitization.class);

    private Supplier<Object> fooField = () -> {
        Object val = expensiveInitialize();
        fooField = () -> val; // it change the value here
        return val;
    };

    public Object expensiveInitialize() {
        System.out.println("expensive initialize");
        Object o = new Object();
        logger.info("hash code:{}", o);
        return o;
    }

    public Object getFoo() {
        return fooField.get();
    }
}
