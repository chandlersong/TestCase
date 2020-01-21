package me.study.security.restful;

import me.study.spirng.tools.ServerRunner;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.slf4j.LoggerFactory.getLogger;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestfulSecurityTest {

    private static final Logger logger = getLogger(RestfulSecurityTest.class);


    /**
     * 只能通过web登录，code访问懒得写了。
     */
    @Test
    public void testHttpDigest() throws IOException {

        ServerRunner.createAndRunServer(SecurityRestfulApplication.class, "http-digest-application.yml");

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            logger.info("password is {}:", new String(Hex.encode(digest.digest("admin:Contacts Realm via Digest Authentication:admin".getBytes()))));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }


        HttpClient client = HttpClientBuilder.create().build();
        HttpGet post = new HttpGet("http://localhost:8080/greeting");
        HttpResponse response = client.execute(post);


        HeaderIterator headerIterator = response.headerIterator();

        while (headerIterator.hasNext()) {
            Header header = headerIterator.nextHeader();

            logger.info("header key is {},header value is{}", header.getName(), header.getValue());
        }

        logger.info("status is {}", response.getStatusLine().getStatusCode());
    }
}

