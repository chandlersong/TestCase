package org.example.consumer;

import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.ai.AiService;
import org.example.ai.io.InfoGanResponse;
import org.example.ai.io.MnistResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@Log4j2
@RestController
public class HelloController {

    @DubboReference
    private AiService aiService;


    @GetMapping("/mnist:{number}")
    public ResponseEntity<MnistResponse> goHello(@PathVariable Integer number) {
        return ResponseEntity.ok(new MnistResponse(aiService.PredictMnist(number)));
    }


    @PostMapping("/infoGan:{number}")
    public ResponseEntity<InfoGanResponse> infoGan(@PathVariable Integer number) {

        RetryTemplate template = RetryTemplate.builder()
                                              .maxAttempts(3)
                                              .fixedBackoff(1000)
                                              .retryOn(Exception.class)
                                              .build();


        InfoGanResponse body = template.execute(ctx -> new InfoGanResponse(aiService.InfoGan(number)));
        return ResponseEntity.ok(body);
    }
}
