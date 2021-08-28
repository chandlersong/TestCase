package com.example.consumersimple;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "org.example:producer-simple:+:stubs:8090")
@DirtiesContext
public class BasicMathControllerIntegrationTest {


    /**
     * 这个是客户端进行的测试
     */
    @Test
    public void given_WhenPassEvenNumberInQueryParam_ThenReturnEven(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8090/fraudcheck",
                HttpMethod.POST,
                new HttpEntity<>("{\"client.id\":1234567890,\"loanAmount\":99999}", headers),
                String.class);
        System.out.println("response: " + responseEntity.getBody());

    }


}
