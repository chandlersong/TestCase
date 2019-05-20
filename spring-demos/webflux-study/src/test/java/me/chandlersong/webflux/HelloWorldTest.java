package me.chandlersong.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest(classes = WebFluxApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class HelloWorldTest {

    private WebClient client = WebClient.create("http://localhost:8080");

    @Test
    public void testHelloWorld() throws InterruptedException {
        Mono<String> helloWorld = client.get()
                .uri("/mono/{id}", "1")
                .retrieve()
                .bodyToMono(String.class);

        CountDownLatch latch = new CountDownLatch(1);
        helloWorld.subscribe(s->{
            log.info("receive message:{}",s);
            latch.countDown();
        });
        latch.await();
    }


    @Test
    public void testFluxStream() throws InterruptedException {
        Flux<String> stream = client.get()
                .uri("/flux/{id}", "1")
                .retrieve()
                .bodyToFlux(String.class);

        CountDownLatch latch = new CountDownLatch(5);
        stream.subscribe(s->{
            log.info("receive message:{},time:{}",s,System.currentTimeMillis());
            latch.countDown();
        });
        latch.await();
    }

    @Test
    public void testObservableStream() throws InterruptedException {
        Flux<String> stream = client.get()
                .uri("/observable/{id}", "1")
                .retrieve()
                .bodyToFlux(String.class);

        CountDownLatch latch = new CountDownLatch(5);
        stream.subscribe(s->{
            log.info("receive message:{},time:{}",s,System.currentTimeMillis());
            latch.countDown();
        });
        latch.await();
    }
}
