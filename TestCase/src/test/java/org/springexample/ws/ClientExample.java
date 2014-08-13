package org.springexample.ws;

import java.io.StringReader;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.hilatest.jibx.FooRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ws.xml")
public class ClientExample extends AbstractJUnit4SpringContextTests {

    private static final String MESSAGE =
            "<message xmlns=\"http://tempuri.org\">Hello Web Service World</message>";

    @Resource(name = "webServiceTemplate")
    private WebServiceTemplate webServiceTemplate;

    @Resource(name = "marshallWebServiceTemplate")
    private WebServiceTemplate marshallWebServiceTemplate;

    @Test
    public void customSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8889/AnotherWebService",
                source, result);
    }

    @Test
    public void customSendAndReceiveObject() {
        FooRequest order = new FooRequest();
        order.setRequest("ok");
        marshallWebServiceTemplate.marshalSendAndReceive(order);
    }
}