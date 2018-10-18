package me.study.security.restful;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.slf4j.LoggerFactory.getLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestfulSecurityTest {

    private static final Logger logger = getLogger(RestfulSecurityTest.class);


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 只能通过web登录，code访问懒得写了。
     */
    @Test
    public void testHttpDigest() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            logger.info("password is {}:", new String(Hex.encode(digest.digest( "admin:Contacts Realm via Digest Authentication:admin".getBytes()))));
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }


        ResponseEntity<String> result = restTemplate.getForEntity("/greeting", String.class);




        HttpHeaders headers = result.getHeaders();
        for (String s : headers.keySet()) {
            logger.info("key is {}", s);
            headers.get(s).forEach(value -> logger.info("value is {}:", value));
        }
        Assert.assertEquals("ok", result.getBody());
    }
}

