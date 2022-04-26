package org.example.consumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.producer.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @DubboReference
    private HelloService helloService;

    @GetMapping("/{name}:hello")
    public ResponseEntity<String> testHello(@PathVariable String name) {
        return ResponseEntity.ok(helloService.sayHello(name));
    }
}
