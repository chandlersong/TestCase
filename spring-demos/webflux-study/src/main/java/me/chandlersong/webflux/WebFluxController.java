package me.chandlersong.webflux;

import io.reactivex.Observable;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Log4j2
@RestController
public class WebFluxController {

    private static final String[] SAMPLE_ARRAY = {
            "1", "2", "3", "4", "5"
    };

    @GetMapping(path = "/mono/{id}")
    public Mono<String> singleWord(@PathVariable String id) {
        log.info("Mono is called,id is {}", id);
        return Mono.just("hello");
    }

    @GetMapping(path = "/flux/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> manyWord(@PathVariable String id) {
        log.info("flux called id is {}", id);
        return Flux.fromArray(new String[]{
                "1", "2", "3", "4", "5"
        });
    }

    @GetMapping(path = "/observable/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Observable<String> manyWordObservable(@PathVariable String id) {
        log.info("Observable called id is {}", id);
        return Observable.fromArray(SAMPLE_ARRAY);
    }


}
