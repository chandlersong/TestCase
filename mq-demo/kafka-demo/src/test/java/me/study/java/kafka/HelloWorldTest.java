package me.study.java.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldTest {

    @Autowired
    private KafkaTemplate<Object, Object> template;

    /**
     * need add kafka server to you host
     */
    @Test
    public void testTestMessage() {
        template.send("helloWorld", "chandler");

    }
}
