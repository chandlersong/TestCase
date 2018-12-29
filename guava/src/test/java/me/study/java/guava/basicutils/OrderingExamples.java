package me.study.java.guava.basicutils;

import com.google.common.collect.Ordering;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.slf4j.LoggerFactory.getLogger;

public class OrderingExamples {

    private static final Logger logger = getLogger(OrderingExamples.class);

    @Test
    public void howToUse() {
        List<Foo> list = createRandom(10);
        Ordering<Foo> ordering = Ordering.from(Comparator.comparingInt(f -> f.value));

        ordering.sortedCopy(list).forEach(f -> logger.debug(" value is {}", f.value));
        logger.info("greatestOf 5");
        ordering.greatestOf(list, 5).forEach(f -> logger.debug(" value is {}", f.value));
        logger.info("leastOf 5");
        ordering.leastOf(list, 5).forEach(f -> logger.debug(" value is {}", f.value));
    }


    public List<Foo> createRandom(int length) {

        List<Foo> result = new ArrayList<>(length);
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            result.add(new Foo(r.nextInt()));
        }

        return result;
    }

    class Foo {

        public Foo(int value) {
            this.value = value;
        }

        int value;
    }
}
