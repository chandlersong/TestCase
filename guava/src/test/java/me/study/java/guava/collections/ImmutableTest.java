package me.study.java.guava.collections;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Collections;

public class ImmutableTest {

    @Test
    public void howToInitial() {
        ImmutableSet.of(
                "red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple");
        ImmutableSet.copyOf(Collections.EMPTY_SET);
    }


}
