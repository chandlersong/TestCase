package me.study.java;

import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class DataDemos {

    @Test
    public void dataDemo() {
        DataEntry dataEntry = new DataEntry();
        dataEntry.setValue("hello");

        log.info("Data dataEntry value:{}", dataEntry.getValue());
        log.info("Data dataEntry hashcode:{}", dataEntry.hashCode());
        log.info("Data dataEntry toString:{}", dataEntry.toString());

        ValueEntry valueEntry = new ValueEntry("hello");
        log.info("value valueEntry value:{}", valueEntry.getValue());
        log.info("value valueEntry hashcode:{}", valueEntry.hashCode());
        log.info("value valueEntry toString:{}", valueEntry.toString());

    }
}

@Data
class DataEntry {

    private String value;
}

@Value
class ValueEntry {
    private String value;
}