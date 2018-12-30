package me.study.java.guava.basicutils;

import com.google.common.base.Throwables;
import org.junit.Test;

public class ThrowableExamples {

    @Test(expected = IllegalStateException.class)
    public void testHelloWorld() {


        try {
            throw new Exception();
        } catch (Throwable throwable) {
            Throwables.propagateIfPossible(throwable, IllegalStateException.class);
        }

        try {
            throw new IllegalStateException();
        } catch (Throwable throwable) {
            Throwables.propagateIfPossible(throwable, IllegalStateException.class);
        }

    }
}
