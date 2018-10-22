package me.study.security.restful;

import me.study.spirng.tools.ServerRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

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
    public void testHttpDigest() {

        ServerRunner.createAndRunServer(SecurityRestfulApplication.class, "http-digest-application.yml");


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            logger.info("password is {}:", new String(Hex.encode(digest.digest( "admin:Contacts Realm via Digest Authentication:admin".getBytes()))));
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }


        ResponseEntity<String> result = (new RestTemplate()).getForEntity("http://localhost:8080/greeting", String.class);




        HttpHeaders headers = result.getHeaders();
        for (String s : headers.keySet()) {
            logger.info("key is {}", s);
            headers.get(s).forEach(value -> logger.info("value is {}:", value));
        }
        Assert.assertEquals("ok", result.getBody());
    }
}

