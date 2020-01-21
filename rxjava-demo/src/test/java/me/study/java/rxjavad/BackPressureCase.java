package me.study.java.rxjavad;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BackPressureCase {

    @Test
    public void SimpleCase() throws InterruptedException {

        int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
        Flowable.create((FlowableOnSubscribe<Integer>) e -> {
            for (int i = 0; i < count; i++) {
                log.info("emit {}", i);
                e.onNext(i);
            }
            log.info("finish emit");
            e.onComplete();
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(createSubscriber(latch, 1, 1));
        latch.await(30, TimeUnit.SECONDS);
    }

    @Test
    public void SimpleMultiReceiver() throws InterruptedException {

        int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
        Flowable.create((FlowableOnSubscribe<Integer>) e -> {
            for (int i = 0; i < count; i++) {
                log.info("emit {}", i);
                e.onNext(i);
            }
            log.info("finish emit");
            e.onComplete();
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(createSubscriber(latch, 3, 1));
        latch.await(30, TimeUnit.SECONDS);
    }

    @Test
    public void printCacheSize() throws InterruptedException {

        int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
        Flowable.create((FlowableOnSubscribe<Integer>) e -> {
            for (int i = 0; i < count; i++) {
                log.info("emit {}", i);
                log.info("cache size {}", e.requested());
                e.onNext(i);
            }
            log.info("finish emit");
            e.onComplete();
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(createSubscriber(latch, 1, 1));
        latch.await(30, TimeUnit.SECONDS);
    }


    private Subscriber<Integer> createSubscriber(CountDownLatch latch, final int firstReceiveNumber, final int eachReceiveNumber) {
        return new Subscriber<Integer>() {

            private Subscription mys;

            @Override
            public void onSubscribe(Subscription s) {
                s.request(firstReceiveNumber);
                mys = s;
            }

            @SneakyThrows
            @Override
            public void onNext(Integer integer) {
                log.info("receive {}", integer);
                latch.countDown();
                mys.request(eachReceiveNumber);
                Thread.sleep(1000);
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onComplete() {
                log.info("finish process");
            }
        };
    }
}
