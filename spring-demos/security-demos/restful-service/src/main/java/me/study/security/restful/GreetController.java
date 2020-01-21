package me.study.security.restful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @RequestMapping("/greeting")
    public String greeting() {
        return "ok";
    }
}
