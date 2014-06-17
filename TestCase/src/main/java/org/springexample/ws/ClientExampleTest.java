package org.springexample.ws;

import com.hilatest.jibx.FooRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ws.xml")
public class ClientExampleTest extends AbstractJUnit4SpringContextTests {

    private static final String MESSAGE =
            "<message xmlns=\"http://tempuri.org\">Hello Web Service World</message>";

    @Resource(name="webServiceTemplate")
    private WebServiceTemplate webServiceTemplate;

    @Resource(name="marshallWebServiceTemplate")
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
        marshallWebServiceTemplate.marshalSendAndReceive(order);
    }
}
