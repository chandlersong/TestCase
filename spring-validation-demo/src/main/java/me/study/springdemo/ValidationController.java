package me.study.springdemo;

import me.study.springdemo.io.Person;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ValidationController {

    @PostMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> helloWorld(@Valid @RequestBody Person p) {
        p.setName("hello " + p.getName());
        return ResponseEntity.ok(p);
    }

}
