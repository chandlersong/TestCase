package me.study.java.eight.functionapi;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class BuildInFunctionInterface {

    private Logger logger = LoggerFactory.getLogger(BuildInFunctionInterface.class);

    @Test
    public void testLazyInitialize() {
        LazyInitization lazy = new LazyInitization();
        logger.info("hash code:{}", lazy.getFoo());
        logger.info("hash code:{}", lazy.getFoo());
        logger.info("hash code:{}", lazy.getFoo());
    }

    @Test
    public void testOperater() {
        List<String> names = Arrays.asList("bob", "josh", "megan");

        names.replaceAll(name -> name.toUpperCase());
    }

    @Test
    public void testFunction() {
        Function<Integer, Integer> pulsOne = s -> {
            System.out.println("pulsOne ");
            return s + 1;
        };

        Function<Integer, String> intToString = s -> {
            System.out.println("intToString ");
            return s.toString();
        };
        Function<String, String> quote = s -> {
            System.out.println("quote ");
            return "'" + s + "'";
        };

        Function<Integer, String> combine1 = quote.compose(intToString.compose(pulsOne));
        logger.info("combine1 is {}",combine1.apply(5));


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
