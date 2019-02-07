package me.study.java.rxjavad.operator;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import me.study.java.rxjavad.RxUtils;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class DoOnNextExamples {

    private RxUtils rxUtils = new RxUtils();

    @Test
    public void simpleDoOnNext() throws InterruptedException {
        int totalNumber = 10;
        CountDownLatch latch = new CountDownLatch(totalNumber);
        Observable.create(rxUtils.SendNumber(totalNumber)).
                observeOn(Schedulers.io()).
                doOnNext(i -> log.info("do on next:{}", i)).
                doOnNext(i -> latch.countDown()).
                subscribeOn(Schedulers.computation()).
                subscribe(System.out::println, Throwable::printStackTrace);

        latch.await();
    }
}
