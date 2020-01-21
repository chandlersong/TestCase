package me.study.java.eight.functionapi;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CommonTests {

    @Test
    public void testMapExample(){
        Map<String, Integer> nameMap = new HashMap<>();
        Integer value = nameMap.computeIfAbsent("John", s -> 6);
        System.out.println(value);
    }

}
