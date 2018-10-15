package me.study.security.restful;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.slf4j.LoggerFactory.getLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestfulSecurityTest {

    private static final Logger logger = getLogger(RestfulSecurityTest.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHelloWorld() {

        ResponseEntity<String> result = restTemplate.getForEntity("/greeting", String.class);
        Assert.assertEquals("ok",result.getBody());
    }
}
