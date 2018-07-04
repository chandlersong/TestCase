package me.study.java.java8.steam;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SteamExample {

    @Test
    public void testCheckNull() {

        List<String> collect = Stream.of("a", null, "b").filter(Objects::nonNull).collect(Collectors.toList());

        System.out.print(collect);
    }
}
