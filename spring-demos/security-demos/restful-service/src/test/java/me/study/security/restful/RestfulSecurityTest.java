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

    @Test
    public void testHelloWorld() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            logger.info("password is {}:", new String(Hex.encode(digest.digest( "admin:Contacts Realm via Digest Authentication:admin".getBytes()))));
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        ResponseEntity<String> result =  restTemplate().getForEntity("http://localhost:8080/greeting", String.class);


        HttpHeaders headers = result.getHeaders();
        for (String s : headers.keySet()) {
            logger.info("key is {}", s);
            headers.get(s).forEach(value -> logger.info("value is {}:", value));
        }
        Assert.assertEquals("ok", result.getBody());
    }

    public RestTemplate restTemplate() {
        HttpHost host = new HttpHost("localhost", 8080, "http");
        CloseableHttpClient client = HttpClientBuilder.create().
                setDefaultCredentialsProvider(provider()).useSystemProperties().build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactoryDigestAuth(host, client);

        return new RestTemplate(requestFactory);
    }

    private CredentialsProvider provider() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials =
                new UsernamePasswordCredentials("admin", "admin");
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }
}


class HttpComponentsClientHttpRequestFactoryDigestAuth extends HttpComponentsClientHttpRequestFactory {
    HttpHost host;

    public HttpComponentsClientHttpRequestFactoryDigestAuth(final HttpHost host, final HttpClient httpClient) {
        super(httpClient);
        this.host = host;
    }

    //

    @Override
    protected HttpContext createHttpContext(final HttpMethod httpMethod, final URI uri) {
        return createHttpContext();
    }

    private HttpContext createHttpContext() {
        // Create AuthCache instance
        final AuthCache authCache = new BasicAuthCache();
        // Generate DIGEST scheme object, initialize it and add it to the local auth cache
        final DigestScheme digestAuth = new DigestScheme();
        // If we already know the realm name
        digestAuth.overrideParamter("realm", "Custom Realm Name");

        // digestAuth.overrideParamter("nonce", "MTM3NTU2OTU4MDAwNzoyYWI5YTQ5MTlhNzc5N2UxMGM5M2Y5M2ViOTc4ZmVhNg==");
        authCache.put(host, digestAuth);

        // Add AuthCache to the execution context
        final BasicHttpContext localcontext = new BasicHttpContext();
        localcontext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);
        return localcontext;
    }

}
