package me.study.java.guava.collections;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PeekingIteratorExamples {

    @Test
    public void testHelloWorld() {

        int loop = 0;
        String lastPeek = "";

        PeekingIterator<String> iter = Iterators.peekingIterator(((List<String>) Lists.newArrayList("1", "2", "3")).iterator());

        while (iter.hasNext()) {

            loop++;
            String next = iter.peek();

            if (lastPeek.equals(next)) {
                iter.next();
            } else {
                lastPeek = next;
            }

        }

        Assert.assertEquals(6, loop);

    }

}
