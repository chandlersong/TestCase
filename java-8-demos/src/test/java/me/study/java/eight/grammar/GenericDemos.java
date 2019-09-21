package me.study.java.eight.grammar;

import me.study.java.Generic;
import org.junit.Test;

public class GenericDemos {

    @Test
    public void testGeneric() {
        Generic<String> target = new Generic<>();

        target.getClassType((String[].class));
    }
}
