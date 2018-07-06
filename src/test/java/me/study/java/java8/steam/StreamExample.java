package me.study.java.java8.steam;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class StreamExample {

    private static final Logger logger = LoggerFactory.getLogger(StreamExample.class);

    @Test
    public void testCheckNull() {

        List<String> collect = Stream.of("a", null, "b").filter(Objects::nonNull).collect(Collectors.toList());

        System.out.print(collect);

    }

    @Test
    public void testCollectExample() {
        Set<Map.Entry<String, String>> data = createTestData();
        logger.info("group by:{}", data.stream().collect(groupingBy(entry -> entry.getKey().charAt(0), counting())));
    }


    private Set<Map.Entry<String, String>> createTestData() {

        Map<String, String> data = new HashMap<>();

        data.put("Adam", "man");
        data.put("Jerry", "man");
        data.put("Peter", "man");
        data.put("ABBY", "woman");
        data.put("Eve", "woman");
        data.put("Marry", "woman");
        return data.entrySet();
    }
}
