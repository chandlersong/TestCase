package me.chandlersong.webflux;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
public class WebFluxController {

    @GetMapping("/mono/{id}")
    public Mono<String> singleWord(@PathVariable String id) {
        log.info("id is {}", id);
        return Mono.just("hello");
    }

    @GetMapping("/flux/{id}")
    public Flux<String> manyWord(@PathVariable String id) {
        log.info("id is {}", id);
        return Flux.fromArray(new String[]{
                "1", "2"
        });
    }
}
