package me.study.java.guava.collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class TableExamples {

    private static final Logger logger = getLogger(TableExamples.class);

    @Test
    public void testHowToCreate() {
        Table<String, String, Double> weightedGraph = HashBasedTable.create();
        weightedGraph.put("1", "a", 2.0);
        weightedGraph.put("2", "a", 3.0);
        weightedGraph.put("1", "b", 8.0);

        weightedGraph.row("1").forEach((col, value) -> {
            logger.info("col is {},value is {}", col, value);
        });
        weightedGraph.row("2").forEach((col, value) -> {
            logger.info("col is {},value is {}", col, value);
        });
    }
}
