package me.study.java;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

public class GetSetDemos {

    @Test
    public void testGet() {
        Entry e = new Entry();
        e.setValue("lombok");
        e.testHelloWorld();
    }


}


@Slf4j
class Entry {

    @Setter
    @Getter
    private String value;

    public void testHelloWorld() {
        log.info("hello :{}", value);
    }
}