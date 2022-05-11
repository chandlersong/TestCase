package org.example.consumer;

import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.go.User;
import org.example.go.UserProvider;
import org.example.producer.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class HelloController {

    @DubboReference
    private HelloService helloService;


    @DubboReference
    private UserProvider goService;

    @GetMapping("/{name}:hello")
    public ResponseEntity<String> testHello(@PathVariable String name) {
        return ResponseEntity.ok(helloService.sayHello(name));
    }


    @GetMapping("/go:{number}")
    public ResponseEntity<User> goHello(@PathVariable String number) {
        User body = goService.GetUser(number);
        log.info("abc");
        return ResponseEntity.ok(body);
    }
}
