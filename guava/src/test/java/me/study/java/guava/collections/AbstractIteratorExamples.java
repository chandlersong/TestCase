package me.study.java.guava.collections;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.AbstractSequentialIterator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AbstractIteratorExamples {

    @Test
    public void testHelloWorld() {
        List<String> in = Arrays.asList("a", "b", null);
        Iterator<String> iter = skipNulls(in.iterator());

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

    @Test
    public void testHelloWorld2() {
        Iterator<Integer> powersOfTwo = new AbstractSequentialIterator<Integer>(1) { // note the initial value!
            protected Integer computeNext(Integer previous) {
                return (previous == 1 << 30) ? null : previous * 2;
            }
        };

        while (powersOfTwo.hasNext()) {
            System.out.println(powersOfTwo.next());
        }
    }


    static Iterator<String> skipNulls(final Iterator<String> in) {
        return new AbstractIterator<String>() {
            protected String computeNext() {
                while (in.hasNext()) {
                    String s = in.next();
                    if (s != null) {
                        return s;
                    }
                }
                return endOfData();
            }
        };
    }
}
