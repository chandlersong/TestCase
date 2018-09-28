package me.study.java.eight.steam;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExample {

    private static final Logger logger = LoggerFactory.getLogger(StreamExample.class);

    @Test
    public void testCheckNull() {

        List<String> collect = Stream.of("a", null, "b").filter(Objects::nonNull).collect(Collectors.toList());

        System.out.print(collect);

    }

    @Test
    public void testCollectExampleGroupBy() {
        Set<Map.Entry<String, String>> data = createTestData();
        logger.info("group by:{}", data.stream().collect(groupingBy(entry -> entry.getKey().charAt(0), counting())));
    }

    @Test
    public void testCollectExampleGroupBy1() {
        Set<Map.Entry<String, String>> data = createTestData();
        logger.info("group by:{}", data.stream().collect(groupingBy(entry -> entry.getKey().charAt(0))));
    }



    @Test
    public void testReduce() {
        List<Object[]> data = Arrays.asList(
                new Object[] { "a1", "a", "567" },
                new Object[] { "a1", "b", "123" },
                new Object[] { "b1", "a", "798" },
                new Object[] { "b1", "b", "151" },
                new Object[] { "a1", "c", "789" },
                new Object[] { "c1", "a", "741" },
                new Object[] { "c1", "e", "2312" },
                new Object[] { "b1", "f", "fsbf" },
                new Object[] { "c1", "c", "74464" }
        );

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

class GroupByObject {

    private String name;

    private Map<String, String> value;

    public GroupByObject() {
        value = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(Map<String, String> value) {
        this.value = value;
    }

    public void addNewValue(String key, String value) {
        this.value.put(key, value);
    }

    public GroupByObject getGroupByObject() {
        return this;
    }
}
